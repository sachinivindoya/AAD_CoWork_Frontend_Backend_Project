package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:11
 */
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
