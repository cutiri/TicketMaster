package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Priority;
import com.ticketmaster.model.Status;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollectorList;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.DialogResult;

class StatusSelectorController implements ControllerT<Status, Status>{

    private final ConsoleView consoleView = new ConsoleView();


    @Override
    public Status run(Status status) throws InvalidActionException {
        consoleView.addPassiveComponents(new TextComponent("Current Status: " + status));
        consoleView.addInputCollector(new InputCollectorList("Change Status to " + Status.getAllStatus() + ": ", "Invalid Status, try again", "", Status.getStatusStringList()));

        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = consoleView.show();

            if (result == DialogResult.SUCCESS) {
                String input = consoleView.getUserInputs().get(0);

                return Status.valueOf(input);
            }
        }

        return null;
    }
}
