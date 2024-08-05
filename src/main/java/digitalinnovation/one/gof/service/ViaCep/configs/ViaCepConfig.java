package digitalinnovation.one.gof.service.ViaCep.configs;

import digitalinnovation.one.gof.domain.dto.EnderecoDTO;
import digitalinnovation.one.gof.service.ViaCep.ViaCepService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ViaCepConfig {

    @Bean
    ViaCepService viaCepService() {
        RestClient restClient = RestClient.create("https://viacep.com.br");
        HttpServiceProxyFactory factory =  HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(ViaCepService.class);
    }

    @Bean
    CommandLineRunner runner (ViaCepService viaCepService) {
        return args -> {
            EnderecoDTO enderecoDTO = viaCepService.consultarCep("23095625");
            System.out.println(enderecoDTO);
        };
    }

}
