package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:09
 */
public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message){
        super(message);
    }
}
