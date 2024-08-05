package digitalinnovation.one.gof.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(@NotBlank String nome, @NotBlank String cep) {
}
