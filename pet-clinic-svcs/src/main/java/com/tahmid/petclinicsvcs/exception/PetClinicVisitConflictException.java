package com.tahmid.petclinicsvcs.exception;

public class PetClinicVisitConflictException extends RuntimeException {
    public PetClinicVisitConflictException() {
        super();
    }

    public PetClinicVisitConflictException(String message) {
        super(message);
    }
}
