package com.ticketmaster.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.ticketmaster.model.Priority.MEDIUM;
import static org.junit.Assert.*;

public class TicketTest {

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
    public void close_shouldReturnTotalTimeOfAllComments_whenCalculateTotalTimeOfTheTicket() throws InvalidActionException {
        testTicket.addComment(new Comment("Checking printer", user.getLogin(), 10, LocalDateTime.now()));
        testTicket.addComment(new Comment("Replaced cable", user.getLogin(), 20, LocalDateTime.now()));
        testTicket.addComment(new Comment("Issue resolved", user.getLogin(), 1, LocalDateTime.now()));
        testTicket.close();

        //fix this
        System.out.println(testTicket);

        assertEquals(31, testTicket.getTotalTimeSpentInMinutes());
    }
}