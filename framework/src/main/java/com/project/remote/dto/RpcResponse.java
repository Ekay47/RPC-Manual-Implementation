package com.project.remote.dto;

import com.project.enums.RpcResponseEnums;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RpcResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 5966559301872758469L;
    private String requestId;
    private Integer code;
    private String message;
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcResponseEnums.SUCCESS.getCode());
        response.setMessage(RpcResponseEnums.SUCCESS.getMessage());
        response.setRequestId(requestId);
        if(data != null){
            response.setData(data);
        }
        return response;
    }

    public static <T> RpcResponse<T> fail(RpcResponseEnums code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
