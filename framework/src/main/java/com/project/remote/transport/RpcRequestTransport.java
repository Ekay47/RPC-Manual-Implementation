package com.project.remote.transport;

import com.project.extensions.SPI;
import com.project.remote.dto.RpcRequest;

@SPI
public interface RpcRequestTransport {
    Object sendRpcRequest(RpcRequest rpcRequest);
}
