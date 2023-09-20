package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Location;
import com.ticketmaster.model.db.LocationDB;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

class UpdateLocationController implements ControllerT<Location, Location>{

    private final ConsoleView updateLocationView = new ConsoleView();

    @Override
    public Location run(Location location) throws InvalidActionException {
        updateLocationView.addInputCollector(new RegexInputCollector("Update Location to (Or leave blank to return to the ticket): ", "Invalid Location, please try again", "", RegexSelector.LOCATION.getRegex()));
        TextComponent invalidLocationError = new TextComponent("Location does not exist, please try again", ConsoleTextColor.RED, true);
        invalidLocationError.hide();
        updateLocationView.addPassiveComponents(invalidLocationError);

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {
            result = updateLocationView.show();

            String input = updateLocationView.getUserInputs().get(0);

            Location newLocation =  LocationDB.locationList().stream()
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
