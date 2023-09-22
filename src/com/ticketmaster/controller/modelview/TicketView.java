package com.ticketmaster.controller.modelview;

import com.ticketmaster.model.Priority;
import com.ticketmaster.model.Request;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.TroubleTicket;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextBackgroundColor;
import com.ticketmaster.view.utils.ConsoleTextColor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TicketView {
    private TicketView(){}

    public static Map<String, Integer> getHeaders(){
        Map<String, Integer> headers = new LinkedHashMap<>();
        headers.put("ID", 5);
        headers.put("Title", 20);
        headers.put("Created by", 12);
        headers.put("Assigned to", 12);
        headers.put("Created At", 19);
        headers.put("Priority", 10);
        headers.put("Status", 16);
        headers.put("Ticket Type", 14);
        return headers;
    }

    public static List<ConsoleText> getRowData(Ticket ticket){
        List<ConsoleText> rawData = new ArrayList<>();
        rawData.add(new ConsoleText(String.valueOf("T" + ticket.getId())));
        rawData.add(new ConsoleText(ticket.getTitle()));
        rawData.add(new ConsoleText(ticket.getCreatedBy().getLogin()));
        rawData.add(new ConsoleText(ticket.getUserAssigned().getLogin()));
        rawData.add(new ConsoleText(ticket.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        ConsoleText sev = new ConsoleText(ticket.getPriority().getPriority());
        if(ticket.getPriority() == Priority.HIGH) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.RED);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        if(ticket.getPriority() == Priority.MEDIUM) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.YELLOW);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        if(ticket.getPriority() == Priority.LOW) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.GREEN);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        rawData.add(sev);
        rawData.add(new ConsoleText(ticket.getStatus().toString()));
        String ticketType = "";
        if(ticket instanceof TroubleTicket){
            ticketType = "Trouble Ticket";
        }
        else if(ticket instanceof Request){
            ticketType = "Request";
        }
        rawData.add(new ConsoleText(ticketType, ConsoleTextColor.WHITE));
        return rawData;
    }
}
