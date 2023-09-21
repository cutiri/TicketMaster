package com.ticketmaster.controller;

import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Location;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;
import com.ticketmaster.view.components.ConsoleView;
import com.ticketmaster.view.components.ListInputCollector;
import com.ticketmaster.view.components.RegexInputCollector;
import com.ticketmaster.view.components.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

class ApproveController implements ControllerT<Ticket, RequestEditController>{

    private final ConsoleView approverView = new ConsoleView();
    
    
    @Override
    public Ticket run(RequestEditController r) throws InvalidActionException {

        approverView.addInputCollector(new RegexInputCollector("Approve (Y) (Or leave empty to return to the request): ", "Invalid input, please type Y to approve", "", RegexSelector.CHARACTER_Y.getRegex()));
        TextComponent approveError = new TextComponent("Only Approver (" + r.getTicket().getApprover().getLogin() + ") of the request can approve this request", ConsoleTextColor.RED, true);
        approveError.hide();
        approverView.addPassiveComponents(approveError);

        DialogResult result = DialogResult.AWAITING;

        while (result != DialogResult.ESCAPE) {
            result = approverView.show();

            String input = approverView.getUserInputs().get(0);

            if (r.getUser().equals(r.getTicket().getApprover()) && "Y".equalsIgnoreCase(input)) {
                return r.getTicket();
            } else {
                approveError.unHide();
            }
        }
        return null;
    }
}
