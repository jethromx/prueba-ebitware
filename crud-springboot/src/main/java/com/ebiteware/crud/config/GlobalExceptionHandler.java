package com.ebiteware.crud.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.ebiteware.crud.exceptions.CustomException;

// This class is a global exception handler for the application.
// It can be used to handle exceptions thrown by controllers and provide a unified response format.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(Calendar.getInstance().getTime());
        error.put("timestamp", s);
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

      @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(Calendar.getInstance().getTime());
        error.put("timestamp", s);
        error.put("error", ex.getMessage());
        error.put("errorCode", "000");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(Calendar.getInstance().getTime());
        error.put("timestamp", s);
        error.put("error", ex.getMessage());
        error.put("errorCode", "000");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

   

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NoResourceFoundException ex) {
        Map<String, String> error = new HashMap<>();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(Calendar.getInstance().getTime());
        error.put("timestamp", s);
        error.put("error", ex.getMessage());
        error.put("errorCode", "000");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    
    // This method handles custom exceptions thrown by the application.
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(CustomException ex) {
        Map<String, String> error = new HashMap<>();
       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(ex.getTimestamp());
        error.put("timestamp", s);
        error.put("error", ex.getMessage());
        error.put("errorCode", ex.getErrorCode());

        if(ex.getErrorCode().equals("001")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
}
