package com.example.crud.delegate;

import com.example.crud.service.UsuarioService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlterarDelegate implements JavaDelegate {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");
        String novaSenha = (String) delegateExecution.getVariable("novaSenha");
        String confirmarSenha = (String) delegateExecution.getVariable("confirmarSenha");

        System.out.println(usuarioService.alterar(email, novaSenha, confirmarSenha));
    }
}
