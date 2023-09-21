package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.db.TicketDB;
import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.controller.modelview.TicketView;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Request;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;
import com.ticketmaster.view.framework.*;
import com.ticketmaster.view.utils.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * This Controller handles the entire ticket queue
 * From here we re-direct the flow to other controllers
 */
class TicketQueueController implements Controller<Object, User> {
    private static final int TICKETS_PER_PAGE = 10;

    private int currentPage = 1;
    private int numberOfPages;
    private int ticketNumber;
    private User user;

    ConsoleView ticketQueueView = new ConsoleView();
    private SheetComponent ticketsSheet = new SheetComponent();
    private MultiTextComponent ticketQueueUserOptions;
    private RegexInputCollector inputCollector = new RegexInputCollector("Enter text here: ", "Invalid option, try again", "", RegexSelector.ANYTHING.getRegex());
    private TicketQueueFilterController ticketQueueFilterController = new TicketQueueFilterController();

    private List<Ticket> ticketList = new ArrayList<>();
    private Map<String, CallBackStringOperator> decisionMap = new TreeMap<>();

    public TicketQueueController(){
        super();
        this.ticketQueueUserOptions = new MultiTextComponent(
                new ConsoleText("\nEnter "),
                new ConsoleText("P", ConsoleTextColor.GREEN),
                new ConsoleText(" or "),
                new ConsoleText("N", ConsoleTextColor.GREEN),
                new ConsoleText(" to navigate to the Previous or Next page.\n"),
                new ConsoleText("Enter "),
                new ConsoleText("T", ConsoleTextColor.GREEN),
                new ConsoleText(" followed by the ticket ID to open a ticket. For example: "),
                new ConsoleText("T1234\n", ConsoleTextColor.GREEN),
                new ConsoleText("Enter "),
                new ConsoleText("A", ConsoleTextColor.GREEN),
                new ConsoleText(" to add a new ticket or "),
                new ConsoleText("R", ConsoleTextColor.GREEN),
                new ConsoleText(" to submit a new request (hardware/software).\n"),
                new ConsoleText("Enter "),
                new ConsoleText("F", ConsoleTextColor.GREEN),
                new ConsoleText(" to set a filter or go to a different ticket queue.\n"),
                new ConsoleText("Leave blank and press "),
                new ConsoleText("ENTER", ConsoleTextColor.GREEN),
                new ConsoleText(" to logout.\n")
        );

        this.ticketQueueView.addPassiveComponents(ticketsSheet);
        this.ticketQueueView.addPassiveComponents(ticketQueueUserOptions);
        this.ticketQueueView.addInputCollector(inputCollector);

        decisionMap.put(RegexSelector.TICKET_NUMBER.getRegex(), this::openTicketNumber);
        decisionMap.put(RegexSelector.PAGE_THEN_ANY_NUMBER.getRegex(), this::goToPage);
        decisionMap.put(RegexSelector.CHARACTER_P.getRegex(), this::goToPreviousPage);
        decisionMap.put(RegexSelector.CHARACTER_N.getRegex(), this::goToNextPage);
        decisionMap.put(RegexSelector.CHARACTER_A.getRegex(), this::createNewTicket);
        decisionMap.put(RegexSelector.CHARACTER_R.getRegex(), this::createNewRequest);
        decisionMap.put(RegexSelector.CHARACTER_F.getRegex(), this::filterBy);


    }



    @Override
    public Object run(User user) throws InvalidActionException {
        ticketList = TicketDB.getList();

        this.user = user;
        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE){
            initializeAllValues();

            result = ticketQueueView.show();

            String input = ticketQueueView.getUserInputs().get(0);
            String regex = decisionMap.keySet().stream().filter(input::matches).findFirst().orElse(null);
            if(regex != null) {
                decisionMap.get(regex).callback(input);
            }
        }

        return null;
    }

    /*
     * Method to initialize our View
     */
    private void initializeAllValues(){
        ticketNumber = ticketList.size();
        numberOfPages = ticketNumber / TICKETS_PER_PAGE + 1;
        if(currentPage > numberOfPages)
            currentPage = numberOfPages;

        List<Ticket> tickets = ticketList.stream()
                .skip((currentPage - 1) * TICKETS_PER_PAGE)
                .limit(10)
                .collect(Collectors.toList());

        List<List<ConsoleText>> data = new ArrayList<>();
        for (Ticket ticket : tickets){
            data.add(TicketView.getRowData(ticket));
        }

        ticketsSheet.setSheetComponentContent(TicketView.getHeaders(), data);
        ConsoleMultiColorText consoleMultiColorText = new ConsoleMultiColorText(
                new ConsoleText("Welcome: "),
                new ConsoleText(user.getFullName(), ConsoleTextColor.RED),
                new ConsoleText(", "),
                new ConsoleText("TICKET", ConsoleTextColor.CYAN),
                new ConsoleText("QUEUE.", ConsoleTextColor.GREEN)
        );
        ticketsSheet.setBannerMessage(consoleMultiColorText);
        ticketsSheet.setCurrentPage(this.currentPage);
        ticketsSheet.setTotalPages(this.numberOfPages);
        ticketsSheet.setTotalRows(this.ticketNumber);
    }

    /*
     * Here we handle opening a ticket
     */
    private void openTicketNumber(String input) throws InvalidActionException {
        String ticketNumberText = input.toLowerCase().replace("t", "");
        int ticketNumber = Integer.parseInt(ticketNumberText);

        Ticket ticket = TicketDB.findTicketById(ticketNumber);

        if (ticket.getClass().equals(Request.class)) {
            new RequestEditController(user).run(ticket);
        } else {
            TicketEditController ticketEditController = new TicketEditController(user);
            ticketEditController.run(ticket);
        }

        if(ticketQueueFilterController.getTicketList() != null)
            this.ticketList = ticketQueueFilterController.getTicketList();

        AppIO.saveS();
    }

    /*
     * Handles changing pages in the queue
     */
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

    /*
     * as the method suggests, we go to the next page
     */
    private void goToNextPage(String input){
        goToPage(String.valueOf(currentPage + 1));
    }

    /*
     * as the method suggests, we go to the previous page
     */
    private void goToPreviousPage(String input){
        goToPage(String.valueOf(currentPage - 1));
    }

    /*
     * we redirect the flow to another controller that will take care of creating a new ticket
     */
    private void createNewTicket(String input) {
        Ticket newTicket = new AddTicketController().run(user);
        if (newTicket != null) {
            TicketDB.add(newTicket);
            if(ticketQueueFilterController.getTicketList() != null)
                this.ticketList = ticketQueueFilterController.getTicketList();
        }

    }

    /*
     * redirecting the flow to a controller that creates requests
     */
    private void createNewRequest(String s) {
        Ticket newRequest = new AddRequestController().run(user);
        if (newRequest != null) {
            TicketDB.add(newRequest);
            if(ticketQueueFilterController.getTicketList() != null)
                this.ticketList = ticketQueueFilterController.getTicketList();
        }
    }

    /*
     * method to filter our ticket queue
     */
    private void filterBy(String input) {
        this.ticketList = ticketQueueFilterController.run(user);
    }
}
