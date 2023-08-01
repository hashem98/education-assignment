package com.example.Education.exceptions;

import com.example.Education.dto.CODE;
import com.example.Education.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.AuthenticationException;

@Slf4j
@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<Response<Object>> exception(FileSizeLimitExceededException exception) {
        log.error("Error during upload media.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.BAD_REQUEST.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BodyGuardException.class)
    public ResponseEntity<Response<Object>> exception(BodyGuardException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.BAD_REQUEST.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response<Object>> exception(NotFoundException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.NOT_FOUND.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<Response<Object>> exception(AuthorizationException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.FORBIDDEN.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Response<Object>> exception(DataIntegrityViolationException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getCause().getMessage())
                .code(CODE.BAD_REQUEST.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Response<Object>> exception(AuthenticationException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.UNAUTHORIZED.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Response<Object>> exception(UserNotFoundException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.UNAUTHORIZED.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<Object>> exception(Exception exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder().message(exception.getMessage())
                .code(CODE.INTERNAL_SERVER_ERROR.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
