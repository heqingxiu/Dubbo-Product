package com.qx.api.product;

import com.qingxiu.common.service.IBaseService;
import com.qx.api.vo.ProductVO;
import com.qx.v9.entity.TProduct;

/**
 * Author: QX_He
 * DATA: 2020/7/30-15:09
 * Description:
 **/
public interface IProductService extends IBaseService<TProduct> {
    Long add(ProductVO productVO);
}
