package com.qx;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Author: QX_He
 * DATA: 2020/8/4-1:09
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrTestApplication {

    @Autowired
    private SolrClient solrClient;

    /**
     * Add data to solr-core
     *
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void addDataToSolr() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "2000");
        document.setField("name", "周杰伦演唱会，仅有一次，不容错过");
        document.setField("sale_price", "5000");
        document.setField("sale_point", "歌fd神");
        document.setField("images", "No yet");
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }

    /**
     * Query data from solr-core
     *
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void queryTest() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        QueryResponse queryResponse = solrClient.query(query);
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("name"));
            System.out.println(result.get("sale_price"));
            System.out.println(result.get("sale_point"));
        }

    }

    /**
     * Delete data from solr-core
     */
    @Test
    public void deleteDataFromSolr() throws IOException, SolrServerException {
//        for (int i = 0; i < 500; i++) {
//            solrClient.deleteById(String.valueOf(i));
//            solrClient.commit();
//        }
        solrClient.deleteById("2000");
        solrClient.commit();
        System.out.println("Delete success");
    }


    @Test
    public void nullPointerTest() {
        String a = null;
        if (a != null) {
            System.out.println("ok");
        } else {
            System.out.println("failed");
        }

    }

}
