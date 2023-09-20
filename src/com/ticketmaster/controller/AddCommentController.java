package com.ticketmaster.controller;

import com.ticketmaster.model.Comment;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.User;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollectorRegex;
import com.ticketmaster.view.utils.DialogResult;

import java.time.LocalDateTime;

class AddCommentController implements ControllerT<Comment, User>{

    private final ConsoleView consoleView = new ConsoleView();

    @Override
    public Comment run(User user) throws InvalidActionException {

        consoleView.addInputCollector(new InputCollectorRegex("New Comment: ", "Invalid Comment", ""));
        consoleView.addInputCollector(new InputCollectorRegex("Time Spent (Minutes): ", "", ""));

        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = consoleView.show();

            if (result == DialogResult.SUCCESS) {
                String text = consoleView.getUserInputs().get(0);
                int timeSpent = Integer.parseInt(consoleView.getUserInputs().get(1));
                System.out.println(text + timeSpent + user.getLogin());

                return new Comment(text, user.getLogin(), timeSpent, LocalDateTime.now());
            }
        }
        return null;
    }
}
