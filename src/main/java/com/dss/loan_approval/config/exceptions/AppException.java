package com.dss.loan_approval.config.exceptions;


import com.dss.loan_approval.config.util.BaseApiResponse;

public class AppException extends RuntimeException {

    private BaseApiResponse apiResponse;
    public AppException(BaseApiResponse apiResponse) { super(); this.apiResponse = apiResponse; }
    public BaseApiResponse getApiResponse() {
        return apiResponse;
    }
}
