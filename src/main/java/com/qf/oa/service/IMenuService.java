package com.qf.oa.service;

import com.qf.oa.common.SysRusult;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.vo.SysMenuVO;

import java.util.List;

public interface IMenuService extends IBaseService<SysMenu> {

    List<SysMenuVO> getMenuList(Long roleId);

    SysRusult addAuthMenu(List<Long> ids, Long roleId);

    List<SysMenu> getMenuListByUserId(Long userId);
}
