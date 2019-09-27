package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu>{

    List<SysMenu> getAllMenuList();

    List<SysMenu> getRoleMenuList(Long roleId);

    List<Long> getRoleMenuIdList(Long roleId);

    void deleteAllMenuByRoleId(Long roleId);

    int addAllMenu(@Param("ids") List<Long> ids,@Param("roleId") Long roleId);

    List<SysMenu> getMenuListByUserId(Long userId);
}