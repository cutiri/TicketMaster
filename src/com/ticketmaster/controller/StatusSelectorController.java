package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Status;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.view.components.*;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;

class StatusSelectorController implements ControllerT<Status, Ticket>{

    private final ConsoleView statusSelectorView = new ConsoleView();


    @Override
    public Status run(Ticket ticket) throws InvalidActionException {
        statusSelectorView.addInputCollector(new ListInputCollector("Change Status (Or leave blank to return to the ticket) ", "Invalid option, please try again" , "", Status.getStatusStringList()));
//        statusSelectorView.addPassiveComponents(new TextComponent("Current Status: " + ticket.getStatus().name()));
        statusSelectorView.addPassiveComponents(new MultiTextComponent(
                new ConsoleText("Current Priority: " ),
                new ConsoleText(ticket.getStatus().name(), ConsoleTextColor.GREEN)));

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {

            result = statusSelectorView.show();

            if (result == DialogResult.SUCCESS) {
                String input = statusSelectorView.getUserInputs().get(0);

                return Status.valueOf(input);
            }
        }

        return null;
    }
}
