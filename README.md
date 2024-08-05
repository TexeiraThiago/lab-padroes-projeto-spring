# Explorando Padrões de Projetos na Prática com Java

Repositório com as implementações dos padrões de projeto explorados no Lab "Explorando Padrões de Projetos na Prática com Java". Especificamente, este projeto explorou alguns padrões usando o Spring Framework, são eles:
- Singleton
- Strategy/Repository
- Facade

Tecnologias Utilizadas

* Java 21
* Spring Boot 3.2.2 
* Spring Data JPA 
* Maven 3 
* Banco de Dados h2
* HATEOS

## Estrutura de diretório

````
digitalinnovation/
└── one/
└── gof/
├── Application.java
├── controller/
│   └── UsuarioController.java
├── domain/
│   ├── dto/
│   │   ├── EnderecoDTO.java
│   │   └── UsuarioDTO.java
│   └── entities/
│       ├── Endereco.java
│       └── Usuario.java
├── repository/
│   ├── EnderecoRepository.java
│   └── UsuarioRepository.java
└── service/
├── ViaCep/
│   ├── ViaCepService.java
│   └── configs/
│       └── ViaCepConfig.java
└── usuario/
    ├── UsuarioService.java
    └── impl/
        └── UsuarioImpl.java
````


## Mermaid

```` mermaid
classDiagram
class Endereco {
UUID id
String cep
String logradouro
String complemento
String bairro
String localidade
String uf
String ibge
String gia
String ddd
String siafi
}

    class Usuario {
        UUID id
        String nome
        Endereco endereco
    }

    Endereco --> Usuario : "1..*" endereço
````