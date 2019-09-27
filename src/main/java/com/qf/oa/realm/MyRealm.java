package com.qf.oa.realm;

import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.IMenuService;
import com.qf.oa.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm{
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权处理");
        SysUser sysUser= (SysUser) principalCollection.getPrimaryPrincipal();
        Long userId=sysUser.getUserId();
        List<SysMenu> menusList=menuService.getMenuListByUserId(userId);
        Set<String> permissionsSet=new HashSet<>();
        for(SysMenu menu:menusList){
            if(menu.getMenuType()==3){
                permissionsSet.add(menu.getMenuPath());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionsSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证处理");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        SysUser sysUser=userService.getUserByUserName(userName);
        if(sysUser!=null){
            ByteSource byteSource=ByteSource.Util.bytes(userName);
            return new SimpleAuthenticationInfo(sysUser,sysUser.getUserPassword(),byteSource,this.getName());
        }
        return null;
    }
}
