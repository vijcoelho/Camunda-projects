package com.project.mercadolivre.service;

import com.project.mercadolivre.dto.request.CadastroRequest;
import com.project.mercadolivre.repository.UsuarioRepository;
import io.camunda.client.CamundaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessoCadastroService {

    private final CamundaClient camundaClient;
    private final UsuarioRepository usuarioRepository;

    public String iniciarCadastro(CadastroRequest request) {
        String userId = UUID.randomUUID().toString();
        String tokenConfirmacao = UUID.randomUUID().toString();

        log.info("ProcessoCadastroService.iniciarCadastro - Iniciando cadastro para o usuario: email {}", request.email());

        var instance = camundaClient
                .newCreateInstanceCommand()
                .bpmnProcessId("cadastro-usuario")
                .latestVersion()
                .variables(Map.of(
                        "userId", userId,
                        "nome", request.nome(),
                        "email", request.email(),
                        "senha", request.senha(),
                        "tokenConfirmacao", tokenConfirmacao
                ))
                .send()
                .join();

        log.info("ProcessoCadastroService.iniciarCadastro - Processo no Camunda criado: key {}", instance.getProcessInstanceKey());
        return String.valueOf(instance.getProcessInstanceKey());
    }

    public void confirmarEmail(String token) {
        var usuario = usuarioRepository.findByTokenConfirmacao(token)
                .orElseThrow(() -> new RuntimeException("Token nao encontrado!"));

        log.info("ProcessoCadastroService.confirmarEmail - Correlacionando mensagem para userId: {}", usuario.getId());

        camundaClient
                .newPublishMessageCommand()
                .messageName("email-confirmado")
                .correlationKey(usuario.getId().toString())
                .send()
                .join();

        log.info("ProcessoCadastroService.confirmarEmail - Zeebe acordou o processo! Conta será ativada.");
    }
}
