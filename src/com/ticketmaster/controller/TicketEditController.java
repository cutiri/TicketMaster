package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollectorRegex;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.CallBackStringOperator;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.util.Map;
import java.util.TreeMap;

class TicketEditController implements ControllerT<Object, Ticket>{

    private Ticket ticket;
    private Map<String, CallBackStringOperator> decisionMap = new TreeMap<>();
    private TextComponent ticketNumber = new TextComponent();
    private ConsoleView ticketEditView = new ConsoleView();

    public TicketEditController() {
        ticketEditView.addPassiveComponents(ticketNumber);
        ticketEditView.addInputCollector(new InputCollectorRegex("Edit", "", ""));

        decisionMap.put(RegexSelector.CHARACTER_P.getRegex(), this::changePriority);
    }

    private void changePriority(String s) throws InvalidActionException {
        ticket.setPriority(new PrioritySelectorController().run(ticket.getPriority()));
    }

    @Override
    public Object run(Ticket ticket) throws InvalidActionException {

        this.ticket = ticket;

        ticketNumber.setText("Ticket Number: " + ticket.getId());

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {
            result = ticketEditView.show();
            String input = ticketEditView.getUserInputs().get(0);

            String regex = decisionMap.keySet().stream().filter((r) -> input.matches(r)).findFirst().orElse(null);
            if(regex != null) {
                decisionMap.get(regex).callback(input);
            }
        }

        return null;
    }
}
