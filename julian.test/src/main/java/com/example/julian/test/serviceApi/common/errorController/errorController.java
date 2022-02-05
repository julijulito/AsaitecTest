package com.example.julian.test.serviceApi.common.errorController;

import com.example.julian.test.serviceApi.common.entities.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class errorController {

    private static final String NOT_FOUND_MESSAGE = "Requested Resource Could Not Be Found";

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseBody requestHandlerResourceNotFound() {

        return new ErrorResponseBody(
                NOT_FOUND_MESSAGE,
                HttpStatus.NOT_FOUND,
                offsetDateTime()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseBody requestBodyError(MethodArgumentNotValidException e) {

        return new ErrorResponseBody(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                offsetDateTime()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseBody internalServerError(Exception e) {

        return new ErrorResponseBody(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                offsetDateTime()
        );
    }

    private OffsetDateTime offsetDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        OffsetDateTime time = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        return time;
    }

}
