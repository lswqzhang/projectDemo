package com.lswq.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhangsw on 2017/4/12.
 */
public class ESJoinSearch {

    private static Logger log = LoggerFactory.getLogger(ESJoinSearch.class);
    private Client client;
    String indexName = "my_index";
    String indexType = "my_parent";

    @Before
    public void setUp() throws UnknownHostException {
        //集群连接超时设置
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "lswq_elastic")
                .put("client.transport.sniff", "true")
                .put("client.transport.ping_timeout", "10s")
                .build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.15.168"), 9300));
    }

    @Test
    public void joinSearch() {

        QueryBuilder text = QueryBuilders.matchQuery("text", "child document");

        QueryBuilder must = QueryBuilders.boolQuery().must(text);
        
        QueryBuilder childQuery = QueryBuilders.hasChildQuery("my_child", must);

        log.info("\n{}",must.toString());

        SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(indexType).setQuery(childQuery)
                .execute().actionGet();
        SearchHit[] hits = searchResponse.getHits().hits();
        for (SearchHit hit : hits) {
            System.err.println("结果为：" + hit.toString());
        }
    }
}
