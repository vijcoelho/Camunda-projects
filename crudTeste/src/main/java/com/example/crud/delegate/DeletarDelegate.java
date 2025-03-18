package com.example.crud.delegate;

import com.example.crud.service.UsuarioService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletarDelegate implements JavaDelegate {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");

        System.out.println(usuarioService.deletar(email));
    }
}
