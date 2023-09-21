package com.ticketmaster.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.ticketmaster.model.Priority.*;
import static com.ticketmaster.model.Status.*;
import static org.junit.Assert.*;

public class RequestTest {
    Ticket testTicket;
    Location location;
    Team team;
    User user;

    private void instantiateUserTeamLocation() throws InvalidActionException {
        location = new Location("ABC1");
        team = new Team("ABC1-IT", location);
        user = new User("john", "password", team);
        team.addMember(user);
        location.setSupportTeam(team);
    }

    @Before
    public void setUp() throws InvalidActionException {
        instantiateUserTeamLocation();
        testTicket = TicketFactory.createTicket("Printer Issue", "Printer jammed.", MEDIUM, location, LocalDateTime.now(), user);
    }

    @Test
    public void close_shouldReturnTicketStatusRESOLVED_whenCloseTicketAfterApproval() throws InvalidActionException {
        Request requestTestTicket = (Request) testTicket;
        requestTestTicket.setApproved(true);
        testTicket.updateStatus(RESOLVED);

        assertEquals(RESOLVED, requestTestTicket.getStatus());
    }

    @Test(expected = InvalidActionException.class)
    public void close_shouldThrowInvalidActionException_whenCloseTicketWithoutApproval() throws InvalidActionException {
        Request requestTestTicket = (Request) testTicket;
        requestTestTicket.updateStatus(RESOLVED);
    }

}