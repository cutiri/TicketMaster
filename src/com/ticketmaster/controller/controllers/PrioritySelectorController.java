package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Priority;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.ListInputCollector;
import com.ticketmaster.view.framework.MultiTextComponent;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;

class PrioritySelectorController implements Controller<Priority, Priority> {

    private final ConsoleView prioritySelectorView = new ConsoleView();


    @Override
    public Priority run(Priority priority) throws InvalidActionException {

        prioritySelectorView.addInputCollector(new ListInputCollector("Change Priority (Or leave blank to return to the ticket) ", "Invalid option, try again", "", Priority.getPriorityStringList()));
//        consoleView.addPassiveComponents(new TextComponent("Current Priority: " + priority.name()));
        prioritySelectorView.addPassiveComponents(new MultiTextComponent(
                                         new ConsoleText("Current Status: " ),
                                         new ConsoleText(priority.name(), ConsoleTextColor.GREEN)));

        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = prioritySelectorView.show();

            if (result == DialogResult.SUCCESS) {
                String input = prioritySelectorView.getUserInputs().get(0);

                return Priority.valueOf(input.toUpperCase());
            }
        }
        return null;
    }
}
