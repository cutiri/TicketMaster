package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;
import com.ticketmaster.model.db.Database;
import com.ticketmaster.view.components.*;
import com.ticketmaster.view.utils.*;

import java.util.*;
import java.util.stream.Collectors;

class TicketQueueController implements ControllerT<Object, User>{
    private static final int TICKETS_PER_PAGE = 10;

    private int currentPage = 1;
    private int numberOfPages;
    private int ticketNumber;
    private User user;

    ConsoleView ticketQueueView = new ConsoleView();
    private SheetComponent ticketsSheet = new SheetComponent();
    private TextComponent bottomBar = new TextComponent();
    private InputCollectorRegex inputCollector = new InputCollectorRegex("Enter text here: ", "", "", RegexSelector.ANYTHING.getRegex());

    Map<String, CallBackStringOperator> decisionMap = new TreeMap<>();

    public TicketQueueController(){
        super();
        this.ticketQueueView.addPassiveComponents(ticketsSheet);
        this.ticketQueueView.addInputCollector(inputCollector);

        decisionMap.put(RegexSelector.NUMBER_1_TO_20.getRegex(), this::openTableElement);
        decisionMap.put(RegexSelector.TICKET_NUMBER.getRegex(), this::openTicketNumber);
        decisionMap.put(RegexSelector.PAGE_THEN_ANY_NUMBER.getRegex(), this::goToPage);
        decisionMap.put(RegexSelector.CHARACTER_P.getRegex(), this::goToPreviousPage);
        decisionMap.put(RegexSelector.CHARACTER_N.getRegex(), this::goToNextPage);
    }

    @Override
    public Object run(User user) throws InvalidActionException {
        this.user = user;
        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE){
            initializeAllValues();

            result = ticketQueueView.show();

            String input = ticketQueueView.getUserInputs().get(0);
            String regex = decisionMap.keySet().stream().filter((r) -> input.matches(r)).findFirst().orElse(null);
            if(regex != null) {
                decisionMap.get(regex).callback(input);
            }
        }

        return null;
    }

    private void initializeAllValues(){
        ticketNumber = Database.allTickets().size();
        numberOfPages = ticketNumber / TICKETS_PER_PAGE + 1;
        if(currentPage > numberOfPages)
            currentPage = numberOfPages;

        List<Ticket> tickets = Database.allTickets().stream()
                .skip((currentPage - 1) * TICKETS_PER_PAGE)
                .limit(10)
                .collect(Collectors.toList());

        List<List<ConsoleText>> data = new ArrayList<>();
        for (Ticket ticket : tickets){
            data.add(ticket.getRowData());
        }

        ticketsSheet.setSheetComponentContent(Ticket.getHeaders(), data);
        ticketsSheet.setBannerMessage(new ConsoleMultiColorText(
                new ConsoleText("Welcome: "),
                new ConsoleText(user.getLogin(), ConsoleTextColor.RED),
                new ConsoleText(", "),
                new ConsoleText("TICKET QUEUE.", ConsoleTextColor.CYAN)
        ));
        ticketsSheet.setCurrentPage(this.currentPage);
        ticketsSheet.setTotalPages(this.numberOfPages);
        ticketsSheet.setTotalRows(this.ticketNumber);
    }

    private void openTicketNumber(String input) throws InvalidActionException {
        String ticketNumberText = input.toLowerCase().replace("t", "");
        int ticketNumber = Integer.parseInt(ticketNumberText);

        Ticket ticket = Database.findTicketById(ticketNumber);

        TicketEditController ticketEditController = new TicketEditController();
//        System.out.println(ticket);
        ticketEditController.run(ticket);
    }

    private void openTableElement(Object input){
        System.out.println(input);
        System.out.println("openTableElement");
    }

    private void goToPage(String input){
        String pageNumberText = input.toLowerCase().replace("page ", "");

        int pageNumber = Integer.parseInt(pageNumberText);
        if(pageNumber < 1) {
            currentPage = 1;
        }
        else if(pageNumber > numberOfPages) {
            currentPage = numberOfPages;
        }
        else{
            currentPage = pageNumber;
        }
    }

    private void goToNextPage(String input){
        goToPage(String.valueOf(currentPage + 1));
    }

    private void goToPreviousPage(String input){
        goToPage(String.valueOf(currentPage - 1));
    }
}
