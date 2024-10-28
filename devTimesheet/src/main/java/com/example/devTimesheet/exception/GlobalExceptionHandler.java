package com.example.devTimesheet.exception;

import com.example.devTimesheet.dto.respon.ApiRespon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiRespon> handlerRuntimeException(RuntimeException exception){
//        log.error("Exception: ", exception);
//        ApiRespon apiRespon = new ApiRespon();
//        apiRespon.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiRespon.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(apiRespon);
//    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespon> handlerAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiRespon apiRespon = new ApiRespon();
        apiRespon.setCode(errorCode.getCode());
        apiRespon.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiRespon);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespon> handlerValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try{
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e){

        }
        ApiRespon apiRespon = new ApiRespon();
        apiRespon.setCode(errorCode.getCode());
        apiRespon.setMessage(errorCode.getMessage());
        return  ResponseEntity.badRequest().body(apiRespon);
    }
}
