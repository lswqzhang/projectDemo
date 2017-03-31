package com.lswq.search;

import com.lswq.search.dao.Medicine;
import com.lswq.search.factory.DataFactory;
import com.lswq.search.utils.JsonUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by zhangshaowei on 2017/3/30.
 */
public class ElasticSearchHandlerTest {


    private static Logger log = LoggerFactory.getLogger(ElasticSearchHandlerTest.class);
    private ElasticSearchHandler esHandler;
    String indexName = "index_demo";
    String indexType = "index_type_demo";

    @Before
    public void setUp() {
        try {
            esHandler = new ElasticSearchHandler();
            log.debug("初始化测试类");
        } catch (UnknownHostException e) {
            log.error("初始化失败：", e);
        }
    }

    /**
     * 创建多个数据到es
     */
    @Test
    public void createIndexListDataTest() {
        List<String> jsonData = DataFactory.getInitJsonData();
        esHandler.createIndexResponse(indexName, indexType, jsonData);
    }

    /**
     * 创建单条数据至es
     */
    @Test
    public void createIndexDataTest() {
        String jsonData  = JsonUtil.obj2JsonData(new Medicine(4,"dingxiaoshuai","My name is dingxiaoshuai。"));
        esHandler.createIndexResponse(indexName, indexType, jsonData);
    }

    /**
     * 查询index下面所有的数据
     */
    @Test
    public void searchIndexAllData() {
        List<Medicine> medicines = esHandler.searchIndexAll(indexName);
        console(medicines);
    }

    /**
     * boolean中must查询
     */
    @Test
    public void searchESThoughtIdTest() {
        //查询条件
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("id", 7));
        List<Medicine> result = esHandler.searcher(queryBuilder, indexName, indexType);
        console(result);
    }

    /**
     * boolean中must not查询
     */
    @Test
    public void searchNonESThoughtIdTest() {
        //查询条件
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery("id", 1));
        List<Medicine> result = esHandler.searcher(queryBuilder, indexName, indexType);
        console(result);
    }


    /**
     * 查询内容中包含特定字符的记录
     */
    @Test
    public void searchESStringInContent() {
        QueryBuilder query = QueryBuilders.queryStringQuery("利咽").queryName("function");
        List<Medicine> searcher = esHandler.searcher(query, indexName, indexType);
        console(searcher);
    }

    /**
     * 前缀索引查询
     */
    @Test
    public void searchPrefixQuery() {
        QueryBuilder queryBuilder = QueryBuilders.prefixQuery("function", "My");
        List<Medicine> searcher = esHandler.searcher(queryBuilder, indexName, indexType);
        console(searcher);
    }

    /**
     * 查询某个字段不为null
     * existsQuery的参数为字段名
     */
    @Test
    public void searchExistsQuery() {
        QueryBuilder queryBuilder = QueryBuilders.existsQuery("function");
        List<Medicine> searcher = esHandler.searcher(queryBuilder, indexName, indexType);
        console(searcher);
    }

    /**
     * 通配符查询，支持* 任意字符串；？任意一个字符
     */
    @Test
    public void searchWildcardQuery() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("function", "M*");
        List<Medicine> searcher = esHandler.searcher(queryBuilder, indexName, indexType);
        console(searcher);
    }


    /**
     * 通配符查询，支持* 任意字符串；？任意一个字符
     */
    @Test
    public void searchFuzzyQuery() {
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("function", "My*");
        List<Medicine> searcher = esHandler.searcher(queryBuilder, indexName, indexType);
        console(searcher);
    }

    private void console(List<Medicine> result) {
        for (int i = 0; i < result.size(); i++) {
            Medicine medicine = result.get(i);
            System.out.println("(" + medicine.getId() + ")药品名称:" + medicine.getName() + "\t\t" + medicine.getFunction());
        }
    }
}
