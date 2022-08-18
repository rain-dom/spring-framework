package com.dzp.springframework.netty.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;

    public static void main(String[] args) {
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());

    }

}