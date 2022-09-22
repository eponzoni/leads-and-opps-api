package com.datacom.api;

import com.azure.cosmos.CosmosException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CosmosExceptionHandler {

    @ExceptionHandler(CosmosException.class)
    public ResponseEntity<String> handleCosmosException(CosmosException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode())).body(e.getMessage());
    }
}
