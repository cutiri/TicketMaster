package com.ticketmaster.controller.db;

import com.ticketmaster.model.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/*
 * A container to all the application's data,
 * it collects all the data in the app and stores it in order for the app to be able to serialize it
 */
public final class AppDatabase implements Serializable {

    private List<User> users;
    private List<Team> teams;
    private List<Location> locations;
    private List<Ticket> tickets;

    public void updateLists(){
        users = UserDB.getList();
        teams = TeamDB.getList();
        locations = LocationDB.getList();
        tickets = TicketDB.getList();
    }

    public void initializeAppDatabase(){
        UserDB.setList(users);
        TeamDB.setList(teams);
        LocationDB.setList(locations);
        TicketDB.setList(tickets);
    }

    /*
     * this method will generate some demo data for our application
     * it gets called if loading the data from the hard drive fails
     */
    public void initializeAppDatabaseWithFakeData()
    {
        Location las1 = new Location("LAS1");
        Location las2 = new Location("LAS2");
        Location las3 = new Location("LAS3");

        LocationDB.setList(List.of(las1, las2, las3));

        Team las1Team = new Team("LAS1-OTS", las1);
        Team las2Team = new Team("LAS2-OTS", las2);
        Team las3Team = new Team("LAS3-OTS", las3);

        TeamDB.setList(List.of(las1Team, las2Team, las3Team));

        User jack = new User("jack", "password", "Jack Sparrow", las1Team);
        User jason = new User("jason", "password","Jason Momoa", las1Team);
        User peter = new User("peter", "password","Peter Jackson", las2Team);
        User william = new User("william", "password","William Defoe", las2Team);
        User michael = new User("michael", "password","Michael Scofield", las3Team);
        User sam = new User("samuel", "password","Samuel Jackson", las3Team);
        User dummy = new User("bob", "123","Bob Newhart", las3Team);

        UserDB.setList(List.of(jack, jason, peter, william, michael, sam, dummy));

        TicketDB.add(new TroubleTicket("Printer Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack));
        TicketDB.add(new TroubleTicket("Scanner Issue", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2023, 7, 1, 13, 15, 44), sam));
        TicketDB.add(new TroubleTicket("TV Issue", "Printer jammed.", Priority.MEDIUM, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jason));
        TicketDB.add(new TroubleTicket("Cable Issue", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2023, 7, 2, 12, 15, 44), michael));
        TicketDB.add(new TroubleTicket("Phone Issue", "Printer jammed.", Priority.LOW, las1, LocalDateTime.of(2023, 7, 2, 3, 15, 44), jason));
        TicketDB.add(new TroubleTicket("Email Issue", "Printer jammed.", Priority.HIGH, las2, LocalDateTime.of(2023, 7, 5, 13, 15, 46), william));
        TicketDB.add(new TroubleTicket("Permission Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack));
        TicketDB.add(new TroubleTicket("Account Issue", "Printer jammed.", Priority.MEDIUM, las1, LocalDateTime.of(2023, 4, 2, 13, 15, 44), jason));
        TicketDB.add(new TroubleTicket("Password Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 8, 11, 18, 44), jack));
        TicketDB.add(new TroubleTicket("Screen Issue", "Printer jammed.", Priority.HIGH, las2, LocalDateTime.of(2023, 7, 2, 5, 15, 44), peter));
        TicketDB.add(new TroubleTicket("Printer Ink Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 8, 4, 13, 15, 44), jack));
        TicketDB.add(new TroubleTicket("Barcode Scanner Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jason));
        TicketDB.add(new TroubleTicket("Fax Issue", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2023, 7, 9, 13, 15, 34), michael));
        TicketDB.add(new TroubleTicket("Meeting Invite Issue", "Printer jammed.", Priority.LOW, las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), jack));
        TicketDB.add(new TroubleTicket("Audio Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 6, 11, 15, 44), jason));
        TicketDB.add(new TroubleTicket("Video Issue", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 5, 2, 13, 11, 44), jason));
        TicketDB.add(new Request("New Printer Request", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 2, 16, 15, 44), jack, jason));
        TicketDB.add(new Request("New Office License Request", "Printer jammed.", Priority.HIGH, las1, LocalDateTime.of(2023, 7, 5, 4, 15, 44), jack, jason));
        TicketDB.add(new Request("New Laptop Request", "Printer jammed.", Priority.MEDIUM, las2, LocalDateTime.of(2023, 7, 2, 13, 15, 44), peter, william));
        TicketDB.add(new Request("New Scanner Request", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2023, 7, 4, 19, 15, 44), michael, sam));
        TicketDB.add(new Request("New Monitor Request", "Printer jammed.", Priority.HIGH, las2, LocalDateTime.of(2023, 3, 2, 13, 15, 44), jason, jack));
        TicketDB.add(new Request("New Keyboard", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2023, 7, 2, 13, 15, 44), sam, michael));
        TicketDB.add(new Request("New Mouse", "Printer jammed.", Priority.HIGH, las3, LocalDateTime.of(2022, 4, 2, 11, 12, 41), sam, michael));
    }
}
