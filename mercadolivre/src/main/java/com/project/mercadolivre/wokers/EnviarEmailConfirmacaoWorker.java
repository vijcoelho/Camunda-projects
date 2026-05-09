package com.project.mercadolivre.wokers;

import com.project.mercadolivre.dto.request.EmailMessage;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnviarEmailConfirmacaoWorker {

    private final StreamBridge streamBridge;

    @JobWorker(type = "enviar-email-confirmacao")
    public void enviar(
            @Variable String email,
            @Variable String nome,
            @Variable String tokenConfirmacao
    ) {
        String link = "http://localhost:8081/usuarios/confirmar?token=" + tokenConfirmacao;

        var mensagem = new EmailMessage(
                email,
                "Confirme seu cadastro",
                "Olá " + nome + "! Confirme aqui: " + link
        );

        streamBridge.send("email-confirmacao-out-0", mensagem);
        log.info("EnviarEmailConfirmacaoWorker.enviar - Mensagem publicada no canal email-confirmacao para: {}", email);
    }
}
