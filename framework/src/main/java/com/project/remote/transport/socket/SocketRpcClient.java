package com.project.remote.transport.socket;

import com.project.extensions.ExtensionLoader;
import com.project.registry.ServiceDiscovery;
import com.project.remote.dto.RpcRequest;
import com.project.remote.transport.RpcRequestTransport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient(){
        this.serviceDiscovery = ExtensionLoader.
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        return null;
    }
}
