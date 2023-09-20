package com.ticketmaster.controller;

import com.ticketmaster.model.Comment;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.User;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.time.LocalDateTime;

class AddCommentController implements ControllerT<Comment, User>{
    private static final int MAX_TIME_SPENT = 99999;
    private static final int MIN_TIME_SPENT = 0;

    private final ConsoleView consoleView = new ConsoleView();

    @Override
    public Comment run(User user) throws InvalidActionException {

        consoleView.addInputCollector(new RegexInputCollector("New Comment: ", "Invalid Comment", ""));
        consoleView.addInputCollector(new RegexInputCollector("Time Spent (Minutes): ", "Invalid time, please enter time in minutes again ("+ MIN_TIME_SPENT +" - "+ MAX_TIME_SPENT+")", "", RegexSelector.NUMBERS.getRegex()));

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
