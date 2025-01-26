package com.apps.backend.exceptions;

import com.apps.backend.dto.GenericResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionsHandler {

    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<GenericResponseDTO>
    handleAuthorityNotFoundException(AuthorityNotFoundException exc){
        return new ResponseEntity<>(new GenericResponseDTO(exc.getMessage(), 404), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GenericResponseDTO>
    handleUsernameNotFoundException(UsernameNotFoundException exc){
        return new ResponseEntity<>(new GenericResponseDTO(exc.getMessage(), 404), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<GenericResponseDTO>
    handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exc){
        return new ResponseEntity<>(new GenericResponseDTO(exc.getMessage(), 409), HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponseDTO>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){
        Map<String, String> errors = new HashMap<>();

        for(FieldError error : exc.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(new GenericResponseDTO(errors, 404), HttpStatusCode.valueOf(404));
    }
}
