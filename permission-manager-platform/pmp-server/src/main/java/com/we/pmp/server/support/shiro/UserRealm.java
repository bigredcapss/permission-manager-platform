package com.we.pmp.server.support.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.model.entity.SysMenuEntity;
import com.we.pmp.model.entity.SysUserEntity;
import com.we.pmp.model.mapper.SysUserMapper;
import com.we.pmp.server.web.sercice.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户认证，授权Realm
 * @author we
 * @date 2021-05-07 21:07
 **/
@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 资源-权限分配 ~ 授权 ~ 需要将分配给当前用户的权限列表赋值给Shiro的权限字段中去
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录用户信息
        SysUserEntity user = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();
        // 声明权限列表
        List<String> perms = Lists.newLinkedList();
        // 系统超级管理员拥有最高的权限，不需要去查询权限列表，直接拥有所有权限；否则，则需要根据当前用户id去查询权限列表
        if(userId.equals(Constant.SUPER_ADMIN)){
            List<SysMenuEntity> list = sysMenuService.list();
            if(list!=null && !list.isEmpty()){
                perms = list.stream().map(SysMenuEntity::getPerms).collect(Collectors.toList());

            }
        }else {
            perms = sysUserMapper.queryAllPerms(userId);
        }
        // 对于每一个授权字符串进行,的解析拆分
        Set<String> stringPermissions= Sets.newHashSet();
        if (perms!=null && !perms.isEmpty()){
            for (String p:perms){
                if (StringUtils.isNotBlank(p)){
                    stringPermissions.addAll(Arrays.asList(StringUtils.split(p.trim(),",")));
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(stringPermissions);
        return authorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        final String username = token.getUsername();
        final String password = String.valueOf(token.getPassword());
        log.info("用户名：{} 密码：{}",username,password);

        SysUserEntity entity=sysUserMapper.selectOne(new QueryWrapper<SysUserEntity>().eq("username",username));

        // 演示sql注入攻击
        // SysUserEntity entity=sysUserMapper.selectByUserName(username);

        // 账户不存在
        if (entity==null){
            throw new UnknownAccountException("账户不存在!");
        }
        // 账户被禁用
        if (0 == entity.getStatus()){
            throw new DisabledAccountException("账户已被禁用,请联系管理员!");
        }

        // 第一种 : 明文匹配
        // 账户密码不匹配
/*        if (!entity.getPassword().equals(password)){
            throw new IncorrectCredentialsException("账户密码不匹配!");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(entity,password,getName());*/

        // 第二种 : 盐值匹配
/*        String realPassword=ShiroUtil.sha256(password,entity.getSalt());
        if (StringUtils.isBlank(realPassword) || !realPassword.equals(entity.getPassword())){
            throw new IncorrectCredentialsException("账户密码不匹配!");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(entity,password,getName());*/

        // 第三种 : 交给shiro的密钥匹配器去实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity, entity.getPassword(),
                ByteSource.Util.bytes(entity.getSalt()), getName());
        return info;
    }

    /**
     * Shiro的密钥匹配器
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
