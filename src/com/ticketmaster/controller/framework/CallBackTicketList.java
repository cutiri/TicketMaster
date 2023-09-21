package com.ticketmaster.controller.framework;

import com.ticketmaster.model.Ticket;

import java.util.List;

/*
 * Functional interface with a single method that returns a List of Tickets
 * It is used in TicketQueueFilterController to create a map of options that will trigger different methods (lambdas)
 * An option will be selected y the user, and depending on the selection a filter (lambda) will be called and the corresponding
 * Ticket list will be returned
 */
public interface CallBackTicketList {
    public List<Ticket> callback();
}
