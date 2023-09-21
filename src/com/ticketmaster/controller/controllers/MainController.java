package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.db.UserDB;
import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.controller.io.LogoIO;
import com.ticketmaster.model.User;
import com.ticketmaster.view.framework.*;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;


/*
 * Main Controller, it handles the logic behind authenticating the user
 * It uses a View that contains controllers in order to show a Welcome message and collect username and passowrd
 */
public class MainController implements Controller<Object, Object> {

    private TicketQueueController ticketQueueController = new TicketQueueController();

    /*
     * Main method inside the class, it will take care of interacting with the View and the Model
     */
    @Override
    public Object run(Object o){
        //We initialize our View
        ConsoleView mainView = new ConsoleView();

        //Adding components to our view
        //The first component is a Ticket Master logo
        mainView.addPassiveComponents(new TextComponent(LogoIO.getLogo()));
        //An error message in case the username/password is wrong that would be hidden by default
        TextComponent mainViewBadUsernamePassword = new TextComponent("Wrong username or password, try again.", ConsoleTextColor.RED, true);
        mainView.addPassiveComponents(mainViewBadUsernamePassword);
        mainView.addPassiveComponents(new MultiTextComponent(
                new ConsoleText("Leave blank and press "),
                new ConsoleText("ENTER ", ConsoleTextColor.GREEN),
                new ConsoleText("to exit the application.")
        ));

        //Two Ative components that will collect username, and password respectively
        //The first input Validator gets a Lambda that will get triggered when the user enters a value
        mainView.addInputCollector(new ValidatorInputCollector("username: ", "Invalid username", "", this::validateUsername));
        mainView.addInputCollector(new RegexInputCollector("password: ", "", "", RegexSelector.ANYTHING.getRegex()));

        //We initialize the result as AWAITING, our View and Input Collectors are waiting for the user to enter something
        DialogResult result = DialogResult.AWAITING;
        //We loop until the user enters the ESCAPE sequence, in this case the escape sequence is blank
        while (result != DialogResult.ESCAPE){
            //calling show in our Main View, this will show the authentication view and collect the input from the user
            //if the View validates the input it will return SUCCESS
            result = mainView.show();

            //If the View returns SUCCESS then we verify that the username and password are correct
            if(result == DialogResult.SUCCESS){
                String username = mainView.getUserInputs().get(0);
                String password = mainView.getUserInputs().get(1);

                try {
                    //UserDB.authenticate will return the correct user if username and password are correct
                    //It will return null in case at least one was wrong
                    User user = UserDB.authenticate(username, password);
                    //If user is not null then we trigger the TicketQueue controller
                    if(user != null){
                        ticketQueueController = new TicketQueueController();
                        mainViewBadUsernamePassword.hide();
                        //Calling the TicketQueue controller to run, passing the user as a parameter
                        ticketQueueController.run(user);
                    }else{
                        //If user is null then we unHide our error message inside the View
                        mainViewBadUsernamePassword.unHide();
                        continue;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    //This method gets triggered using a Functional Interface once the user enters the username
    //We will make sure the username doesn't start with a number
    private boolean validateUsername(String s) {
        if(s == null) {
            return false;
        }
        else{
            return !Character.isDigit(s.charAt(0));
        }
    }
}
