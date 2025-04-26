package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import netty.handle.NettyClientHandler;
import netty.serialize.KryoSerializer;
import netty.serialize.NettyKryoDecoder;
import netty.serialize.NettyKryoEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private final String host;
    private final int port;
    private static final Bootstrap bs;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //initialization resources like EventLoopGroup, Bootstrap
    static {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bs = new Bootstrap();
        KryoSerializer serializer = new KryoSerializer();
        bs.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    // self-defined serialize en-decoder
                    @Override
                    protected void initChannel(SocketChannel socketChannel){
                        // bytebuf into Respeonse
                        socketChannel.pipeline().addLast(new NettyKryoDecoder(serializer, RpcResponse.class));
                        // request into bytebuf
                        socketChannel.pipeline().addLast(new NettyKryoEncoder(serializer, RpcRequest.class));
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
    }

    public RpcResponse sendMessage(RpcRequest request){
        try{
            ChannelFuture future = bs.connect(host, port).sync();
            logger.info("Client connected to {}:{}", host, port);
            Channel futureChannel = future.channel();
            logger.info("Send Message...");
            if (futureChannel != null){
                futureChannel.writeAndFlush(request).addListener(future1 -> {
                    if (future1.isSuccess()){
                        logger.info("Client send message success: [{}]", request.toString());
                    }else {
                        logger.info("Client send message failed.");
                    }
                });
                // I/O Blocked until channel closed
                futureChannel.closeFuture().sync();
                // get the data from server out of the Attribute
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                return futureChannel.attr(key).get();
            }
        }catch (InterruptedException e){
            logger.error("Exception when connect to " + host + ": " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        RpcRequest request = RpcRequest.builder().interfaceName("interface00")
                .methodName("helloMethod00").build();
        NettyClient client = new NettyClient("127.0.0.1", 9999);
        for(int i=1; i<=3; i++){
            client.sendMessage(request);
        }
        RpcResponse response = client.sendMessage(request);
        System.out.println(response.toString());
    }
}
