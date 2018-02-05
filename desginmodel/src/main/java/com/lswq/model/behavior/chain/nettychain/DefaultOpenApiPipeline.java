package com.lswq.model.behavior.chain.nettychain;

/**
 * 请求流
 *
 * @author zhangshaowei
 */
public class DefaultOpenApiPipeline implements OpenApiPipeline {

    final AbstractOpenApiContextHandler head;
    final AbstractOpenApiContextHandler tail;


    /**
     * 请求流信息
     */
    protected DefaultOpenApiPipeline() {

        tail = new TailContext(this);
        head = new HeadContext(this);

        head.next = tail;
        tail.prev = head;
    }

    private class TailContext extends AbstractOpenApiContextHandler {
        public TailContext(DefaultOpenApiPipeline openApiPipeline) {
            super(openApiPipeline, true, false);
        }
    }

    private class HeadContext extends AbstractOpenApiContextHandler {
        public HeadContext(DefaultOpenApiPipeline openApiPipeline) {
            super(openApiPipeline, false, true);
        }
    }
}
