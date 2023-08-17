package com.goodhomes.authentication.models.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OnboardingUserGenericError {
    private String errorCode;
    private String errorMessage;
}
