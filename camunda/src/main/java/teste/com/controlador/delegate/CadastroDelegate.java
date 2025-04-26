package teste.com.controlador.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import teste.com.controlador.entity.Usuario;
import teste.com.controlador.service.UsuarioService;


@Component("cadastroDelegate")
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
        usuario.setStatus("ANALISE");
        usuario.setProcessId(execution.getProcessInstanceId());
        usuarioService.cadastro(usuario);

        System.out.println("Usuário salvo no MongoDB: " + usuario);
    }
}
