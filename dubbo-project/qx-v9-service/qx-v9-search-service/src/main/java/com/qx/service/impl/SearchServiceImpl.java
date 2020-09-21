package com.qx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingxiu.utills.solrpageresult.SolrPageResult;
import com.qingxiu.utills.webresult.Result;
import com.qx.api.search.IProductSearchService;
import com.qx.v9.entity.TProduct;
import com.qx.v9.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: QX_He
 * DATA: 2020/8/4-14:26
 * Description:
 **/

@Service
@Component
public class SearchServiceImpl implements IProductSearchService {

    @Autowired
    private TProductMapper tProductMapper;

    @Autowired
    private SolrClient solrClient;

    /**
     * 首次全批加载
     * @return
     */
    @Override
    public String importAllData() {
        // 1. Query all the product messages
        List<TProduct> tProducts = tProductMapper.selectAll();
        // 2. Create a Documents of solr and push all data to solr-core by iterator
        for (TProduct product : tProducts) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.setField("id", product.getId());
            solrInputDocument.setField("name", product.getName());
            solrInputDocument.setField("sale_point", product.getSalePoint());
            solrInputDocument.setField("sale_price", product.getSalePrice());
            solrInputDocument.setField("images", product.getImages());
            try {
                solrClient.add(solrInputDocument);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return "Failed to add data to solr-core";
            }
        }
        try {
            solrClient.commit();
//            solrClient.close(); // Don`t active this
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return "Failed to add data to solr-core";
        }
        return "All data were process successfully";
    }


    /**
     * 增量加载
     *
     * @param result
     */
    @Override
    public String synchronizedSolrDataIncrement(Long result) {
        // Obtain specific product message from sql data bases by primary key
        TProduct tProduct = tProductMapper.selectByPrimaryKey(result);
        //
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.setField("id", tProduct.getId());
        solrInputDocument.setField("name", tProduct.getName());
        solrInputDocument.setField("sale_point", tProduct.getSalePoint());
        solrInputDocument.setField("sale_price", tProduct.getSalePrice());
        solrInputDocument.setField("images", tProduct.getImages());
        try {
            solrClient.add(solrInputDocument);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return "solr-data synchronization fail";
        }
        return "solr-data synchronization success";
    }


    /**
     * 通过关键字进行模糊搜索
     *
     * @param words
     * @return
     */
    @Override
    public List<TProduct> searchByKeyWords(String words) {
        //1. Get data from solr core by key-words-search
        SolrQuery query = new SolrQuery();
        //appendix.1: Set highlight messages up
        query.setHighlight(true); // launch the highlight function
        query.addHighlightField("name"); // which one need to be highlighted
        query.addHighlightField("sale_point");
        query.setHighlightSimplePre("<font color='red'>"); // pre format
        query.setHighlightSimplePost("</font>");    //post format

        if (!StringUtils.isAllEmpty(words)) {
            query.setQuery("product_keywords:" + words);
        } else {
            // Default key-words
            query.setQuery("product_keywords" + "刘德华");
        }
        //2. Convert data to List-type
        List<TProduct> tProductList = null;
        try {
            QueryResponse responseResult = solrClient.query(query);
            SolrDocumentList resultsList = responseResult.getResults();
            tProductList = new ArrayList<>(resultsList.size());
            //appendix.2: Get highlight data with highlighting
            Map<String, Map<String, List<String>>> highlighting = responseResult.getHighlighting();
            //3. Packaging list-type data to TProduct with iterator
            for (SolrDocument entries : resultsList) {
                TProduct tProduct = new TProduct();
                tProduct.setImages(entries.getFieldValue("images").toString());  // The property of indexed need to be set true
                tProduct.setSalePrice(Long.parseLong(entries.getFieldValue("sale_price").toString()));
                tProduct.setId(Long.parseLong(entries.getFieldValue("id").toString()));
                //appendix.3: Get commodity`s highlight messages
                Map<String, List<String>> map = highlighting.get(entries.getFieldValue("id").toString());
                List<String> name = map.get("name");
                List<String> sale_point = map.get("sale_point");
                if (name != null && name.size() > 0) {      //  it will be greater then 0 if it is null，so we need to estimate this value whether is equal null
                    tProduct.setName(name.toString());
                } else {
                    tProduct.setName(entries.getFieldValue("name").toString());
                }
                if (sale_point != null && sale_point.size() > 0) {
                    tProduct.setSalePoint(sale_point.toString());
                } else {
                    tProduct.setSalePoint(entries.getFieldValue("sale_point").toString());
                }

                //Use a.add(object) to add data to list-set .rather then use for(..){ trp[i++]}
                tProductList.add(tProduct);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return null;
        }
        return tProductList;
    }

    /**
     * 分页搜索
     *
     * @param words
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public SolrPageResult<TProduct> searchByKeyWords(String words, Integer pageIndex, Integer pageSize) {

        SolrQuery query = new SolrQuery();
        query.setHighlight(true); // launch the highlight function
        query.addHighlightField("name"); // which one need to be highlighted
        query.addHighlightField("sale_point");
        query.setHighlightSimplePre("<font color='red'>"); // pre format
        query.setHighlightSimplePost("</font>");    //post format
        // Use to hold the paging data
        SolrPageResult<TProduct> productSolrPageResult = new SolrPageResult<TProduct>();
        // Set paging configuration
        query.setStart((pageIndex - 1) * pageSize);
        query.setRows(pageSize);
        Long totalCount = 0L;

        if (!StringUtils.isAllEmpty(words)) {
            query.setQuery("product_keywords:" + words);
        } else {
            // Default key-words
            query.setQuery("product_keywords" + "刘德华");
        }
        //2. Convert data to List-type
        List<TProduct> tProductList = null;
        try {
            QueryResponse responseResult = solrClient.query(query);
            SolrDocumentList resultsList = responseResult.getResults();
            tProductList = new ArrayList<>(resultsList.size());
            //Get total Count
            totalCount = resultsList.getNumFound();
            //appendix.2: Get highlight data with highlighting
            Map<String, Map<String, List<String>>> highlighting = responseResult.getHighlighting();
            //3. Packaging list-type data to TProduct with iterator
            for (SolrDocument entries : resultsList) {
                TProduct tProduct = new TProduct();
                tProduct.setImages(entries.getFieldValue("images").toString());  // The property of indexed need to be set true
                tProduct.setSalePrice(Long.parseLong(entries.getFieldValue("sale_price").toString()));
                tProduct.setId(Long.parseLong(entries.getFieldValue("id").toString()));
                //appendix.3: Get commodity`s highlight messages
                Map<String, List<String>> map = highlighting.get(entries.getFieldValue("id").toString());
                List<String> name = map.get("name");
                List<String> sale_point = map.get("sale_point");
                if (name != null && name.size() > 0) {      //  it will be greater then 0 if it is null，so we need to estimate this value whether is equal null
                    tProduct.setName(name.toString());
                } else {
                    tProduct.setName(entries.getFieldValue("name").toString());
                }
                if (sale_point != null && sale_point.size() > 0) {
                    tProduct.setSalePoint(sale_point.toString());
                } else {
                    tProduct.setSalePoint(entries.getFieldValue("sale_point").toString());
                }

                //Use a.add(object) to add data to list-set .rather then use for(..){ trp[i++]}
                tProductList.add(tProduct);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return null;
        }
        // packaging result aggregate
        productSolrPageResult.setPageNum(pageIndex);
        productSolrPageResult.setPageSize(pageSize);
        productSolrPageResult.setList(tProductList);
        productSolrPageResult.setTotal(totalCount);
        //Set total page quantity
        productSolrPageResult.setPages((int) (totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1));
        return productSolrPageResult;
    }

}
