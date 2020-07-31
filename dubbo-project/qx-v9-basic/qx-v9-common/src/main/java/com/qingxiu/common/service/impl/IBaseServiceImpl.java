package com.qingxiu.common.service.impl;

import com.github.pagehelper.PageInfo;
import com.qingxiu.common.dao.IBaseDao;
import com.qingxiu.common.service.IBaseService;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/29-15:14
 * Description:
 **/
// 不能确定类型，因此使用泛型
public abstract class IBaseServiceImpl<T> implements IBaseService<T> {

    // 将接口定义一个抽象方法（不能new ,但可以是抽象方法）
    public abstract IBaseDao<T> getBaseDao();

    @Override
    public int deleteByPrimaryKey(Long id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getBaseDao().insertSelective(t);
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getBaseDao().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(T t) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return updateByPrimaryKey(t);
    }

    @Override
    public List<T> selectAll() {
        return getBaseDao().selectAll();
    }

    /**
     * Unconditional page (Need to be implemented on provider service-end)
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<T> page(Integer pageIndex, Integer pageSize) {
        return null;
    }


}
