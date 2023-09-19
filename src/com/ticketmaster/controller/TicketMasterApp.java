package com.ticketmaster.controller;

import com.ticketmaster.model.db.Database;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.InputCollector;
import com.ticketmaster.view.components.InputCollectorRegex;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

public class TicketMasterApp {
    public static void main(String[] args) {
        try {
            Database.loadData();
            MainController mainController = new MainController();
            mainController.run(null);
        }catch (Exception e){

        }
    }
}
