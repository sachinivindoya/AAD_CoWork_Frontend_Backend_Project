package lk.nexttravel.apigateway.util.securityCodes;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 31/10/2023
 * Time    : 01:06
 */
public class SecurityCodes {
    //API Gateway Token Details - For Frontend
    public static final String FRONTEND_APIGATEWAY_JWT_TOKEN_KEY = "FRONTENDTOKENEFGVBTHJNMYUJKEDFVBEDFGBTGHNEDFCWSDXQAZEDFCTGBHNJMYHNTGBFVEDCWSXFVFGVGBYHNTGBKJOLDFGHJRFGHJWSDCEDFVRFGBRFGBEDFV";
    public static final long FRONTEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY = 5 * 60 * 60 * 60;//5min

    public static final long FRONTEND_APIGATEWAY_REFRESH_TOKEN_KEY_VALIDITY = 5 * 60 * 60 * 60;//5min

    //API Gateway Token Details - For Backend
    public static final String BACKEND_APIGATEWAY_JWT_TOKEN_USERNAME = "API_GATEWAY_BACKEND";
    public static final String BACKEND_APIGATEWAY_JWT_TOKEN_KEY = "BACKENDTOKENERTYUIDFGHJKERTYUIDFGHHJHLHNKGMDHEYRJFGHFEHEWQYUIEORUDHGHSJDHNNCDHRIUEYIOIUWERTYUSDFGHJDFGHERTYUIDFGHJRTYUIDFGHJK";
    public static final long BACKEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY     = 5 * 60 * 60 *60; //5min
}
