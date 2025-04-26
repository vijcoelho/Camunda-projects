package teste.com.primeiro.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import teste.com.primeiro.entity.Usuario;
import teste.com.primeiro.service.UsuarioService;

@Component
public class CadastroDelegate implements JavaDelegate {
    private final UsuarioService usuarioService;

    public CadastroDelegate(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void execute(DelegateExecution execution) {
        String nome = (String) execution.getVariable("nome");
        Long idade = (Long) execution.getVariable("idade");

        Usuario usuario = new Usuario(nome, idade);

        usuarioService.cadastrar(usuario);

        System.out.println("cadastro realizado!");
    }
}
