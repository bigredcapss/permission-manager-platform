package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理Mapper
 * @author we
 * @date 2021-05-07 16:52
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    /**
     * 根据父级Id，查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenuEntity> queryListParentId(Long parentId);
    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();
    /**
     * 获取所有菜单
     * @return
     */
    List<SysMenuEntity> queryList();
}
