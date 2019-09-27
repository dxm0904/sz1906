package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.entity.Page;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.IMenuService;
import com.qf.oa.service.IUserService;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("SysUser")
public class SysUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("getUserById")
    @ResponseBody
    public SysUser getUserById(Long userId){
        SysUser sysUser=userService.selectByPrimaryKey(userId);
        return  sysUser;
    }

    @RequestMapping("page")
    public String getPage(Page page, ModelMap map){
        PageInfo<SysUser> pageInfo=userService.getUserPage(page);
        map.put("pageInfo",pageInfo);
        return "user/userList";
    }

    @RequestMapping("selectByCondition")
    public String selectByCondition(Page page,String userName,ModelMap map){
        PageInfo<SysUser> pageInfo=userService.getUserByCondition(page,userName);
        map.put("url","SysUser/selectByCondition");
        map.put("userName",userName);
        map.put("pageInfo",pageInfo);
        Gson gson=new Gson();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userName",userName);
        map.put("params",gson.toJson(paramMap));
        return "user/userList";
    }

    @RequestMapping("checkLogin")
    public String login(SysUser user,ModelMap map){
       /* SysUser loginUser=userService.checkLogin(user);
        if(loginUser==null){
            return "login";
        }
        List<SysMenu> menuList=menuService.getMenuListByUserId(loginUser.getUserId());
        map.put("menuList",menuList);
        return "index";*/

        Subject currentUser=SecurityUtils.getSubject();
        //得到当前用户后判断是否登录
        if(!currentUser.isAuthenticated()){
            //如果未登录，创建一个TOKEN对象来验证
            UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
            //然后进行登录认证
            try {
                currentUser.login(token);
            }catch (AuthenticationException e){
                System.out.println("登录认证失败");
                return "login";
            }
        }
        //登录成功后的操作，展示用户能看到的菜单
        //得到simpleAuthentiedInfo构造方法的第一个参数
        SysUser sysUser= (SysUser) currentUser.getPrincipal();
        List<SysMenu> menuList=menuService.getMenuListByUserId(sysUser.getUserId());
        map.put("menuList",menuList);
        return "index";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("addUser")
    public String add(){
        System.out.println("这是一个添加的操作");
        return "addUser";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("updateUser")
    public String update(){
        System.out.println("这是一个修改的操作");
        return "updateUser";
    }
}
