package com.ticketmaster.model;


// unchecked exception to throw it when there is an action that is not permitted such as adding a duplicate user, close request without approval.
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
