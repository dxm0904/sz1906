package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.entity.Page;
import com.qf.oa.entity.SysUser;

public interface IUserService extends IBaseService<SysUser>{


    PageInfo<SysUser> getUserPage(Page page);

    PageInfo<SysUser> getUserByCondition(Page page, String userName);

    PageInfo<SysUser> queryAuthByRoleId(Page page,long roleId);

    PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId,String userName,Page page);

    SysUser checkLogin(SysUser user);

    SysUser getUserByUserName(String userName);
}
