package teste.com.controlador.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import teste.com.controlador.entity.Usuario;
import teste.com.controlador.repository.UsuarioRepository;

@Component
public class ReprovadoDelegate implements JavaDelegate {
    private final UsuarioRepository usuarioRepository;

    public ReprovadoDelegate(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void execute(DelegateExecution execution) {
        String processId = execution.getProcessInstanceId();
        Usuario usuario = usuarioRepository.findByProcessId(processId);

        if (usuario != null) {
            usuarioRepository.delete(usuario);
            System.out.println("usuario deletado!");
        }
    }
}
