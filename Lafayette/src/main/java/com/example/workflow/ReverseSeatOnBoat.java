package com.example.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class ReverseSeatOnBoat implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String money = "0.0";
        String ticketType = "Coach";

        money = (String) execution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);

        if (moneyDouble >= 10000) {
            ticketType = "First Class";
        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        } else if (moneyDouble <= 10) {
            ticketType = "Stowaway Class";
            throw new BpmnError("Fall_Overboard", "A Cheap ticket has led to a small wave throwing you overboard.");
        }

        execution.setVariable("ticketType", ticketType);
    }
}
