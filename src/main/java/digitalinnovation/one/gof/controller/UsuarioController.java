package digitalinnovation.one.gof.controller;

import digitalinnovation.one.gof.domain.dto.UsuarioDTO;
import digitalinnovation.one.gof.domain.entities.Usuario;
import digitalinnovation.one.gof.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> inserir(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.inserir(usuarioDTO));
    }

    @GetMapping()
    public ResponseEntity<Iterable<Usuario>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(id, usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
