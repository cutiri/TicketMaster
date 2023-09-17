package com.ticketmaster.model;

class TroubleTicket extends Ticket{

    public TroubleTicket(String title, String description, Priority priority, Location location) {
        super(title, description, priority, location);
    }

    @Override
    public void close() {
        System.out.println("Closing Trouble Ticket");
    }
}
