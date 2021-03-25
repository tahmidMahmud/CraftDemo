package com.tahmid.petclinicsvcs.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PetClinicNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            PetClinicNotFoundException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetClinicInternalServerError.class)
    protected ResponseEntity<Object> handleInternalServerError(
            PetClinicInternalServerError ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PetClinicVisitConflictException.class)
    protected ResponseEntity<Object> handleEntityConflict(
            PetClinicVisitConflictException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }
}
