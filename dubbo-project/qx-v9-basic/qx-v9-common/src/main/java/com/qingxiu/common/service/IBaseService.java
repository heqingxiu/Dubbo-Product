package com.qingxiu.common.service;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/29-15:12
 * Description:
 **/
public interface IBaseService<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

    List<T> selectAll();
}
