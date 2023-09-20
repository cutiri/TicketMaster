package com.ticketmaster.model.db;

import com.ticketmaster.model.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    private static List<Ticket> ticketList;

    public static void loadData() {
        ticketList = TicketDB.getTicketDB();
    }

    public static Ticket findTicketById(int ticketNumber) {
        return ticketList.stream()
                .filter(ticket -> ticket.getId() == ticketNumber)
                .findFirst()
                .orElse(null);
    }

    public static User authenticate(String login, String password){
        User user = UserDB.userList().stream().
                filter(
                        u -> u.getLogin().equals(login)).
                findFirst().orElse(null);

        if(user != null){
            return password.equals(user.getPassword()) ? user : null;
        }
        return user;
    }

    public static List<Ticket> allTickets(){
        return ticketList;
    }

    public static List<Ticket> findTicketsByLocation(String location) {
        return ticketList.stream()
                .filter(ticket -> ticket.getLocation().getName().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByAssignedTeam(String team) {
        return ticketList.stream()
                .filter(ticket -> ticket.getTeamAssigned().getName().equalsIgnoreCase(team))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByAssignedUser(String user) {
        return ticketList.stream()
                .filter(ticket -> ticket.getUserAssigned().getLogin().equalsIgnoreCase(user))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByAssignedUser(User user) {
        return ticketList.stream()
                .filter(ticket -> ticket.getUserAssigned().getLogin().equalsIgnoreCase(user.getLogin()))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByTicketCreator(String user) {
        return ticketList.stream()
                .filter(ticket -> ticket.getCreatedBy().getLogin().equalsIgnoreCase(user))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByPriority(String priority) {
        return ticketList.stream()
                .filter(ticket -> ticket.getPriority().name().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByStatus(String status) {
        return ticketList.stream()
                .filter(ticket -> ticket.getStatus().name().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findTicketsByStatus(Status status) {
        return ticketList.stream()
                .filter(ticket -> ticket.getStatus() == status)
                .collect(Collectors.toList());
    }

    public static List<Ticket> sortTicketsByCreationDateOldToNew() {
        return ticketList.stream()
                .sorted(Comparator.comparing(Ticket::getCreatedAt))
                .collect(Collectors.toList());
    }

    public static List<Ticket> sortTicketsByCreationDateNewToOld() {
        return ticketList.stream()
                .sorted(Comparator.comparing(Ticket::getCreatedAt, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Ticket> sortTicketsByCreationDateOldToNewFromTo(int from, int to) {
        if (from >= 0 && to <= ticketList.size()) {
            return sortTicketsByCreationDateOldToNew().subList(from-1, to-1);
        } else {
            throw new IllegalArgumentException("Out of range values");
        }
    }

    public static List<Ticket> sortTicketsByCreationDateNewToOldFromTo(int from, int to) {
        if (from >= 0 && to <= ticketList.size()) {
            return sortTicketsByCreationDateNewToOld().subList(from-1, to-1);
        } else {
            throw new IllegalArgumentException("Out of range values");
        }
    }

    public static List<Ticket> sortByPriorityLowToHigh() {
        // sort in the order enum declared
        return ticketList.stream()
                .sorted(Comparator.comparing(ticket -> ticket.getPriority().ordinal()))
                .collect(Collectors.toList());
    }

    public static List<Ticket> sortByPriorityHighToLow() {
        return ticketList.stream()
                .sorted(Comparator.comparing(ticket -> ticket.getPriority().ordinal(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Ticket> sortByPriorityLowToHighFromTo(int from, int to) {
        if (from >= 0 && to <= ticketList.size()) {
            return sortByPriorityLowToHigh().subList(from-1, to-1);
        } else {
            throw new IllegalArgumentException("Out of range values");
        }
    }

    public static List<Ticket> sortByPriorityHighToLowFromTo(int from, int to) {
        if (from >= 0 && to <= ticketList.size()) {
            return sortByPriorityHighToLow().subList(from-1, to-1);
        } else {
            throw new IllegalArgumentException("Out of range values");
        }
    }

    public static List<Ticket> findTroubleTickets() {
        return ticketList.stream()
                .filter(ticket -> ticket.getClass().equals(TroubleTicket.class))
                .collect(Collectors.toList());
    }

    public static List<Ticket> findRequests() {
        return ticketList.stream()
                .filter(ticket -> ticket.getClass().equals(Request.class))
                .collect(Collectors.toList());
    }

    public static void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }
}
