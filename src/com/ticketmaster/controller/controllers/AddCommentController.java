package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.Comment;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.User;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.RegexInputCollector;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.time.LocalDateTime;

/*
 * This controller will handle adding comments to a ticket
 * It's main method run accepts an user and returns the comment
 */
public class AddCommentController implements Controller<Comment, User> {
    //Constrains to limit the amount of time spend on a ticket
    private static final int MAX_TIME_SPENT = 99999;
    private static final int MIN_TIME_SPENT = 0;

    private final ConsoleView consoleView = new ConsoleView();

    /*
     * Main class method that will handle the View and interacts with the Model
     */
    @Override
    public Comment run(User user) throws InvalidActionException {

        consoleView.addInputCollector(new RegexInputCollector("New Comment: ", "Invalid Comment", ""));
        consoleView.addInputCollector(new RegexInputCollector("Time Spent (Minutes): ", "Invalid time, please enter time in minutes again ("+ MIN_TIME_SPENT +" - "+ MAX_TIME_SPENT+")", "", RegexSelector.NUMBERS.getRegex()));

        //We loop until the user enters the ESCAPE sequence
        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = consoleView.show();

            if (result == DialogResult.SUCCESS) {
                String text = consoleView.getUserInputs().get(0);
                int timeSpent = Integer.parseInt(consoleView.getUserInputs().get(1));

                return new Comment(text, user.getLogin(), timeSpent, LocalDateTime.now());
            }
        }
        return null;
    }
}
