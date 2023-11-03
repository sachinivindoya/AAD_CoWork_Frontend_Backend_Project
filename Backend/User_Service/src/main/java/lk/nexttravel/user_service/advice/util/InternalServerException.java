package lk.nexttravel.user_service.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 20:53
 */
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }

}
