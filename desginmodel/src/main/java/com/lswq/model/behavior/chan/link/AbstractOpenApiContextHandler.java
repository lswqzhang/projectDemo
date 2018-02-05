package com.lswq.model.behavior.chan.link;

public abstract class AbstractOpenApiContextHandler implements OpenApiContextHandler {


    volatile AbstractOpenApiContextHandler next;
    volatile AbstractOpenApiContextHandler prev;


    private final boolean inbound;
    private final boolean outbound;

    private DefaultOpenApiPipeline pipeline;

    AbstractOpenApiContextHandler(DefaultOpenApiPipeline pipeline, boolean inbound, boolean outbound) {
        this.pipeline = pipeline;
        this.inbound = inbound;
        this.outbound = outbound;
    }


    private AbstractOpenApiContextHandler findContextInbound() {
        AbstractOpenApiContextHandler ctx = this;
        do {
            ctx = ctx.next;
        } while (!ctx.inbound);
        return ctx;
    }

    private AbstractOpenApiContextHandler findContextOutbound() {
        AbstractOpenApiContextHandler ctx = this;
        do {
            ctx = ctx.prev;
        } while (!ctx.outbound);
        return ctx;
    }






    @Override
    public Request request() {
        return null;
    }

    @Override
    public Response response() {
        return null;
    }
}
