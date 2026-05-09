package com.project.mercadolivre;

import io.camunda.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath:bpmn/cadastro-usuario.bpmn")
public class MercadolivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercadolivreApplication.class, args);
    }

}
