package com.ticketmaster.model.db;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Priority;
import com.ticketmaster.model.Ticket;
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
        Database.loadData();
    }

    @Test
    public void allTickets_shouldReturnAllTickets() {
        for (Ticket ticket: Database.allTickets()) {
            System.out.println(ticket);
        }
    }

    @Test
    public void findTicketByLocation_shouldReturnListOfFilteredTicketsByLocation_whenFilterTicketsByLocation() {
        List<Ticket> result = Database.findTicketsByLocation("LAS1");

        assertEquals(13, result.size());

        boolean locationVerify = result.stream()
                .allMatch(ticket -> "LAS1".equalsIgnoreCase(ticket.getLocation().getName()));

        assertTrue(locationVerify);
    }

    @Test
    public void findTicketsByTeam_shouldReturnListOfFilteredTicketsByAssignedTeam_whenFilterTicketsByAssignedTeam() {
        List<Ticket> result = Database.findTicketsByAssignedTeam("LAS1-OTS");

        assertEquals(13, result.size());

        boolean assignedTeamVerify = result.stream()
                .allMatch(ticket -> "LAS1-OTS".equalsIgnoreCase(ticket.getTeamAssigned().getName()));

        assertTrue(assignedTeamVerify);
    }

    @Test
    public void findTicketsByAssignedUser_shouldReturnListOfFilteredTicketsByAssignedUser_whenFilterTicketsByAssignedUser() {
        List<Ticket> result = Database.findTicketsByAssignedUser("peter");

        assertEquals(4, result.size());

        boolean assignedUserVerify = result.stream()
                .allMatch(ticket -> "peter".equalsIgnoreCase(ticket.getUserAssigned().getLogin()));

        assertTrue(assignedUserVerify);
    }

    @Test
    public void findTicketsByTicketCreator_shouldReturnListOfFilteredTicketsByTicketCreator_whenFilterTicketsByTicketCreator() {
        List<Ticket> result = Database.findTicketsByTicketCreator("william");

        printHelper(result);

        assertEquals(2, result.size());

        boolean createdByUserVerify = result.stream()
                .allMatch(ticket -> "william".equalsIgnoreCase(ticket.getCreatedBy().getLogin()));

        assertTrue(createdByUserVerify);

    }

    @Test
    public void findTicketsByPriority_shouldReturnListOfFilteredTicketsByPriority_whenFilterTicketsByPriority() {
        List<Ticket> result = Database.findTicketsByPriority("low");

        assertEquals(2, result.size());

        boolean createdByUserVerify = result.stream()
                .allMatch(ticket -> "low".equalsIgnoreCase(ticket.getPriority().name()));

        assertTrue(createdByUserVerify);
    }

    @Test
    public void findTicketsByStatus_shouldReturnListOfFilteredTicketsByStatus_whenFilterTicketsByStatus() {
        List<Ticket> result = Database.findTicketsByStatus("resolved");

        assertEquals(1, result.size());

        boolean createdByUserVerify = result.stream()
                .allMatch(ticket -> "resolved".equalsIgnoreCase(ticket.getStatus().name()));

        assertTrue(createdByUserVerify);
    }

    @Test
    public void sortTicketsByDate() {
        List<Ticket> result = Database.sortTicketsByDate();

        printHelper(result);
    }

}