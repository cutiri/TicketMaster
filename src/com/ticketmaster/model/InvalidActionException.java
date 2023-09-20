package com.ticketmaster.model;

public class InvalidActionException extends RuntimeException{

    public InvalidActionException() {
    }

    public InvalidActionException(String message) {
        super(message);
    }

    public InvalidActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidActionException(Throwable cause) {
        super(cause);
    }
}
