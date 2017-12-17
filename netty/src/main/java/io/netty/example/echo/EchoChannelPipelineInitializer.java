package io.netty.example.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;


public class EchoChannelPipelineInitializer extends ChannelInitializer<SocketChannel> {

    static final boolean SSL = System.getProperty("ssl") != null;
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {

        // Configure SSL.
        final SslContext sslCtx;
        
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new LoggingHandler(LogLevel.INFO));
        p.addLast(new EchoServerHandler());
    }
}
