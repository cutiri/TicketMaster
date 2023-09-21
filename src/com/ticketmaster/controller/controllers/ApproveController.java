package com.ticketmaster.controller.controllers;

import com.ticketmaster.controller.framework.Controller;
import com.ticketmaster.model.InvalidActionException;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.view.framework.ConsoleView;
import com.ticketmaster.view.framework.RegexInputCollector;
import com.ticketmaster.view.framework.TextComponent;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

class ApproveController implements Controller<Ticket, RequestEditController> {

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
