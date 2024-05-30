package com.example.springboottest.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.validation.BindException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// http://localhost:8080/swagger-ui/index.html 에서 확인가능

@Log4j2
@RestControllerAdvice
public class CustomRestAdvice {
    @ExceptionHandler(BindException.class) // BindException 예외 처리
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)  // HTTP 상태 코드 417
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) { // BindException 처리
        log.error(e);

        Map<String, String> errorsMap = new HashMap<>(); // 에러 메시지를 담을 Map

        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> { // 필드 에러를 순회하며
                errorsMap.put(fieldError.getField(), fieldError.getCode()); // 에러 메시지를 Map에 담음
            });
        }

        return ResponseEntity.badRequest().body(errorsMap); // 400 Bad Request와 에러 메시지를 반환
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // DataIntegrityViolationException 예외 처리
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)  // HTTP 상태 코드 417
    public ResponseEntity<Map<String, String>> handleFKException(BindException e) { // BindException 처리
        log.error(e);

        Map<String, String> errorsMap = new HashMap<>(); // 에러 메시지를 담을 Map

        errorsMap.put("time", "" + System.currentTimeMillis());
        errorsMap.put("msg", "constraint fails");

        return ResponseEntity.badRequest().body(errorsMap); // 400 Bad Request와 에러 메시지를 반환
    }

    @ExceptionHandler(NoSuchElementException.class) // NoSuchElementException 예외 처리
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)  // HTTP 상태 코드 417
    public ResponseEntity<Map<String, String>> handlerNoSuchElementException(BindException e) { // BindException 처리
        log.error(e);

        Map<String, String> errorsMap = new HashMap<>(); // 에러 메시지를 담을 Map

        errorsMap.put("time", "" + System.currentTimeMillis());
        errorsMap.put("msg", "No Such Element Exception");

        return ResponseEntity.badRequest().body(errorsMap); // 400 Bad Request와 에러 메시지를 반환
    }
}