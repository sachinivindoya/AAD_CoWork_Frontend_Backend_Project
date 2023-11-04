package lk.nexttravel.apigateway.advice;

import lk.nexttravel.apigateway.advice.util.*;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.util.RespondCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:25
 */
@ControllerAdvice
public class RespondExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<RespondDTO> exception(DuplicateException exception) {
//        System.out.println("DuplicateException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Respond_DATA_DUPLICATED,exception.getMessage(), null,null)
                , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<RespondDTO> exception(InternalServerException exception) {
//        System.out.println("InternalServerException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL,exception.getMessage(), null,null)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<RespondDTO> exception(InvalidInputException exception) {
//        System.out.println("InvalidInputException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Respond_DATA_INVALID,exception.getMessage(), null,null)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotfoundException.class)
    protected ResponseEntity<RespondDTO> exception(NotfoundException notfoundException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .rspd_code(RespondCodes.Respond_NO_DATA_FOUND)
                        .rspd_code(notfoundException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    protected ResponseEntity<RespondDTO> exception(PasswordNotMatchException passwordNotMatchException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .rspd_code(RespondCodes.Respond_NOT_AUTHORISED)
                        .repd_msg(passwordNotMatchException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UnauthorizeException.class)
    protected ResponseEntity<RespondDTO> exception(UnauthorizeException unauthorizeException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .rspd_code(RespondCodes.Respond_NOT_AUTHORISED)
                        .repd_msg(unauthorizeException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
