package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.db.LocationDB;
import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.*;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.ListInputCollector;
import com.ticketmaster.view.framework.RegexInputCollector;
import com.ticketmaster.view.framework.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * This controller will handle adding a Trouble Ticket
 * It's main method run accepts an user and returns a Ticket
 */
public class AddTicketController implements Controller<Ticket, User> {

    private final ConsoleView addTicketView = new ConsoleView();

    @Override
    public Ticket run(User user) throws InvalidActionException {

        addTicketView.addPassiveComponents(new TextComponent("New Ticket", ConsoleTextColor.GREEN));
        addTicketView.addInputCollector(new RegexInputCollector("Title: ", "Title is mandatory", "\n", RegexSelector.NON_EMPTY_STRING.getRegex()));
        addTicketView.addInputCollector(new RegexInputCollector("Description: ", "Description is mandatory", "\n", RegexSelector.NON_EMPTY_STRING.getRegex()));
        addTicketView.addInputCollector(new ListInputCollector("Priority: ", "Invalid option, try again", "", Priority.getPriorityStringList()));
        addTicketView.addInputCollector((new ListInputCollector("Location: ", "Invalid Location, please try again", "", LocationDB.locationNameList())));

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {

            result = addTicketView.show();

            if (result == DialogResult.SUCCESS) {
                String title = addTicketView.getUserInputs().get(0);
                String description = addTicketView.getUserInputs().get(1);
                Priority priority = Priority.valueOf(addTicketView.getUserInputs().get(2));
                Location location = LocationDB.getList().stream().filter(loc -> loc.getName().equalsIgnoreCase(addTicketView.getUserInputs().get(3))).findFirst().orElse(null);
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                return TicketFactory.createTicket(title, description, priority, location, LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), user);
            }
        }

        return null;
    }
}