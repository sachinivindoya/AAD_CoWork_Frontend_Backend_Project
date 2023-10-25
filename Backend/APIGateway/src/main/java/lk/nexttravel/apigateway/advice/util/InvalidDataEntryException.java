package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:01
 */
public class InvalidDataEntryException extends RuntimeException{
    public InvalidDataEntryException(String message){
        super(message);
    }
}
