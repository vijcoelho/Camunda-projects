package io.camunda.demo.process_payments;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChargeCreditCardWorker {
    private final static Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);

    @JobWorker(type = "charge-credit-card")
    public Map<String, Double> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
        LOG.info("charge credit card: {}", totalWithTax);
        return Map.of("amountCharged", totalWithTax);
    }
}
