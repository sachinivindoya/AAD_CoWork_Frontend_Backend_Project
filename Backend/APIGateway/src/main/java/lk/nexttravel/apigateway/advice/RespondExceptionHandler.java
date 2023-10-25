package lk.nexttravel.apigateway.advice;

import lk.nexttravel.apigateway.advice.util.*;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.util.ResCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 13:39
 */
@ControllerAdvice
public class RespondExceptionHandler {

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<RespondDTO> exception (InvalidPasswordException invalidPasswordException){
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_NOT_AUTHORISED)
                        .res_mg(invalidPasswordException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.UNAUTHORIZED

        );
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<RespondDTO> exception (NotFoundException notFoundException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_NO_DATA_FOUND)
                        .res_mg(notFoundException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NullPointException.class)
    protected ResponseEntity<RespondDTO> exception (NullPointException nullPointException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_FAIL)
                        .res_mg(nullPointException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidDataEntryException.class)
    protected ResponseEntity<RespondDTO> exception (InvalidDataEntryException invalidDataEntryException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_DATA_INVALID)
                        .res_mg(invalidDataEntryException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<RespondDTO> exception (InternalServerException internalServerException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_SERVERSIDE_INTERNAL_FAIL)
                        .res_mg(internalServerException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<RespondDTO> exception (DuplicateException duplicateException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .res_code(ResCodes.Response_DUPLICATED)
                        .res_mg(duplicateException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.CONFLICT
        );
    }
}
