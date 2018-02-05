package com.lswq.model.behavior.chain.nettychain;

public interface OpenApiContextHandler extends OpenApiInboundInvoker, OpenApiOutboundInvoker {

    Request request();

    Response response();
}
