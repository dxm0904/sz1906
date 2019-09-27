package com.qf.oa.dao;

public interface IBaseDao<T> {
    int deleteByPrimaryKey(Long userId);

    int insert( T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    int delAuthUser(Long userId, Long roleId);
}
