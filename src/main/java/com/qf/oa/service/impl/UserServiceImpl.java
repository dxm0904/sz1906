package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.Page;
import com.qf.oa.entity.SysUser;
import com.qf.oa.mapper.SysUserMapper;
import com.qf.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements IUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IBaseDao getDao() {
        return sysUserMapper;
    }

    @Override
    public PageInfo<SysUser> getUserPage(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list=sysUserMapper.getUserList();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<SysUser> getUserByCondition(Page page, String userName) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list=sysUserMapper.getUserByCondition(userName);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<SysUser> queryAuthByRoleId(Page page,long roleId) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userLists=sysUserMapper.queryAuthByRoleId(roleId);
        return new PageInfo<>(userLists);
    }

    @Override
    public PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId,String userName,Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> userList=sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        return new PageInfo<>(userList);
    }

    @Override
    public SysUser checkLogin(SysUser user) {
        SysUser sysUser=sysUserMapper.getUserByName(user.getUserName());

        if(user.getUserPassword().equals(sysUser.getUserPassword())){
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }
}
