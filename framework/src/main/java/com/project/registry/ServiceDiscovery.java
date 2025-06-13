package com.project.registry;

import com.project.extensions.SPI;
import com.project.remote.dto.RpcRequest;

import java.net.InetSocketAddress;

@SPI
public interface ServiceDiscovery {
    InetSocketAddress lookupService(RpcRequest request);
}
