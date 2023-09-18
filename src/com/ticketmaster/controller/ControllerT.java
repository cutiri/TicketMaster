package com.ticketmaster.controller;

public interface ControllerT<T, Y>{
    public T run(Y y);
}