package com.ticketmaster.controller.framework;

import com.ticketmaster.model.InvalidActionException;

public interface Controller<T, Y>{
    T run(Y y) throws InvalidActionException;
}
