package com.qf.oa.service;

import com.qf.oa.entity.SysUser;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testQuery(){
        SysUser sysUser=userService.selectByPrimaryKey(1L);
        System.out.println(sysUser);
    }
}
