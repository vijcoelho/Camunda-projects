package com.project.mercadolivre.wokers;

import com.project.mercadolivre.entity.Usuario;
import com.project.mercadolivre.repository.UsuarioRepository;
import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalvarUsuarioPendenteWorker {

    private final UsuarioRepository usuarioRepository;

    @JobWorker(type = "salvar-usuario-pendente")
    public Map<String, Object> salvar(
            @Variable String userId,
            @Variable String nome,
            @Variable String email,
            @Variable String senha,
            @Variable String tokenConfirmacao
    ) {
        log.info("SalvarUsuarioPendenteWorker.salvar - Salvando usuário pendente: {}", email);

        var usuario = Usuario.builder()
                .id(UUID.fromString(userId))
                .nome(nome)
                .email(email)
                .senha(senha)
                .tokenConfirmacao(tokenConfirmacao)
                .status(Usuario.StatusUsuario.PENDENTE)
                .criadoEm(LocalDateTime.now())
                .build();

        usuarioRepository.save(usuario);

        return Map.of("usuarioSalvo", true);
    }
}
