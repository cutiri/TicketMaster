package com.ticketmaster.controller;

import com.ticketmaster.controller.db.UserDB;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.User;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

import java.util.stream.Collectors;

class UpdateAssignedUserController implements ControllerT<User, User>{

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
