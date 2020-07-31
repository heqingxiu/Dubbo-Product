package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingxiu.common.dao.IBaseDao;
import com.qingxiu.common.service.impl.IBaseServiceImpl;
import com.qx.api.product.IProductService;
import com.qx.api.vo.ProductVO;
import com.qx.v9.entity.TProduct;
import com.qx.v9.entity.TProductDesc;
import com.qx.v9.mapper.TProductDescMapper;
import com.qx.v9.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/30-15:10
 * Description:
 **/
@Component
@Service
public class ProductServiceImpl extends IBaseServiceImpl<TProduct> implements IProductService {
    @Autowired
    private TProductMapper tProductMapper;

    @Autowired
    private TProductDescMapper tProductDescMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return tProductMapper;
    }

    /**
     * Unconditional page (Need to be implemented on provider service-end)
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<TProduct> tProductList = tProductMapper.selectAll();
        PageInfo<TProduct> pageInfo = new PageInfo<TProduct>(tProductList, 3);
        return pageInfo;
    }

    /**
     * Add product information from VO object,so we need to extract product corresponding information
     *
     * @param productVO
     * @return
     */
    @Override
    public Long add(ProductVO productVO) {
        // We need to decide whether to add or modify , but we ignore that here.

        //Get TProduct information
        TProduct tProduct = productVO.gettProduct();
        // Supplement general information
        tProduct.setCreateTime(new Date());
        tProduct.setCreateUser((long) 1);
        tProduct.setFlag(true);
        tProduct.setIsDelete(false);
        tProduct.setUpdateTime(new Date());
        tProduct.setUpdateUser((long) 1);
        tProductMapper.insertSelective(tProduct);
        //Get ProductDesc information
        TProductDesc tProductDesc = new TProductDesc();
        tProductDesc.setProductId(productVO.gettProduct().getId());
        tProductDesc.setpDesc(productVO.getProductDesc());
        //Insert new data to databases form mysql databases
        tProductDescMapper.insertSelective(tProductDesc);

        return tProduct.getId();
    }

}
