package com.qf.oa.service;

import com.qf.oa.common.SysRusult;
import com.qf.oa.entity.SysRole;

import java.util.List;

public interface IRoleService extends IBaseService<SysRole>{

    List<SysRole> getRoleList();

    SysRusult batchAddUser(List<Long> idsList, Long roleId);

    SysRusult delAuthUser(Long userId, Long roleId);
}
