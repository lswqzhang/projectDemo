package com.lswq.search;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by zhangshaowei on 2017/5/15.
 */
public class ZhidaoMapping {
    public static XContentBuilder getMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject()

                        //开启倒计时功能
                        .startObject("_ttl")
                            .field("enabled", false)
                        .endObject()

                        .startObject("properties")

                            .startObject("title")
                                .field("type", "string")
                            .endObject()
                            .startObject("question")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("answer")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("category")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("author")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("date")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("answer_author")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("answer_date")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("description")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("keywords")
                                .field("type", "string")
                                .field("index", "not_analyzed")
                            .endObject()
                            .startObject("read_count")
                                .field("type", "integer")
                                .field("index", "not_analyzed")
                            .endObject()
                            //关联数据
                            .startObject("list")
                                .field("type", "object")
                            .endObject()

                        .endObject()

                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapping;
    }

    private static final String INDEX = "finance.test.indices";
    private static final String OLD_ALIASES_INDEX = "finance.indices";
    private static final String FIRST_ALIASES_INDEX = "finance.indices_v1";
    private static final String SECOND_ALIASES_INDEX = "finance.indices_v2";
    private static final String TYPE = "finance_test";

    private ElasticSearchHandler esHandler;
    private static Logger log = LoggerFactory.getLogger(ZhidaoMapping.class);

    @Before
    public void setUp() {
        try {
            esHandler = new ElasticSearchHandler();
            log.debug("初始化测试类");
        } catch (UnknownHostException e) {
            log.error("初始化失败：", e);
        }
    }

    @Test
    public void createBangMapping(){
        //构建一个Index（索引）
        CreateIndexRequest request = new CreateIndexRequest(INDEX);

        esHandler.getClient().admin().indices().create(request);
        esHandler.getClient().admin().indices().prepareAliases()
                .removeAlias(INDEX, OLD_ALIASES_INDEX)
                .addAlias(INDEX, FIRST_ALIASES_INDEX)
                .addAlias(INDEX, SECOND_ALIASES_INDEX)
                .execute().actionGet();



        PutMappingRequest mapping = Requests.putMappingRequest(INDEX).type(TYPE).source(ZhidaoMapping.getMapping());

        log.info("{}", mapping.source());


        esHandler.getClient().admin().indices().putMapping(mapping).actionGet();
    }



}
