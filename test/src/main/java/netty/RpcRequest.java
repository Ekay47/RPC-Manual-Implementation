package netty;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
