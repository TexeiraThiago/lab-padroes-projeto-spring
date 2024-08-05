package digitalinnovation.one.gof.service.usuario.impl;

import digitalinnovation.one.gof.controller.UsuarioController;
import digitalinnovation.one.gof.domain.dto.EnderecoDTO;
import digitalinnovation.one.gof.domain.dto.UsuarioDTO;
import digitalinnovation.one.gof.domain.entities.Endereco;
import digitalinnovation.one.gof.domain.entities.Usuario;
import digitalinnovation.one.gof.repository.EnderecoRepository;
import digitalinnovation.one.gof.repository.UsuarioRepository;
import digitalinnovation.one.gof.service.ViaCep.ViaCepService;
import digitalinnovation.one.gof.service.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsuarioImpl implements UsuarioService {

    private final UsuarioRepository usarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public UsuarioImpl(UsuarioRepository usarioRepository, EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.usarioRepository = usarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Usuario> buscarTodos() {
        Iterable<Usuario> usuarios = usarioRepository.findAll();
        if (usuarios != null) {
            usuarios.forEach(usuario -> {
                UUID id = usuario.getId();
                usuario.add(linkTo(methodOn(UsuarioController.class)
                        .buscarPorId(id))
                        .withSelfRel());
            });
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorId(UUID id) {
        Optional<Usuario> optionalUsuario = usarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        optionalUsuario.get().add(linkTo(methodOn(UsuarioController.class).buscarTodos()).withRel("Lista de Usuarios"));
        return optionalUsuario.get();
    }

    @Transactional
    @Override
    public Usuario inserir(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        String cep = usuarioDTO.cep();
        Endereco endereco = validarEndereco(cep);
        usuario.setEndereco(endereco);
        usarioRepository.save(usuario);

        return usuario;
    }

    @Override
    public Usuario atualizar(UUID id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não existe"));
        usuario.setNome(usuarioDTO.nome());
        String cep = usuarioDTO.cep();
        Endereco endereco = validarEndereco(cep);
        usuario.setEndereco(endereco);
        usarioRepository.save(usuario);
        return usuario;
    }

    @Transactional
    @Override
    public void deletar(UUID id) {
        Optional<Usuario> optionalUsuario = usarioRepository.findById(id);
        optionalUsuario.ifPresent(usuario -> enderecoRepository.delete(usuario.getEndereco()));
        usarioRepository.deleteById(id);
    }

    private Endereco validarEndereco(String cep) {
        return enderecoRepository.findByCep(cep).orElseGet(() -> {
            EnderecoDTO enderecoDTO = viaCepService.consultarCep(cep);
            Endereco novoEndereco = new Endereco();
            BeanUtils.copyProperties(enderecoDTO, novoEndereco);
            return novoEndereco;
        });
    }
}
