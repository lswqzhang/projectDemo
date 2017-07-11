package com.lswq.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangsw on 2017/4/8.
 */
public class ElasSearchHandlerTest {
    private Client client;

    private static String INDEX_NAME = "mart_finance.finance_busn_data_int_all";
    private static String INDEX_TYPE = "finance_busn_data_int_all";


    @Test
    public void timeTest() {
        long start = 1488297600000L;
        long end = 1488383999000L;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date(start);
        Date endDate = new Date(end);
        System.err.println("start date " + formatter.format(startDate));
        System.err.println("end date " + formatter.format(endDate));
    }

    @Before
    public void setUp() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "data_test_cluster")
                .put("client.transport.sniff", "true").build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("test.es.data.sankuai.com"), 9300));
    }



    @Test
    public void esSearchDemo() {

        int offSet = 0;
        int size = 10;


        QueryBuilder sourceSystem = QueryBuilders.termQuery("source_system", "DCMD");
        QueryBuilder business_type = QueryBuilders.termQuery("business_type", "MDTK-DP");
        QueryBuilder err_code = QueryBuilders.regexpQuery("err_code", "*AA*");
        QueryBuilder gt = QueryBuilders.rangeQuery("deliver_date").format("yyyyMMdd").lte("20170330").gte("20170330");

        List<QueryBuilder> queryBuilders = new ArrayList<>();
        queryBuilders.add(sourceSystem);
        queryBuilders.add(business_type);
        queryBuilders.add(gt);


        SearchResponse searcher = ElasticSearchHandler.searcher(client, queryBuilders, INDEX_NAME, INDEX_TYPE, offSet, size);
        long totalHits = searcher.getHits().getTotalHits();
        System.err.println("total hits is " + totalHits);
        SearchHits hits = searcher.getHits();
        console(ElasticSearchHandler.processResult(hits, String.class));

    }


    @Test
    public void esSearchMultiDemo() {


        QueryBuilder mustBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("source_system", "DCTG"))
                .must(QueryBuilders.termQuery("business_type", "TGFK"));

        RegexpQueryBuilder err_code = QueryBuilders.regexpQuery("err_code", "*AA*");

        RangeQueryBuilder gt = QueryBuilders.rangeQuery("deliver_date").format("yyyyMMdd").lte("20170331").gte("20170330");

        SearchHits hits = client.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE).setQuery(gt).execute().actionGet().getHits();


        console(ElasticSearchHandler.processResult(hits, String.class));


    }
    
    private void console(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.err.println(result.get(i));
        }
    }
}
