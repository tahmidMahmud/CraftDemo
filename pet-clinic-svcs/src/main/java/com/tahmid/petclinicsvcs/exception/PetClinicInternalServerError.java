package com.tahmid.petclinicsvcs.exception;

public class PetClinicInternalServerError extends RuntimeException {
    public PetClinicInternalServerError() {
        super();
    }

    public PetClinicInternalServerError(String message) {
        super(message);
    }
}
