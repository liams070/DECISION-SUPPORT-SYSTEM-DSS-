package com.dss.loan_approval.config.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseApiResponse<V> {
    private Integer code;
    private String status;
    private String msg;
    private V data;

    public BaseApiResponse(Integer code, String status, String msg){
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

}
