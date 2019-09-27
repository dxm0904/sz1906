package com.qf.oa.service.impl;

import com.qf.oa.common.SysRusult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.mapper.SysRoleMapper;
import com.qf.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements IRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public IBaseDao getDao() {
        return sysRoleMapper;
    }

    @Override
    public List<SysRole> getRoleList() {
        return sysRoleMapper.getRoleList();
    }

    @Override
    public SysRusult batchAddUser(List<Long> idsList, Long roleId) {
        int count=sysRoleMapper.batchAddUser(idsList,roleId);
        SysRusult sysRusult=new SysRusult();
        if(count>0){
            sysRusult.setResult(true);
        }else {
            sysRusult.setResult(false);
        }
        return sysRusult;

    }

    @Override
    public SysRusult delAuthUser(Long userId, Long roleId) {
        int count=sysRoleMapper.delAuthUser(userId,roleId);
        SysRusult sysRusult=new SysRusult();
        if(count>0){
            sysRusult.setResult(true);
        }else {
            sysRusult.setResult(false);
        }
        return sysRusult;
    }
}
