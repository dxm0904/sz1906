package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseDao{
    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> getUserList();

    List<SysUser> getUserByCondition(String userName);

    List<SysUser> queryAuthByRoleId(long roleId);

    List<SysUser> queryNoAuthUserByRoleId(@Param("roleId") Long roleId, @Param("userName") String userName);

    SysUser getUserByName(String userName);

    SysUser getUserByUserName(String userName);
}