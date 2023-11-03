package lk.nexttravel.user_service.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 21:05
 */
public class NotfoundException extends RuntimeException{
    public NotfoundException (String message){
        super(message);
    }
}
