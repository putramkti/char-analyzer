package com.bootcamp19.analyzer.exception;

import com.bootcamp19.analyzer.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidation(MethodArgumentNotValidException exception){
        List<String> messages = new ArrayList<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            messages.add(fieldError.getDefaultMessage());
        }
        return build(HttpStatus.BAD_REQUEST, String.join(",", messages));
    }

    @ExceptionHandler(AnalysisNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNotFound(AnalysisNotFoundException exception){
        return new ResponseEntity<>(BaseResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistExeption.class)
    public ResponseEntity<BaseResponse<Object>> handleAlreadyExist(AlreadyExistExeption exception){
        return new ResponseEntity<>(BaseResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<BaseResponse<Object>> build(HttpStatus status, String message){
        return ResponseEntity.status(status).body(BaseResponse.error(message));
    }

}
