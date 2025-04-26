package teste.com.controlador.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import teste.com.controlador.entity.Usuario;
import teste.com.controlador.repository.UsuarioRepository;
import teste.com.controlador.service.UsuarioService;

@Component
public class AprovadoDelegate implements JavaDelegate {
    private final UsuarioRepository usuarioRepository;

    public AprovadoDelegate(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void execute(DelegateExecution execution) {
        String processId = execution.getProcessInstanceId();
        Usuario usuario = usuarioRepository.findByProcessId(processId);

        if (usuario != null) {
            usuario.setStatus("APROVADO");
            usuarioRepository.save(usuario);
            System.out.println("Salvo com sucesso!");
        }
    }
}
