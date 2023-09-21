package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.db.UserDB;
import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.User;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.ListInputCollector;
import com.ticketmaster.view.framework.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import java.util.stream.Collectors;

/*
 * Controller that let the user change a ticket's assigned user
 */
class UpdateAssignedUserController implements Controller<User, User> {

    private final ConsoleView updateAssignedUserView = new ConsoleView();

    @Override
    public User run(User user) throws InvalidActionException {

//        updateAssignedUserView.addInputCollector(new RegexInputCollector("Assign To: (Or leave blank to return to the ticket): ", "Invalid login, please try again", "", RegexSelector.NO_NUMBERS.getRegex()));
        updateAssignedUserView.addInputCollector(new ListInputCollector("Assign To: (Or leave blank to return to the ticket): ", "Invalid login, please try again", "", UserDB.getList().stream().map(User::getLogin).collect(Collectors.toList())));
        TextComponent invalidLoginError = new TextComponent("User does not exist, please try again", ConsoleTextColor.RED, true);
        invalidLoginError.hide();
        updateAssignedUserView.addPassiveComponents(invalidLoginError);

        DialogResult result = DialogResult.AWAITING;
        while (result != DialogResult.ESCAPE) {
            result = updateAssignedUserView.show();

            if (result == DialogResult.SUCCESS) {
                String userLogin = updateAssignedUserView.getUserInputs().get(0);

                User newAssignedUser =  UserDB.getList().stream()
                        .filter(usr -> usr.getLogin().equalsIgnoreCase(userLogin))
                        .findFirst().orElse(null);

                if (newAssignedUser != null) {
                    return newAssignedUser;
                } else {
                    invalidLoginError.unHide();
                }
            }
        }
        return null;
    }
}
