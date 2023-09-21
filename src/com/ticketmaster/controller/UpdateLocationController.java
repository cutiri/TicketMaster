package com.ticketmaster.controller;

import com.ticketmaster.controller.db.LocationDB;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Location;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;

class UpdateLocationController implements ControllerT<Location, Location>{

    private final ConsoleView updateLocationView = new ConsoleView();

    @Override
    public Location run(Location location) throws InvalidActionException {
        //updateLocationView.addInputCollector(new RegexInputCollector("Update Location to (Or leave blank to return to the ticket): ", "Invalid Location, please try again", "", RegexSelector.LOCATION.getRegex()));
        updateLocationView.addInputCollector(new ListInputCollector("Update Location to (Or leave blank to return to the ticket): ", "Invalid option, please try again", "", LocationDB.locationNameList()));
        TextComponent invalidLocationError = new TextComponent("Location does not exist, please try again", ConsoleTextColor.RED, true);
        invalidLocationError.hide();
        updateLocationView.addPassiveComponents(invalidLocationError);

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {
            result = updateLocationView.show();

            String input = updateLocationView.getUserInputs().get(0);

            Location newLocation = LocationDB.getList().stream()
                    .filter(loc -> loc.getName().equalsIgnoreCase(input))
                    .findFirst().orElse(null);

            if (newLocation != null) {
                return newLocation;
            } else {
                invalidLocationError.unHide();
            }
        }

        return null;
    }
}
