package lk.nexttravel.apigateway.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 09:15
 */
public class RespondCodes {
    public static final String Respond_SUCCESS = "00";
    public static final String Respond_NO_DATA_FOUND        = "01";
    public static final String Respond_NOT_AUTHORISED = "02";
    public static final String Respond_TOKEN_EXPIRED = "03";
    public static final String Respond_TOKEN_INVALID = "04";
    public static final String Respond_ERROR = "05";

    public static final String Respond_THIS_USER_ALREADY_REGISTERED = "06";
    public static final String Respond_THIS_USER_NOT_REGISTERED_YET = "07";

    public static final String Respond_FAIL = "10";
    public static final String Respond_SERVERSIDE_INTERNAL_FAIL = "11";
    public static final String Respond_DATA_INVALID = "12";
    public static final String Respond_AUTHORISED = "13";

    public static final String Respond_DATA_SAVED = "14";

    public static final String Respond_INTERNAL_SERVER_FAIL = "15";

    public static final String Respond_DATA_DUPLICATED = "16";

    public static final String Respond_PASSWORD_MATCHED = "17";
    public static final String Respond_PASSWORD_NOT_MATCHED = "18";

    public static final String Respond_USERNAME_AND_OTP_VERIFIED = "19";

    public static final String Respond_NEW_PASSWORD_CREATED_AND_LOGIN_SUCCEED = "20";

    public static final String PENDING = "PENDING";
    public static final String COMMITED = "COMMITED";

}
