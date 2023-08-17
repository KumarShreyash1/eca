package com.goodhomes.authentication.service;

import com.goodhomes.authentication.models.exception.OnboardingUserGenericError;
import com.goodhomes.authentication.models.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    public static final String ONB_USER_1001 = "ONB_USER_1001";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("Error while fetching user.");
        OnboardingUserGenericError errorObject = new OnboardingUserGenericError();
        errorObject.setErrorCode(ONB_USER_1001);
        errorObject.setErrorMessage(ex.getMessage());
        return new ResponseEntity(errorObject, HttpStatus.BAD_REQUEST);
    }
}
