package com.ticketmaster.controller;

import com.ticketmaster.model.*;
import com.ticketmaster.model.db.Database;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.CallBackStringOperator;
import com.ticketmaster.view.utils.DialogResult;
import org.w3c.dom.Text;

import javax.xml.crypto.Data;
import java.util.*;

class TicketQueueFilterController implements ControllerT<List<Ticket>, User>{

    private ConsoleView consoleView = new ConsoleView();
    private ListInputCollector listInputCollector;
    private User user;

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
    public List<Ticket> run(User user) throws InvalidActionException {
        this.user = user;
        listInputCollector = new ListInputCollector("Enter a filter's value","Invalid option, please try again","", List.copyOf(decisionMap.keySet()));

        List<Ticket> result = new ArrayList<>();
        DialogResult dialogResult = DialogResult.AWAITING;
        while (dialogResult != DialogResult.ESCAPE) {
            dialogResult = listInputCollector.show();

            for (String key : decisionMap.keySet()) {
                if (key.equals(listInputCollector.getCollectedInput())) {
                    return decisionMap.get(key).callback();
                }
            }

        }
        return result;
    }

    public List<Ticket> getTicketList(){
        if(listInputCollector == null)
            return null;
        return decisionMap.get(listInputCollector.getCollectedInput()).callback();
    }

    private List<Ticket> getTicketsAssignedToMe(){
        return Database.findTicketsByAssignedUser(user);
    }

    private List<Ticket> geTicketsAssignedToMyTeam(){
        return Database.findTicketsByAssignedTeam(user.getTeam().getName());
    }

    private List<Ticket> getTicketsCreatedByMe(){
        return Database.findTicketsByTicketCreator(user.getLogin());
    }

    private List<Ticket> getTicketsClosed() {
        return Database.findTicketsByStatus(Status.RESOLVED);
    }

    private List<Ticket> getTicketsOpened() {
        return Database.findTicketsByStatus(Status.OPEN);
    }

}
