package com.ticketmaster.view.utils;

import com.ticketmaster.model.InvalidActionException;

public interface CallBackStringOperator {
    public void callback(String param) throws InvalidActionException;
}
