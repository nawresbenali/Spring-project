package com.cursus.cursus.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author oussama othmani
 */

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

    @Getter
    private final Set<String> violations;

    @Getter
    private final String violationSource;

}