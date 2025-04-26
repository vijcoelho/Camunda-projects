package teste.com.primeiro.service;

import org.springframework.stereotype.Service;
import teste.com.primeiro.entity.Usuario;
import teste.com.primeiro.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
