package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Priority;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollectorList;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.DialogResult;

class PrioritySelectorController implements ControllerT<Priority, Priority> {

    private final ConsoleView consoleView = new ConsoleView();


    @Override
    public Priority run(Priority priority) throws InvalidActionException {
        consoleView.addInputCollector(new InputCollectorList("Change Priority (Or leave blank to return to the ticket) ", "Invalid option, try again", "", Priority.getPriorityStringList()));
        consoleView.addPassiveComponents(new TextComponent("Current Priority: " + priority.name()));

        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = consoleView.show();

            if (result == DialogResult.SUCCESS) {
                String input = consoleView.getUserInputs().get(0);

                return Priority.valueOf(input);
            }
        }
        return null;
    }
}
