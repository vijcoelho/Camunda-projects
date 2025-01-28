package com.camunda.cliente.workers;

import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.protocol.rest.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

import java.util.Map;

public class CustomerWorker {

    @ZeebeWorker(type = "validateCustomer")
    public void validateCustomer(final JobClient client, final ActivatedJob job) {
        Map<String, Object> variables = job.getVariables();
        String cpf = (String) variables.get("cpf");

        boolean isValid = cpf != null && cpf.matches("\\d{11}");
        variables.put("isValid", isValid);

        client.newCompleteCommand(job.getJobKey())
                .variables(variables)
                .send()
                .join();
    }

    @ZeebeWorker(type = "notifyCustomer")
    public void notifyCustomer(final JobClient client, final ActivatedJob job) {
        Map<String, Object> varibles = job.getVariables();
        String email = (String) varibles.get("email");
        boolean approved = (boolean) varibles.get("approved");

        String message = approved
                ? "Client approved"
                : "Client not approved";

        System.out.println("Send email to " + email + ": " + message);

        client.newCompleteCommand(job.getJobKey()).send().join();
    }
}
