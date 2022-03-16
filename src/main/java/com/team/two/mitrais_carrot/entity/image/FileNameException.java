package com.team.two.mitrais_carrot.entity.image;

public class FileNameException extends RuntimeException {
    public FileNameException(String message) {
        super(message);
    }

    public FileNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
