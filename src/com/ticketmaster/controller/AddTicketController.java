package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;

class AddTicketController implements ControllerT<Ticket, User>{
    public AddTicketController(User user) {
    }

    @Override
    public Ticket run(User user) throws InvalidActionException {
        return null;
    }
}
