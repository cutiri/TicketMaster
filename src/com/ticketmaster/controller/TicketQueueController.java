package com.ticketmaster.controller;

import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;
import com.ticketmaster.view.components.*;
import com.ticketmaster.view.utils.*;

import java.util.*;

class TicketQueueController implements ControllerT<Object, User>{

    @Override
    public Object run(User user) {
        ConsoleView ticketQueueView = new ConsoleView();

        List<List<ConsoleText>> data = new ArrayList<>();

        //FAKE DATA: TODO REPLACE WITH ACTUAL DATA

        Map<String, CallBackStringOperator> map = new TreeMap<>();
        map.put(RegexSelector.NUMBER_1_TO_20.getRegex(), this::openTableElement);
        map.put(RegexSelector.TICKET_NUMBER.getRegex(), this::openTicketNumber);



        //ticketQueueView.addPassiveComponents(new SheetComponent(Ticket.getHeaders(), data));
        ticketQueueView.addInputCollector(new InputCollectorRegex("Enter text here: ", "", "", RegexSelector.ANYTHING.getRegex()));


        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE){
            result = ticketQueueView.show();

            String input = ticketQueueView.getUserInputs().get(0);
            String regex = map.keySet().stream().filter((r) -> input.matches(r)).findFirst().orElse(null);
            if(regex != null)
                map.get(regex).callback(input);
        }


        return null;
    }

    private void openTicketNumber(Object input){
        System.out.println(input);
        System.out.println("openTicketNumber");
    }

    private void openTableElement(Object input){
        System.out.println(input);
        System.out.println("openTableElement");
    }
}
