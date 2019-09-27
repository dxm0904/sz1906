package com.qf.oa.mapper;

import com.qf.oa.common.SysRusult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole>{

    List<SysRole> getRoleList();

    int batchAddUser(@Param("idsList") List<Long> idsList, @Param("roleId") Long roleId);

    int delAuthUser(@Param("userId") Long userId, @Param("roleId") Long roleId);
}