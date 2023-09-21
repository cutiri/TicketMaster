package com.ticketmaster.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import static com.ticketmaster.model.Priority.*;
import static com.ticketmaster.model.Status.*;

public class TroubleTicketTest {

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

    @Test(expected = InvalidActionException.class)
    public void close_shouldThrowInvalidActionException_whenCloseTicketWithoutComment() throws InvalidActionException {
        testTicket.updateStatus(RESOLVED);
    }

    @Test
    public void close_shouldReturnTicketStatusRESOLVED_whenCloseTicket() throws InvalidActionException {
        testTicket.addComment(new Comment("Fixed printer issue", user.getLogin(), 10, LocalDateTime.now()));
        testTicket.updateStatus(RESOLVED);

        assertEquals(RESOLVED, testTicket.getStatus());
    }
}