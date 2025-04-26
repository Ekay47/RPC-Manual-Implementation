package netty.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class NettyKryoDecoder extends ByteToMessageDecoder {
    private final Serializer serializer;
    private final Class<?> genericClass;
    // message length transferred by Netty is same as Serialized array length
    private static final int BODY_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list){
        if (byteBuf.readableBytes() >= BODY_LENGTH){
            // mark the readIndex position
            byteBuf.markReaderIndex();
            // read massage length
            int dataLength = byteBuf.readInt();
            // check unavailable situation
            if(dataLength < 0 || byteBuf.readableBytes() < 0){
                log.error("Data length or ByteBuf ReadableBytes is invalid...");
                return;
            }
            // check the message is whether completed
            if(byteBuf.readableBytes() < dataLength){
                byteBuf.resetReaderIndex();
                return;
            }
            // start deserialization
            byte[] body = new byte[dataLength];
            byteBuf.readBytes(body);
            // deserialization
            Object object = serializer.deserialize(body, genericClass);
            list.add(object);
            log.info("Successfully deserialized data...");
        }
    }
}
