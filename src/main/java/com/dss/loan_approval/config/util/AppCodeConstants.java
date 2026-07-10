package com.dss.loan_approval.config.util;

public class AppCodeConstants {
    private AppCodeConstants (){
    }
    public static final Integer SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "success";

    public static final Integer FAILED_CODE = 400;
    public static final String FAILED_MSG = "failed";

    public static final Integer UNAUTHORIZED_CODE = 401;
    public static final String UNAUTHORIZED_MSG = "unauthorized";

    public static final Integer FORBIDDEN_CODE = 403;
    public static final String FORBIDDEN_MSG = "forbidden";

    public static final Integer NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MSG = "not found";

    public static final Integer CONFLICT_CODE = 409;
    public static final String CONFLICT_MSG = "conflict";

    public static final Integer SERVER_ERROR_CODE = 500;
    public static final String SERVER_ERROR_MSG = "server error";

}
