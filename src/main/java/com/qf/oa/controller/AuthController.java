package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.SysRusult;
import com.qf.oa.entity.Page;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysRole;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.IMenuService;
import com.qf.oa.service.IRoleService;
import com.qf.oa.service.IUserService;
import com.qf.oa.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("showAuthPage")
    public String toAuthPage(ModelMap map){
        List<SysRole> list=roleService.getRoleList();
        map.put("list",list);
        return "auth/authorization";
    }

    @RequestMapping("queryAuthByRoleId")
    public String queryAuthByRoleId(Page page,long roleId,ModelMap map){
        PageInfo<SysUser> pageInfo=userService.queryAuthByRoleId(page,roleId);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("url","auth/queryAuthByRoleId");
        Gson gson=new Gson();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("roleId",roleId);
        map.put("params",gson.toJson(paramMap));
        return "auth/auth_user";
    }

    @RequestMapping("queryNoAuthUserByRoleId")
    public String queryNoAuthUserByRoleId(Long roleId,String userName,Page page,ModelMap map){
        PageInfo<SysUser> pageInfo=userService.queryNoAuthUserByRoleId(roleId,userName,page);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("userName",userName);
        map.put("url","auth/queryNoAuthUserByRoleId");

        Gson gson=new Gson();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("roleId",roleId);
        paramMap.put("userName",userName);
        map.put("params",gson.toJson(paramMap));
        return "auth/no_auth_user";
    }

    @RequestMapping("batchAddUser")
    @ResponseBody
    public SysRusult batchAddUser(@RequestParam("ids") List<Long> idsList, Long roleId){

        return roleService.batchAddUser(idsList,roleId);

    }

    @RequestMapping("delAuthUser")
    @ResponseBody
    public SysRusult delAuthUser(Long userId,Long roleId){
       return roleService.delAuthUser(userId,roleId);
    }

    @RequestMapping("queryAuthMenuByRoleId")
    public String queryAuthMenuByRoleId(Long roleId,ModelMap map){
        List<SysMenuVO> menuVOList=menuService.getMenuList(roleId);
        map.put("menuVOList",new Gson().toJson(menuVOList));
        map.put("roleId",roleId);
        return "auth/auth_menu";
    }

    @RequestMapping("addAuthMenu")
    @ResponseBody
    public SysRusult addAuthMenu(@RequestParam("ids") List<Long> ids,Long roleId){

        return menuService.addAuthMenu(ids,roleId);
    }
}
