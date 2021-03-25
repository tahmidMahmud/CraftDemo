package com.tahmid.petclinicsvcs.exception;

public class PetClinicNotFoundException extends RuntimeException {
    public PetClinicNotFoundException() {
        super();
    }

    public PetClinicNotFoundException(String message) {
        super(message);
    }
}
