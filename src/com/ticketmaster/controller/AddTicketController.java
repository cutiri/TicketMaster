package com.ticketmaster.controller;

import com.ticketmaster.model.*;
import com.ticketmaster.model.db.LocationDB;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AddTicketController implements ControllerT<Ticket, User> {

    private final ConsoleView addTicketView = new ConsoleView();

    @Override
    public Ticket run(User user) throws InvalidActionException {

        addTicketView.addInputCollector(new RegexInputCollector("Title: ", "Title is mandatory", "\n", RegexSelector.NON_EMPTY_STRING.getRegex()));
        addTicketView.addInputCollector(new RegexInputCollector("Description: ", "Description is mandatory", "\n", RegexSelector.NON_EMPTY_STRING.getRegex()));
        addTicketView.addInputCollector(new ListInputCollector("Priority ", "Invalid option, try again", "", Priority.getPriorityStringList()));
        addTicketView.addInputCollector((new ListInputCollector("Location: ", "Invalid Location, please try again", "", LocationDB.locationNameList())));


        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {

            result = addTicketView.show();

            if (result == DialogResult.SUCCESS) {
                System.out.println(addTicketView.getUserInputs());
                String title = addTicketView.getUserInputs().get(0);
                String description = addTicketView.getUserInputs().get(1);
                Priority priority = Priority.valueOf(addTicketView.getUserInputs().get(2));
                Location location = LocationDB.locationList().stream().filter(loc -> loc.getName().equalsIgnoreCase(addTicketView.getUserInputs().get(3))).findFirst().orElse(null);

                return TicketFactory.createTicket(title, description, priority, location, LocalDateTime.parse("2023-09-20 14:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), user);
            }
        }

        return null;
    }
}