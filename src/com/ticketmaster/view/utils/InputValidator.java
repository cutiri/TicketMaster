package com.ticketmaster.view.utils;

/*
 * Functional interface, it receives a String (user input), and returns if the input is valid or not
 */
public interface InputValidator {
    public boolean validate(String param);
}
