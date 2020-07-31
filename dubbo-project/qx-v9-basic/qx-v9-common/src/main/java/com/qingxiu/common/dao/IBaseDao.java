package com.qingxiu.common.dao;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/29-15:08
 * Description:
 **/
public interface IBaseDao<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

    List<T> selectAll();

    /**
     * Unconditional page (Need to be implemented on provider service-end)
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<T> page(Integer pageIndex, Integer pageSize);

    /**
     * Conditional page (Need to be implemented on provider service-end)
     *
     */

}
