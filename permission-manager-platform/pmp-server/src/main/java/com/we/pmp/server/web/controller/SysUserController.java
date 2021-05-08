package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.common.utils.ValidatorUtil;
import com.we.pmp.model.entity.SysUserEntity;
import com.we.pmp.server.support.log.LogAnnotation;
import com.we.pmp.server.support.shiro.ShiroUtil;
import com.we.pmp.server.web.sercice.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理Controller
 * @author we
 * @date 2021-05-08 15:12
 **/
@RestController
@Slf4j
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取当前登录用户的详情
     * @return
     */
    @GetMapping(value = "/info")
    public BaseResponse currInfo(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            SysUserEntity user= (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            Long userId = user.getUserId();
            resMap.put("user",userId);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 修改登录密码
     * @param password
     * @param newPassword
     * @return
     */
    @PostMapping("/password")
    @LogAnnotation("修改登录密码")
    public BaseResponse updatePassword(String password,String newPassword){
        if (StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)){
            return new BaseResponse(StatusCode.PasswordCanNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            // 真正的处理逻辑：先校验旧密码输入是否正确，再更新新的密码
            SysUserEntity entity= (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            final String salt=entity.getSalt();

            String oldPsd= ShiroUtil.sha256(password,salt);
            if (!entity.getPassword().equals(oldPsd)){
                return new BaseResponse(StatusCode.OldPasswordNotMatch);
            }
            String newPsd=ShiroUtil.sha256(newPassword,salt);

            // 执行更新密码的逻辑
            log.info("~~~~旧密码正确，开始更新新的密码~~~~");
            sysUserService.updatePassword(entity.getUserId(),oldPsd,newPsd);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

    /**
     * 分页列表模糊查询
     * @param paramMap
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions(value = {"sys:user:list"})
    public BaseResponse list(@RequestParam Map<String,Object> paramMap){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            log.info("用户模块~分页列表模糊查询：{}",paramMap);
            PageResult page=sysUserService.queryPage(paramMap);
            resMap.put("page",page);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }


    /**
     * 新增用户
     * @param user
     * @param result
     * @return
     */
    @LogAnnotation("新增用户")
    @PostMapping("/save")
    @RequiresPermissions(value = {"sys:user:save"})
    public BaseResponse save(@RequestBody @Validated SysUserEntity user, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        if (StringUtils.isBlank(user.getPassword())){
            return new BaseResponse(StatusCode.PasswordCanNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysUserService.saveUser(user);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions(("sys:user:list"))
    public BaseResponse info(@PathVariable Long userId){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap=Maps.newHashMap();
        try {
            log.info("用户模块~获取详情：{}",userId);
            resMap.put("user",sysUserService.getInfo(userId));
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

    /**
     * 更新用户信息
     * @param user
     * @param result
     * @return
     */
    @LogAnnotation("修改用户")
    @PostMapping("/update")
    @RequiresPermissions(value = {"sys:user:update"})
    public BaseResponse update(@RequestBody @Validated SysUserEntity user,BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("用户模块~修改用户：{}",user);
            sysUserService.updateUser(user);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @LogAnnotation("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public BaseResponse delete(@RequestBody Long[] ids){
        if (ids==null || ids.length<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        // 超级管理员~admin不能删除；当前登录用户不能删
        // if (Arrays.asList(ids).contains(Constant.SUPER_ADMIN)){
        if (ArrayUtils.contains(ids,Constant.SUPER_ADMIN)){
            return new BaseResponse(StatusCode.SysUserCanNotBeDelete);
        }
        SysUserEntity user= (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getUserId();
        if (ArrayUtils.contains(ids,userId)){
            return new BaseResponse(StatusCode.CurrUserCanNotBeDelete);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysUserService.deleteUser(ids);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

    /**
     * 重置密码
     * @param ids
     * @return
     */
    @LogAnnotation("重置用户密码")
    @PostMapping("/psd/reset")
    @RequiresPermissions("sys:user:resetPsd")
    public BaseResponse restPsd(@RequestBody Long[] ids){
        if (ids==null || ids.length<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        // 超级管理员~admin不能删除；当前登录用户不能删
        SysUserEntity user= (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getUserId();
        if (ArrayUtils.contains(ids, Constant.SUPER_ADMIN) || ArrayUtils.contains(ids,userId)){
            return new BaseResponse(StatusCode.SysUserAndCurrUserCanNotResetPsd);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysUserService.updatePsd(ids);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }
}
