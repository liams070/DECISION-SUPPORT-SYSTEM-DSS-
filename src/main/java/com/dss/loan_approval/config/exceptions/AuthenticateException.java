package com.dss.loan_approval.config.exceptions;

import com.dss.loan_approval.config.util.BaseApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)

public class AuthenticateException extends RuntimeException{
    private BaseApiResponse apiResponse;
    public AuthenticateException(BaseApiResponse apiResponse) { super(); this.apiResponse = apiResponse; }
    public BaseApiResponse getApiResponse() {
        return apiResponse;
    }
}
