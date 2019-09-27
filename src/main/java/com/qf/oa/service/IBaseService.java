package com.qf.oa.service;

import com.qf.oa.entity.SysUser;

public interface IBaseService<T>{

    int deleteByPrimaryKey(Long userId);

        int insert( T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
}
