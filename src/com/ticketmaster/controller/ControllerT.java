package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;

public interface ControllerT<T, Y>{
    public T run(Y y) throws InvalidActionException;
}