package lk.nexttravel.user_service.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 17:05
 */
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
