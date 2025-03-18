package com.example.crud.service;

import com.example.crud.model.Usuario;
import com.example.crud.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastro(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public String alterar(String email, String novaSenha, String confirmarSenha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) return "Sem usuario com este email";
        if (!novaSenha.equals(confirmarSenha)) return "Senhas diferentes!!";

        Usuario usuario = usuarioOpt.get();
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);

        return "Senha alterada com sucesso!";
    }

    public String deletar(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) return "Sem usuario com este email";

        Usuario usuario = usuarioOpt.get();
        usuarioRepository.deleteById(usuario.getIdUsuario());

        return "Usuario deletado com sucesso!";
    }
}
