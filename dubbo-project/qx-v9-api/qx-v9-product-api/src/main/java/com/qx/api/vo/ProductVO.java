package com.qx.api.vo;

import com.qx.v9.entity.TProduct;

import java.io.Serializable;

/**
 * Author: QX_He
 * DATA: 2020/7/31-1:31
 * Description:
 **/
public class ProductVO implements Serializable {
    private TProduct tProduct;
    private String ProductDesc;

    public TProduct gettProduct() {
        return tProduct;
    }

    public void settProduct(TProduct tProduct) {
        this.tProduct = tProduct;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "tProduct=" + tProduct +
                ", ProductDesc='" + ProductDesc + '\'' +
                '}';
    }
}
