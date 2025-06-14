package com.project.remote.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RpcRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 8402028368858092013L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    private String getRpcServiceName(){
        return this.getInterfaceName() + this.getMethodName() + this.getVersion();
    }
}
