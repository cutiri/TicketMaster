package com.ticketmaster.model.db;

import com.ticketmaster.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ticketmaster.model.Priority.*;
import static com.ticketmaster.model.Status.*;

public class Database {

    private static final List<User> userList = new ArrayList<>();
    private static final List<Team> teamList = new ArrayList<>();
    private static final List<Location> locationList = new ArrayList<>();
    private static final List<Ticket> ticketList = new ArrayList<>();


    private Database() {

    }

    public static Ticket findTicketById(int ticketNumber) {
        return ticketList.stream()
                .filter(ticket -> ticket.getId() == ticketNumber)
                .findFirst()
                .orElse(null);
    }

    private void addLocations() {

    }

    public static void loadData() throws InvalidActionException {
        Location las1 = new Location("LAS1");
        Location las2 = new Location("LAS2");
        Location las3 = new Location("LAS3");

        locationList.add(las1);
        locationList.add(las2);
        locationList.add(las3);

        Team las1Team = new Team("LAS1-OTS", las1);
        Team las2Team = new Team("LAS2-OTS", las2);
        Team las3Team = new Team("LAS3-OTS", las3);

        teamList.add(las1Team);
        teamList.add(las2Team);
        teamList.add(las3Team);

        User jack = new User("jack", "jack_password", las1Team);
        User jason = new User("jason", "jason_password", las1Team);
        User peter = new User("peter", "peter_password", las2Team);
        User william = new User("william", "william_password", las2Team);
        User michael = new User("michael", "michael_password", las3Team);
        User sam = new User("samuel", "samuel_password", las3Team);
        User dummy = new User("a", "a", las3Team);

        userList.addAll(List.of(jack, jason, peter, william, michael, sam, dummy));

        ticketList.addAll(List.of(
                TicketFactory.createTicket("Printer Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack),
                TicketFactory.createTicket("Scanner Issue", "Printer jammed.", HIGH, las3, LocalDateTime.of(2023, 7, 1, 13, 15, 44), sam),
                TicketFactory.createTicket("TV Issue", "Printer jammed.", MEDIUM, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jason),
                TicketFactory.createTicket("Cable Issue", "Printer jammed.", HIGH, las3, LocalDateTime.of(2023, 7, 2, 12, 15, 44), michael),
                TicketFactory.createTicket("Phone Issue", "Printer jammed.", LOW, las1, LocalDateTime.of(2023, 7, 2, 3, 15, 44), jason),
                TicketFactory.createTicket("Email Issue", "Printer jammed.", HIGH, las2, LocalDateTime.of(2023, 7, 5, 13, 15, 46), william),
                TicketFactory.createTicket("Permission Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack),
                TicketFactory.createTicket("Account Issue", "Printer jammed.", MEDIUM, las1, LocalDateTime.of(2023, 4, 2, 13, 15, 44), jason),
                TicketFactory.createTicket("Password Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 8, 11, 18, 44), jack),
                TicketFactory.createTicket("Screen Issue", "Printer jammed.", HIGH, las2, LocalDateTime.of(2023, 7, 2, 5, 15, 44), peter),
                TicketFactory.createTicket("Printer Ink Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 8, 4, 13, 15, 44), jack),
                TicketFactory.createTicket("Barcode Scanner Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jason),
                TicketFactory.createTicket("Fax Issue", "Printer jammed.", HIGH, las3, LocalDateTime.of(2023, 7, 9, 13, 15, 34), michael),
                TicketFactory.createTicket("Meeting Invite Issue", "Printer jammed.", LOW, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack),
                TicketFactory.createTicket("Audio Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 6, 11, 15, 44), jason),
                TicketFactory.createTicket("Video Issue", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 5, 2, 13, 11, 44), jason),
                TicketFactory.createTicket("New Printer Request", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 2, 16, 15, 44), jack, jason),
                TicketFactory.createTicket("New Office License Request", "Printer jammed.", HIGH, las1, LocalDateTime.of(2023, 7, 5, 4, 15, 44), jack, jason),
                TicketFactory.createTicket("New Laptop Request", "Printer jammed.", MEDIUM, las2, LocalDateTime.of(2023, 7, 2, 13, 15, 44), peter, william),
                TicketFactory.createTicket("New Scanner Request", "Printer jammed.", HIGH, las3, LocalDateTime.of(2023, 7, 4, 19, 15, 44), michael, sam),
                TicketFactory.createTicket("New Monitor Request", "Printer jammed.", HIGH, las2, LocalDateTime.of(2023, 3, 2, 13, 15, 44), jason, jack),
                TicketFactory.createTicket("New Keyboard", "Printer jammed.", HIGH, las3, LocalDateTime.of(2023, 7, 2, 13, 15, 44), sam, michael),
                TicketFactory.createTicket("New Mouse", "Printer jammed.", HIGH, las3, LocalDateTime.of(2022, 4, 2, 11, 12, 41), sam, michael)
        ));

        // update ticket status to RESOLVED
        ticketList.get(0).setStatus(RESOLVED);

    }

    public static User authenticate(String login, String password){
        User user = userList.stream().
                filter(
                u -> u.getLogin().equals(login)).
                findFirst().orElse(null);

        if(user != null){
            return password.equals(user.getPassword()) ? user : null;
        }
        return user;
    }

    public static List<Ticket> allTickets(){
        return Collections.unmodifiableList(ticketList);
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
