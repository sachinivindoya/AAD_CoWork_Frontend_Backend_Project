package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:26
 */
public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
