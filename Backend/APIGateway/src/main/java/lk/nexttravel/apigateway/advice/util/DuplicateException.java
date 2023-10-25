package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 13:46
 */
public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
