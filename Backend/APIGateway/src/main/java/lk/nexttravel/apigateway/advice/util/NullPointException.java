package lk.nexttravel.apigateway.advice.util;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:05
 */
public class NullPointException extends RuntimeException{
    public NullPointException(String message){
        super(message);
    }
}
