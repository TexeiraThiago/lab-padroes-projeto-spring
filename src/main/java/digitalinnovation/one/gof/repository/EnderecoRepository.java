package digitalinnovation.one.gof.repository;

import digitalinnovation.one.gof.domain.entities.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends CrudRepository<Endereco, UUID> {

    Optional<Endereco> findByCep(String Cep);
}
