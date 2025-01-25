package io.camunda.demo.process_payments;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class ProcessPaymentsApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessPaymentsApplication.class);

    @Autowired
    private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(ProcessPaymentsApplication.class, args);
	}

    @Override
    public void run(final String... args) {
        var bpmnProcessId = "process-payments";
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(bpmnProcessId)
                .latestVersion()
                .variables(Map.of("total", 100))
                .send()
                .join();
        LOG.info("started a process instance: {}", event.getProcessInstanceKey());
    }

}
