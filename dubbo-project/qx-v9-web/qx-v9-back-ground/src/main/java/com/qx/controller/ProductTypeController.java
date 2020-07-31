package com.qx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qx.api.product.IProductTypeService;

import com.qx.v9.entity.TProductType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/30-0:46
 * Description:
 **/
@RestController
@CrossOrigin
@RequestMapping("/get")
public class ProductTypeController {

    @Reference
    private IProductTypeService iProductTypeService;

    @RequestMapping("/product")
    public List<TProductType> getProductTypeById() {
        List<TProductType> tProductTypes = (List<TProductType>) iProductTypeService.selectAll();
        System.out.println(tProductTypes);
        return tProductTypes;
    }
}
