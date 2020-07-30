package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingxiu.common.dao.IBaseDao;
import com.qingxiu.common.service.impl.IBaseServiceImpl;
import com.qx.api.product.IProductTypeService;
import com.qx.v9.entity.TProductType;
import com.qx.v9.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: QX_He
 * DATA: 2020/7/29-21:40
 * Description:
 **/

@Service
@Component
public class ProductTypeService extends IBaseServiceImpl<TProductType> implements IProductTypeService{

    // 要想注入成功，那么需要在启动类中添加 mybatis的扫描
    @Autowired
    private TProductTypeMapper tProductTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return tProductTypeMapper;
    }


}
