package com.ticketmaster.controller;

import com.ticketmaster.model.*;
import com.ticketmaster.view.components.ConsoleView;

import com.ticketmaster.view.components.MultiTextComponent;
import com.ticketmaster.view.components.RegexInputCollector;

import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

class TicketEditController implements ControllerT<Object, Ticket>{

    private User user;
    private Ticket ticket;
    private final Map<String, CallBackStringOperator> decisionMap = new TreeMap<>();
    private final TextComponent ticketInfo = new TextComponent();
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

    public TicketEditController(User user) {
        this.user = user;
        ticketEditView.addPassiveComponents(ticketInfo);
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

        ticketEditView.addPassiveComponents(new MultiTextComponent(
                new ConsoleText("Update "),
                new ConsoleText("[P]", ConsoleTextColor.GREEN),
                new ConsoleText("riority "),
                new ConsoleText(" [S]", ConsoleTextColor.GREEN),
                new ConsoleText("tatus "),
                new ConsoleText(" [C]", ConsoleTextColor.GREEN),
                new ConsoleText("ommit "),
                new ConsoleText(" [U]", ConsoleTextColor.GREEN),
                new ConsoleText("ser Assigned "),
                new ConsoleText("[L]", ConsoleTextColor.GREEN),
                new ConsoleText("ocation\n"),
                new ConsoleText("OR Leave Blank To Return To Ticket Queue")
        ));

        ticketEditView.addInputCollector(new RegexInputCollector("Enter one of the options above: ", "Invalid option, please try again", "", RegexSelector.EDIT_TICKET_OPTIONS.getRegex()));


        decisionMap.put(RegexSelector.CHARACTER_P.getRegex(), this::changePriority);
        decisionMap.put(RegexSelector.CHARACTER_S.getRegex(), this::changeStatus);
        decisionMap.put(RegexSelector.CHARACTER_C.getRegex(), this::addComment);
        decisionMap.put(RegexSelector.CHARACTER_U.getRegex(), this::updateAssignedUser);
        decisionMap.put(RegexSelector.CHARACTER_L.getRegex(), this::updateLocation);
    }

    private void updateLocation(String s) {
        Location newLocation = new UpdateLocationController().run(ticket.getLocation());
        if (newLocation != null) {
            ticket.updateLocation(newLocation);
        }
    }

    private void updateAssignedUser(String s) {
        User newAssignedUser = new UpdateAssignedUserController().run(ticket.getUserAssigned());
        if (newAssignedUser != null) {
            ticket.setUserAssigned(newAssignedUser);

        }
    }

    private void addComment(String s) {
        Comment newComment = new AddCommentController().run(user);

        if (newComment != null) {
            ticket.addComment(newComment);
        }
    }

    private void changeStatus(String s) throws InvalidActionException {
        Status newStatus = new StatusSelectorController().run(ticket);

        if(newStatus != null) {
            ticket.updateStatus(newStatus);
        }
    }

    private void changePriority(String s) throws InvalidActionException {
        Priority newPriority = new PrioritySelectorController().run(ticket.getPriority());

        if(newPriority != null) {
            ticket.setPriority(newPriority);
        }
    }

    // StringBuilder to convert comment arraylist to proper formatted string for console view
    private String commentStringBuilder(Collection<Comment> comments) {
        StringBuilder formattedComments = new StringBuilder("\n--------------------------------------------------");

        for (Comment comment: comments) {
            formattedComments.append(comment);
        }

        return formattedComments.toString();
    }

    private void initializeAllValues() {
        ticketInfo.setText("##Ticket Details##");
        ticketInfo.setTextColor(ConsoleTextColor.GREEN);
        ticketNumber.setText("Id: " + ticket.getId());
        ticketTitle.setText("Title: " + ticket.getTitle());
        ticketDescription.setText("Description: " + ticket.getDescription());
        ticketCreatedAt.setText("Created At: " + LocalDateTime.parse(ticket.getCreatedAt().toString()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        ticketStatus.setText("Status: " + ticket.getStatus().getStatus());
        ticketPriority.setText("Priority: " + ticket.getPriority().getPriority());
        ticketLocation.setText("Location: " + ticket.getLocation().getName());
        ticketTeamAssigned.setText("Team Assigned: " + ticket.getTeamAssigned().getName());
        ticketUserAssigned.setText("User Assigned: " + ticket.getUserAssigned().getLogin());
        ticketCreatedBy.setText("Created By: " + ticket.getCreatedBy().getLogin());
        ticketTimeSpent.setText("Time Spent: " + ticket.getTotalTimeSpentInMinutes() + " Minutes");
        ticketComments.setText("Comments: " + commentStringBuilder(ticket.getComments()));
    }

    @Override
    public Object run(Ticket ticket) throws InvalidActionException {

        this.ticket = ticket;

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {
            initializeAllValues();
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
