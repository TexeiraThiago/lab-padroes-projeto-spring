package digitalinnovation.one.gof.repository;

import digitalinnovation.one.gof.domain.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {
}
