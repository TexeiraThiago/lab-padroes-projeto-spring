package digitalinnovation.one.gof.service.ViaCep;

import digitalinnovation.one.gof.domain.dto.EnderecoDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ViaCepService {

    @GetExchange("/ws/{cep}/json")
    EnderecoDTO consultarCep(@PathVariable String cep);
}
