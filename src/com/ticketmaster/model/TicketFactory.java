package com.ticketmaster.model;

import java.time.LocalDateTime;

class TicketFactory {

    // create TroubleTicket Instance
    public static Ticket createTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt) {
        return new TroubleTicket(title, description, priority, location, createdAt);
    }

    // create Request Instance
    public static Ticket createTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User approver) {
        return new Request(title, description, priority, location, createdAt, approver);
    }
}
