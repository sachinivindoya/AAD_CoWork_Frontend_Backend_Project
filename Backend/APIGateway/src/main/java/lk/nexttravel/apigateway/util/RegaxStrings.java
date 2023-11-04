package lk.nexttravel.apigateway.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 09:13
 */
public class RegaxStrings {
    public static final String NameRegax = "^[a-zA-Z0-9_.-]{5,30}$";
    public static final String AddressRegax  = "^\\S+\\s*[a-zA-Z0-9,.-]+\\S{0,48}$";
    public static final String NICRegax = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
    public static final String TellRegax = "^(?:7|0|(?:\\+94))[0-9]{9,10}$";
    public static final String DOBRegax  = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String TextRegax = "^[a-zA-Z0-9, ]+$";
    public static final String OnlyNumberRegax = "^(?=.*[0-9]).*$";
    public static final String GenderRegax  = "^(male|female|trans)$";
    public static final String NameWithInitialRegax = "^[A-Za-z. ]+$";
    public static final String EmailRegax = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PasswordRegax  = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
}
