package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Status;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollectorList;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;

class StatusSelectorController implements ControllerT<Status, Status>{

    private final ConsoleView statusSelectorView = new ConsoleView();


    @Override
    public Status run(Status status) throws InvalidActionException {
        statusSelectorView.addInputCollector(new InputCollectorList("Change Status (Or leave blank to return to the ticket) ", "Invalid option, please try again" , "", Status.getStatusStringList()));
        statusSelectorView.addPassiveComponents(new TextComponent("Current Status: " + status.name()));

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
