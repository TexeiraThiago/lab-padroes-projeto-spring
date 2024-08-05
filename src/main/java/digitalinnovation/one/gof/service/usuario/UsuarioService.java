package digitalinnovation.one.gof.service.usuario;

import digitalinnovation.one.gof.domain.dto.UsuarioDTO;
import digitalinnovation.one.gof.domain.entities.Usuario;

import java.util.UUID;

public interface UsuarioService {
    Iterable<Usuario> buscarTodos();

    Usuario buscarPorId(UUID id);

    Usuario inserir(UsuarioDTO usuarioDTO);

    Usuario atualizar(UUID id, UsuarioDTO usuarioDTO);

    void deletar(UUID id);
}
