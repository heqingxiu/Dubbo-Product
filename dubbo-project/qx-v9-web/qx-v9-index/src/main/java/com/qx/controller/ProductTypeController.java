package com.qx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qx.api.product.IProductTypeService;
import com.qx.v9.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/8/2-20:15
 * Description:
 **/
@Controller
@RequestMapping("/index")
public class ProductTypeController {

    @Reference
    private IProductTypeService iProductTypeService;

    /**
     * Single Application
     *
     * @param model
     * @return
     */
    @RequestMapping("/goodsShow")
    public String showGoodsToIndex(Model model) {
        List<TProductType> tProductTypes = iProductTypeService.selectAll();
        model.addAttribute("GoodsList", tProductTypes);
        return "index";
    }

    @RequestMapping("/list")
    public String frontRearSeparation() {
        return "front2rear-index";
    }

}
