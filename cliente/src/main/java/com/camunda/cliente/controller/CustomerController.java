package com.camunda.cliente.controller;

import com.camunda.cliente.model.Customer;
import com.camunda.cliente.repository.CustomerRepository;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ZeebeClient zeebeClient;
    private final CustomerRepository customerRepository;

    public CustomerController(
            ZeebeClient zeebeClient,
            CustomerRepository customerRepository
    ) {
        this.zeebeClient = zeebeClient;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("customer_registration")
                .latestVersion()
                .variables(Map.of(
                        "name", customer.getName(),
                        "email", customer.getEmail(),
                        "cpf", customer.getCpf()
                ))
                .send()
                .join();

        return ResponseEntity.ok("Starting process");
    }
}
