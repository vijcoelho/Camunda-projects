package com.project.mercadolivre.wokers;

import com.project.mercadolivre.repository.UsuarioRepository;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidarDadosWorker {

    private final UsuarioRepository usuarioRepository;

    @JobWorker(type = "validar-dados-usuario")
    public Map<String, Object> validar(
        @Variable String email,
        @Variable String nome,
        @Variable String senha
    ) {
        log.info("ValidarDadosWorker.validar - Validando dados para: email {}", email);

        if (usuarioRepository.existsByEmail(email)) {
//            throw new BpmnError("EMAIL_JA_CADASTRADO", "Email já está em uso");
             return Map.of(
                     "dadosValidos", false,
                     "motivoRejeicao", "Email já cadastrado"
             );
        }

        if (nome == null || nome.isBlank()) {
//            throw new BpmnError("DADOS_INVALIDOS", "Nome é obrigatório");
            return Map.of(
                    "dadosValidos", false,
                    "motivoRejeicao", "Nome é obrigatório"
            );
        }

        if (senha == null || senha.length() < 6) {
//            throw new BpmnError("DADOS_INVALIDOS", "Senha muito curta");
            return Map.of(
                    "dadosValidos", false,
                    "motivoRejeicao", "Senha deve ter no mínimo 6 caracteres"
            );
        }

        return Map.of("dadosValidos", true);
    }
}
