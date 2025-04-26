package teste.com.controlador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import teste.com.controlador.entity.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query("{ 'processId' : ?0 }")
    Usuario findByProcessId (String processId);
}
