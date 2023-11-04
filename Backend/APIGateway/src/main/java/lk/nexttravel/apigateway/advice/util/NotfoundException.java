package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:29
 */
public class NotfoundException extends RuntimeException{
    public NotfoundException(String message){
        super(message);
    }
}
