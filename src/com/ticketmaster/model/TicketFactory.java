package com.ticketmaster.model;

import java.time.LocalDateTime;

public class TicketFactory {

    // create TroubleTicket Instance
    public static Ticket createTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User createdBy) {
        return new TroubleTicket(title, description, priority, location, createdAt, createdBy);
    }

    // create Request Instance
    public static Ticket createTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User approver, User createdBy) {
        return new Request(title, description, priority, location, createdAt, approver, createdBy);
    }
}
