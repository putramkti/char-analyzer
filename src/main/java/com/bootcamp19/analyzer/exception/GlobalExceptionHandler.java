package com.bootcamp19.analyzer.exception;

import com.bootcamp19.analyzer.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnalysisNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNotFound(AnalysisNotFoundException exception){
        return new ResponseEntity<>(BaseResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistExeption.class)
    public ResponseEntity<BaseResponse<Object>> handleAlreadyExist(AlreadyExistExeption exception){
        return new ResponseEntity<>(BaseResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
