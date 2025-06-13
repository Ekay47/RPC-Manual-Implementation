package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseEnums {
    SUCCESS(200, "Remote Call is Successful!"),
    FAIL(500, "Remote Call is Failed!");

    private final int code;
    private final String message;
}
