package com.ticketmaster.controller.framework;

import com.ticketmaster.model.InvalidActionException;

/*
 * A generic interface with a single method run
 * The purpose of this class is to allow Controllers to receive something (Y) and return something else (T)
 * For example a Controller modifying a ticket should receive that ticket as a parameter and it could return it back
 */
public interface Controller<T, Y>{
    T run(Y y) throws InvalidActionException;
}
