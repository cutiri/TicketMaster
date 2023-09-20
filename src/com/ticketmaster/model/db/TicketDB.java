package com.ticketmaster.model.db;

import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.TicketFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ticketmaster.model.Priority.*;

public class TicketDB {
    static private final List<Ticket> ticketList = new ArrayList<>();

    public static List<Ticket> getTicketDB() {




        ticketList.addAll(List.of(
                TicketFactory.createTicket("Printer Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.jack),
                TicketFactory.createTicket("Scanner Issue", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2023, 7, 1, 13, 15, 44), UserDB.sam),
                TicketFactory.createTicket("TV Issue", "Printer jammed.", MEDIUM, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.jason),
                TicketFactory.createTicket("Cable Issue", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2023, 7, 2, 12, 15, 44), UserDB.michael),
                TicketFactory.createTicket("Phone Issue", "Printer jammed.", LOW, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 3, 15, 44), UserDB.jason),
                TicketFactory.createTicket("Email Issue", "Printer jammed.", HIGH, LocationDB.las2, LocalDateTime.of(2023, 7, 5, 13, 15, 46), UserDB.william),
                TicketFactory.createTicket("Permission Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.jack),
                TicketFactory.createTicket("Account Issue", "Printer jammed.", MEDIUM, LocationDB.las1, LocalDateTime.of(2023, 4, 2, 13, 15, 44), UserDB.jason),
                TicketFactory.createTicket("Password Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 8, 11, 18, 44), UserDB.jack),
                TicketFactory.createTicket("Screen Issue", "Printer jammed.", HIGH, LocationDB.las2, LocalDateTime.of(2023, 7, 2, 5, 15, 44), UserDB.peter),
                TicketFactory.createTicket("Printer Ink Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 8, 4, 13, 15, 44), UserDB.jack),
                TicketFactory.createTicket("Barcode Scanner Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.jason),
                TicketFactory.createTicket("Fax Issue", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2023, 7, 9, 13, 15, 34), UserDB.michael),
                TicketFactory.createTicket("Meeting Invite Issue", "Printer jammed.", LOW, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.jack),
                TicketFactory.createTicket("Audio Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 6, 11, 15, 44), UserDB.jason),
                TicketFactory.createTicket("Video Issue", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 5, 2, 13, 11, 44), UserDB.jason),
                TicketFactory.createTicket("New Printer Request", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 2, 16, 15, 44), UserDB.jack, UserDB.jason),
                TicketFactory.createTicket("New Office License Request", "Printer jammed.", HIGH, LocationDB.las1, LocalDateTime.of(2023, 7, 5, 4, 15, 44), UserDB.jack, UserDB.jason),
                TicketFactory.createTicket("New Laptop Request", "Printer jammed.", MEDIUM, LocationDB.las2, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.peter, UserDB.william),
                TicketFactory.createTicket("New Scanner Request", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2023, 7, 4, 19, 15, 44), UserDB.michael, UserDB.sam),
                TicketFactory.createTicket("New Monitor Request", "Printer jammed.", HIGH, LocationDB.las2, LocalDateTime.of(2023, 3, 2, 13, 15, 44), UserDB.jason, UserDB.jack),
                TicketFactory.createTicket("New Keyboard", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2023, 7, 2, 13, 15, 44), UserDB.sam, UserDB.michael),
                TicketFactory.createTicket("New Mouse", "Printer jammed.", HIGH, LocationDB.las3, LocalDateTime.of(2022, 4, 2, 11, 12, 41), UserDB.sam, UserDB.michael)
        ));
        return ticketList;
    }
}
