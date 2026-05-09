package com.project.mercadolivre.dto.request;

public record EmailMessage(
        String email,
        String assunto,
        String texto
) {
}
