package com.lswq.search;

import com.lswq.search.dao.Medicine;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshaowei on 2017/3/30.
 */
public class ElasticSearchHandler {

    private Client client;


    public ElasticSearchHandler() throws UnknownHostException {
        //使用本机做为节点
        this("test.es.data.xxx.com");
    }

    public ElasticSearchHandler(String ipAddress) throws UnknownHostException {
        //集群连接超时设置
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "data_test_cluster")
                .put("client.transport.sniff", "true")
                .put("client.transport.ping_timeout", "10s")
                .build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), 9300));
    }


    /**
     * 建立索引,索引建立好之后,会在elasticsearch-0.20.6\data\elasticsearch\nodes\0创建所以你看
     *
     * @param indexName 为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
     * @param indexType Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
     * @param jsonData  json格式的数据集合
     * @return
     */
    public void createIndexResponse(String indexName, String indexType, List<String> jsonData) {
        //创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexName, indexType).setRefresh(true);
        for (int i = 0; i < jsonData.size(); i++) {
            requestBuilder.setSource(jsonData.get(i)).execute().actionGet();
        }

    }

    /**
     * 创建索引
     *
     * @param indexName
     * @param indexType
     * @param jsonData
     * @return
     */
    public IndexResponse createIndexResponse(String indexName, String indexType, String jsonData) {
        IndexResponse response = client.prepareIndex(indexName, indexType)
                .setSource(jsonData)
                .execute()
                .actionGet();
        return response;
    }

    /**
     * 查询相应index下面所有的记录
     * @param indexName
     * @return
     */
    public List<Medicine> searchIndexAll(String indexName) {
        SearchResponse response = client.prepareSearch(indexName).execute().actionGet();
        SearchHits hits = response.getHits();
        return processResult(hits);
    }

    /**
     * 执行搜索
     *
     * @param queryBuilder
     * @param indexName
     * @param indexType
     * @return
     */
    public List<Medicine> searcher(QueryBuilder queryBuilder, String indexName, String indexType) {

        SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(indexType)
                .setQuery(queryBuilder)
                .execute()
                .actionGet();
        SearchHits hits = searchResponse.getHits();
        return processResult(hits);
    }

    private List<Medicine> processResult(SearchHits hits) {
        List<Medicine> list = new ArrayList();
        System.out.println("查询到记录数=" + hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                Integer id = (Integer) hit.getSource().get("id");
                String name = (String) hit.getSource().get("name");
                String function = (String) hit.getSource().get("function");
                list.add(new Medicine(id, name, function));
            }
        }
        return list;
    }

}
