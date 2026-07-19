package com.dss.loan_approval.config.security;

public class ApiPathConfig {
    private ApiPathConfig() {
    }

    public static final String[] OPEN_GENERAL_PATHS = {
            "/api/v1/public/**",
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",

            // Customer submission endpoints
            "/api/v1/customer-profile/**",
            "/api/v1/employment-details/**",
            "/api/v1/promotion-details/**",
            "/api/v1/loan-application/**"
    };

    public static final String[] OPEN_AUTH_PATHS = {
            "/api/v1/auth/**"
    };
}
