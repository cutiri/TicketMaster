package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.CallBackStringOperator;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.util.Map;
import java.util.TreeMap;

class TicketEditController implements ControllerT<Object, Ticket>{

    private Ticket ticket;
    private Map<String, CallBackStringOperator> decisionMap = new TreeMap<>();
    private final TextComponent ticketNumber = new TextComponent();
    private final TextComponent ticketTitle = new TextComponent();
    private final TextComponent ticketDescription = new TextComponent();
    private final TextComponent ticketCreatedAt = new TextComponent();
    private final TextComponent ticketStatus = new TextComponent();
    private final TextComponent ticketPriority = new TextComponent();
    private final TextComponent ticketLocation = new TextComponent();
    private final TextComponent ticketTeamAssigned = new TextComponent();
    private final TextComponent ticketUserAssigned = new TextComponent();
    private final TextComponent ticketCreatedBy = new TextComponent();
    private final TextComponent ticketTimeSpent = new TextComponent();
    private final TextComponent ticketComments = new TextComponent();
    private final ConsoleView ticketEditView = new ConsoleView();

    public TicketEditController() {
        ticketEditView.addPassiveComponents(ticketNumber);
        ticketEditView.addPassiveComponents(ticketTitle);
        ticketEditView.addPassiveComponents(ticketDescription);
        ticketEditView.addPassiveComponents(ticketCreatedAt);
        ticketEditView.addPassiveComponents(ticketStatus);
        ticketEditView.addPassiveComponents(ticketPriority);
        ticketEditView.addPassiveComponents(ticketLocation);
        ticketEditView.addPassiveComponents(ticketTeamAssigned);
        ticketEditView.addPassiveComponents(ticketUserAssigned);
        ticketEditView.addPassiveComponents(ticketCreatedBy);
        ticketEditView.addPassiveComponents(ticketTimeSpent);
        ticketEditView.addPassiveComponents(ticketComments);


        ticketEditView.addInputCollector(new RegexInputCollector("Change [P]riority: ", "", ""));

        decisionMap.put(RegexSelector.CHARACTER_P.getRegex(), this::changePriority);
    }

    private void changePriority(String s) throws InvalidActionException {
        ticket.setPriority(new PrioritySelectorController().run(ticket.getPriority()));
        System.out.println(ticket);
    }

    @Override
    public Object run(Ticket ticket) throws InvalidActionException {

        this.ticket = ticket;

        ticketNumber.setText("Id: " + ticket.getId());
        ticketTitle.setText("Title: " + ticket.getTitle());
        ticketDescription.setText("Description: " + ticket.getDescription());
        ticketCreatedAt.setText("Created At: " + ticket.getCreatedAt().toString());
        ticketStatus.setText("Status: " + ticket.getStatus().name());
        ticketPriority.setText("Priority: " + ticket.getPriority().name());
        ticketLocation.setText("Location: " + ticket.getLocation().getName());
        ticketTeamAssigned.setText("Team Assigned: " + ticket.getTeamAssigned().getName());
        ticketUserAssigned.setText("User Assigned: " + ticket.getUserAssigned().getLogin());
        ticketCreatedBy.setText("Created By: " + ticket.getCreatedBy().getLogin());
        ticketTimeSpent.setText("Time Spent: " + ticket.getTotalTimeSpentInMinutes() + " Minutes");
        ticketComments.setText("Comments: " + ticket.getComments());

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
