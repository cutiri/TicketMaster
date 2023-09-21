package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.db.TicketDB;
import com.ticketmaster.controller.framework.CallBackTicketList;
import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.*;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.ListInputCollector;
import com.ticketmaster.view.utils.DialogResult;
import java.util.*;

/*
 * This Controller will take care of filtering our ticket queue
 */
class TicketQueueFilterController implements Controller<List<Ticket>, User> {

    private ListInputCollector listInputCollector;
    private User user;
    private ConsoleView consoleView;
    private Map<String, CallBackTicketList> decisionMap = new TreeMap<>();

    public TicketQueueFilterController(){
        decisionMap = new TreeMap<>();

        decisionMap.put("Assigned to me", this::getTicketsAssignedToMe);
        decisionMap.put("Assigned to my team", this::geTicketsAssignedToMyTeam);
        decisionMap.put("Created by me", this::getTicketsCreatedByMe);
        decisionMap.put("Opened tickets", this::getTicketsOpened);
        decisionMap.put("Closed tickets", this::getTicketsClosed);
    }


    @Override
    public List<Ticket> run(User user) {
        this.user = user;
        consoleView = new ConsoleView();

        listInputCollector = new ListInputCollector("Enter a filter's value","Invalid option, please try again","", List.copyOf(decisionMap.keySet()));

        consoleView.addInputCollector(listInputCollector);

        List<Ticket> result = new ArrayList<>();
        DialogResult dialogResult = DialogResult.AWAITING;
        while (dialogResult != DialogResult.ESCAPE) {
            dialogResult = consoleView.show();


            for (String key : decisionMap.keySet()) {
                if (key.equals(listInputCollector.getCollectedInput())) {
                    return decisionMap.get(key).callback();
                }
            }

        }
        return result;
    }

    /*
     * returns the ticket queue depending on the selected filter
     */
    public List<Ticket> getTicketList(){
        if(listInputCollector == null)
            return null;
        return decisionMap.get(listInputCollector.getCollectedInput()).callback();
    }

    /*
     * this method will get called to show only tickets assigned to the authenticated user
     */
    private List<Ticket> getTicketsAssignedToMe(){
        return TicketDB.findTicketsByAssignedUser(user);
    }

    /*
     * this method will get called to show only tickets assigned the authenticated user's team
     */
    private List<Ticket> geTicketsAssignedToMyTeam(){
        return TicketDB.findTicketsByAssignedTeamName(user.getTeam().getName());
    }

    /*
     * this method will get called to show only tickets created by the authenticated user
     */
    private List<Ticket> getTicketsCreatedByMe(){
        return TicketDB.findTicketsByTicketCreatorLogin(user.getLogin());
    }

    /*
     * this method will get called to show only tickets that are closed
     */
    private List<Ticket> getTicketsClosed() {
        return TicketDB.findTicketsByStatus(Status.RESOLVED);
    }

    /*
     * this method will get called to show only tickets that are Opened
     */
    private List<Ticket> getTicketsOpened() {
        return TicketDB.findTicketsByStatus(Status.OPEN);
    }

}
