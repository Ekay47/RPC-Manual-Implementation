package netty;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class RpcResponse {
    private String message;
}
