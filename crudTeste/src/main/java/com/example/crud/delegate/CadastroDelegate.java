package com.example.crud.delegate;

import com.example.crud.model.Usuario;
import com.example.crud.service.UsuarioService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroDelegate implements JavaDelegate {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String nome = (String) delegateExecution.getVariable("nome");
        String email = (String) delegateExecution.getVariable("email");
        String senha = (String) delegateExecution.getVariable("senha");

        Usuario usuario = new Usuario(nome, email, senha);

        System.out.println(this.usuarioService.cadastro(usuario));
    }
}
