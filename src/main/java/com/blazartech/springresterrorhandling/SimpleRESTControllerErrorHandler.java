/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.springresterrorhandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author aar1069
 */
@ControllerAdvice
public class SimpleRESTControllerErrorHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleIllegalArgument(Exception ex, WebRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = request.getDescription(false).replaceAll("uri=", "");
        
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setPath(url);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String bodyOfResponse;
        
        try {
            bodyOfResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            bodyOfResponse = "unknown error";
        }
        
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
