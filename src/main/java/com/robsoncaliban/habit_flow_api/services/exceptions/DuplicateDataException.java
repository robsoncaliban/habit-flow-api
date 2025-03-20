package com.robsoncaliban.habit_flow_api.services.exceptions;

public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException(Object credencial) {
        super("A credencial: " +credencial+ " já existe");
    }
}
