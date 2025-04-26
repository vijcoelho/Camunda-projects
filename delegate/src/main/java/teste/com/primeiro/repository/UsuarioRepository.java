package teste.com.primeiro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import teste.com.primeiro.entity.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
