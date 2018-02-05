package com.lswq.model.behavior.chan.link;

public interface OpenApiContextHandler extends OpenApiInboundInvoker, OpenApiOutboundInvoker {

    Request request();

    Response response();
}
