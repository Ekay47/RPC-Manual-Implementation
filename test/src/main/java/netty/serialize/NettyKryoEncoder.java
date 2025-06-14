package netty.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

// network transfer needs the bytes form, ByteBuf is the container provided by Netty
@AllArgsConstructor
public class NettyKryoEncoder extends MessageToByteEncoder<Object> {
    private final Serializer serializer;
    private final Class<?> genericClass;

    // transform the class into bytes and put into byteBuf
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (genericClass.isInstance(o)) {
            // transform class into bytes
            byte[] body = serializer.serialize(o);
            // get the body length
            int dataLength = body.length;
            // write the data length
            byteBuf.writeInt(dataLength);
            //write the bytes array into bytebuf
            byteBuf.writeBytes(body);
        }
    }
}
