package teste.com.controlador.service;

import org.springframework.stereotype.Service;
import teste.com.controlador.entity.Usuario;
import teste.com.controlador.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastro(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario acharPeloIdDoProcesso(String processId) {
        return usuarioRepository.findByProcessId(processId);
    }
}
