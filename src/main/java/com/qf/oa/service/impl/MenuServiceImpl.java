package com.qf.oa.service.impl;

import com.qf.oa.common.SysRusult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.mapper.SysMenuMapper;
import com.qf.oa.service.IMenuService;
import com.qf.oa.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<SysMenu> implements IMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public IBaseDao getDao() {

        return sysMenuMapper;
    }

    @Override
    public List<SysMenuVO> getMenuList(Long roleId) {
        //查询所有的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.getAllMenuList();
        //查询当前角色有的菜单
//        List<SysMenu> roleMenuList = sysMenuMapper.getRoleMenuList(roleId);
        //查询当前角色拥有的菜单id集合
        List<Long> roleMenuIdList = sysMenuMapper.getRoleMenuIdList(roleId);
        //定义一个集合
        List<SysMenuVO> resultList = new ArrayList<>(sysMenuList.size());
        //把以上的两个操作整合在一起，得到一个真实数据菜单集合
        for (SysMenu menu : sysMenuList) {
            SysMenuVO sysMenuVO = new SysMenuVO();
            sysMenuVO.setMenuId(menu.getMenuId());
            sysMenuVO.setMenuParentId(menu.getMenuParentId());
            sysMenuVO.setMenuName(menu.getMenuName());

            //当该角色拥有这个菜单时，才会设置为true
            if(roleMenuIdList.contains(menu.getMenuId())){
                sysMenuVO.setChecked(true);
            }
            //循环添加对象到集合中
            resultList.add(sysMenuVO);

        }
        return resultList;
    }

    @Override
    public SysRusult addAuthMenu(List<Long> ids, Long roleId) {
        sysMenuMapper.deleteAllMenuByRoleId(roleId);
        int count=sysMenuMapper.addAllMenu(ids,roleId);
        SysRusult sysRusult=new SysRusult();
        if(count>0){
            sysRusult.setResult(true);
        }else {
            sysRusult.setResult(false);
        }
        return sysRusult;
    }

    @Override
    public List<SysMenu> getMenuListByUserId(Long userId) {
        return sysMenuMapper.getMenuListByUserId(userId);
    }
}
