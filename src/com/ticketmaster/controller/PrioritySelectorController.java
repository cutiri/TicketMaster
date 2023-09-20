package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Priority;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.DialogResult;

class PrioritySelectorController implements ControllerT<Priority, Priority> {

    private final ConsoleView consoleView = new ConsoleView();


    @Override
    public Priority run(Priority priority) throws InvalidActionException {
        consoleView.addPassiveComponents(new TextComponent("Current Status: " + priority));
        consoleView.addInputCollector(new ListInputCollector("Change status to " + Priority.getPriorities() + " :", "Invalid Priority", "", Priority.getPriorityStringList()));

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
