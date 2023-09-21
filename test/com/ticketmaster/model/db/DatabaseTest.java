package com.ticketmaster.model.db;

import com.ticketmaster.controller.db.TicketDB;
import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Request;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.TroubleTicket;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private void printHelper(List<Ticket> tickets) {
        for (Ticket ticket: tickets) {
            System.out.println(ticket);
        }
    }


    @Before
    public void setUp() throws InvalidActionException {
        AppIO.readS().initializeAppDatabase();
    }

    @Test
    public void allTickets_shouldReturnAllTickets() {
        for (Ticket ticket: TicketDB.getList()) {
            System.out.println(ticket);
        }
    }

    @Test
    public void findTicketsByAssignedUser_shouldReturnListOfFilteredTicketsByAssignedUser_whenFilterTicketsByAssignedUser() {
        List<Ticket> result = TicketDB.findTicketsByAssignedUser("peter");

        assertEquals(4, result.size());

        boolean assignedUserVerify = result.stream()
                .allMatch(ticket -> "peter".equalsIgnoreCase(ticket.getUserAssigned().getLogin()));

        assertTrue(assignedUserVerify);
    }



    @Test
    public void findTicketsByStatus_shouldReturnListOfFilteredTicketsByStatus_whenFilterTicketsByStatus() {
        List<Ticket> result = TicketDB.findTicketsByStatus("resolved");

        assertEquals(1, result.size());

        boolean createdByUserVerify = result.stream()
                .allMatch(ticket -> "resolved".equalsIgnoreCase(ticket.getStatus().name()));

        assertTrue(createdByUserVerify);
    }
}