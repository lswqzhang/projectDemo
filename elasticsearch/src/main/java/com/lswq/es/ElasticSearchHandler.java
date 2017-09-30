package com.lswq.es;

import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsw on 2017/4/8.
 */
public class ElasticSearchHandler {
    /**
     * 执行搜索
     *
     * @param client        ES客户端
     * @param queryBuilder  创建查询对象
     * @param indexName     查询的索引
     * @param indexType     查询的类型
     * @return
     */
    public static <T> List<T> searcher(Client client, QueryBuilder queryBuilder, String indexName, String indexType, Class<T> clazz) {
        SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(indexType)
                .setQuery(queryBuilder)
                .execute()
                .actionGet();
        SearchHits hits = searchResponse.getHits();
        return processResult(hits, clazz);
    }


    /**
     * 执行搜索
     *
     * @param client        ES客户端
     * @param queryBuilders  创建查询对象
     * @param indexName     查询的索引
     * @param indexType     查询的类型
     * @return
     */
    public static SearchResponse searcher(Client client, List<QueryBuilder> queryBuilders, String indexName, String indexType, int offSet, int size) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        for (QueryBuilder queryBuilder: queryBuilders) {
            builder.must(queryBuilder);
        }
        SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(indexType)
                .setSearchType(SearchType.DFS_QUERY_AND_FETCH)
                .setQuery(builder)
                .setFrom(offSet)
                .setSize(size)
                .execute()
                .actionGet();
        return searchResponse;
    }

    public static <T> List<T> processResult(SearchHits hits, Class<T> clazz) {
        List<T> list = new ArrayList();
        System.out.println("查询到记录数=" + hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                Gson gson = new Gson();
                list.add(gson.fromJson(hit.getSourceAsString(), clazz));
            }
        }
        return list;
    }
}
