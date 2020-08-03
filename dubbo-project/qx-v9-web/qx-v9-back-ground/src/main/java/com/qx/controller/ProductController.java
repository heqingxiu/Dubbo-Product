package com.qx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import com.qx.api.product.IProductService;
import com.qx.api.vo.ProductVO;
import com.qx.v9.entity.TProduct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/7/30-14:35
 * Description:
 **/
@Controller
@RequestMapping("/product")
@CrossOrigin

public class ProductController {

    @Reference
    private IProductService iProductService;

    /**
     * List all product
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String getProductList(Model model) {
        List<TProduct> tProducts = iProductService.selectAll();
        model.addAttribute("list", tProducts);
        return "product/list";
    }

    /**
     * Unconditional page display
     *
     * @param model
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/pagelist/{pageIndex}/{pageSize}")
    public String getProductListByPage(Model model,
                                       @PathVariable("pageIndex") Integer pageIndex,
                                       @PathVariable("pageSize") Integer pageSize) {
        PageInfo<TProduct> pageInfo = iProductService.page(pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "/product/pagelist";
    }

    /**
     * Add product data
     * We usually push a package data to service layer,rather than parse it in here.
     *
     * @param productVO
     * @return
     */
    @PostMapping("/add")    // Table submit require a Post-method
    public String addProductData(ProductVO productVO) {
        // Return commodity id after insert data success
        Long result = iProductService.add(productVO);
        // Redirect to home page after add a commodity success.
        return "redirect:/product/pagelist/1/1";
    }

}
