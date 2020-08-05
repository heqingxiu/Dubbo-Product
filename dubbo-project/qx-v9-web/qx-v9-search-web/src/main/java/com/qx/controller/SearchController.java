package com.qx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingxiu.utills.solrpageresult.SolrPageResult;
import com.qingxiu.utills.webresult.Result;
import com.qingxiu.utills.webresult.StatusCode;
import com.qx.api.search.IProductSearchService;
import com.qx.v9.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/8/4-15:08
 * Description:
 **/
@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private IProductSearchService iProductSearchService;

    /**
     * Push product messages which is from mysql data-base to solr-core witch name of my-core
     *
     * @return
     */
    @RequestMapping("/importAllData")
    @ResponseBody
    public Result<String> importAllData() {
        String result = iProductSearchService.importAllData();
        return new Result<String>(true, StatusCode.OK, "Messages were inserted successfully", result);
    }


    /**
     * Fuzzy search for product by key words (Front and rear separation)
     *
     * @param words
     * @return
     */
    @GetMapping("/words")
    @ResponseBody
    public Result<List<TProduct>> searchByKeyWords(@RequestParam("words") String words) {
        List<TProduct> tProductList = iProductSearchService.searchByKeyWords(words);
        return new Result<List<TProduct>>(true, StatusCode.OK, "Query done with success", tProductList);
    }

    /**
     * Fuzzy search for product by key words( Single Application )
     *
     * @param words
     * @return
     */
    @GetMapping("/keywords")
//    @ResponseBody
    public String singleSearchByKeyWords(@RequestParam("words") String words, Model model) {
        List<TProduct> tProductList = iProductSearchService.searchByKeyWords(words);
        model.addAttribute("tProductList", tProductList);
        return "list";
    }

    @GetMapping("/keywordsPageList/{pageIndex}/{pageSize}")
    public String singleSearchByKeyWordsPaging(@RequestParam("words") String words, Model model,
                                               @PathVariable Integer pageIndex,
                                               @PathVariable Integer pageSize) {
        SolrPageResult<TProduct> productSolrPageResult = iProductSearchService.searchByKeyWords(words, pageIndex, pageSize);
        productSolrPageResult.setNavigatePages(3);
        model.addAttribute("list", productSolrPageResult);
        return "pageList";
    }

}






