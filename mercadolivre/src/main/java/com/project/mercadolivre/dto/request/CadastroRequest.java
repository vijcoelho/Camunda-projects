package com.project.mercadolivre.dto.request;

public record CadastroRequest(
        String nome,
        String email,
        String senha
) {
}
