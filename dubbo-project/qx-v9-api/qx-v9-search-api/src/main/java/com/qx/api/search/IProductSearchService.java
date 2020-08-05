package com.qx.api.search;

import com.qingxiu.utills.solrpageresult.SolrPageResult;
import com.qx.v9.entity.TProduct;

import java.util.List;

/**
 * Author: QX_He
 * DATA: 2020/8/4-14:14
 * Description:
 **/
public interface IProductSearchService {
    String importAllData();

    /**
     * non paging search
     *
     * @param words
     * @return
     */
    List<TProduct> searchByKeyWords(String words);

    /**
     * paging search
     *
     * @param words
     * @param pageIndex
     * @param pageSize
     * @return
     */
    SolrPageResult<TProduct> searchByKeyWords(String words, Integer pageIndex, Integer pageSize); //  reload this methods

    /**
     * Solr-data synchronization
     *
     * @param result
     */
    String synchronizedSolrDataIncrement(Long result);
}
