package com.ambydata.ambydata_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação principal do Spring Boot — ativa três configurações de uma vez:
//   @Configuration       → permite definir beans e configurações na aplicação
//   @EnableAutoConfiguration → configura automaticamente o Spring com base nas dependências do pom.xml
//   @ComponentScan       → escaneia todos os pacotes abaixo deste em busca de @Controller, @Service, @Repository, etc.
@SpringBootApplication
public class AmbydataBackendApplication {

    // Ponto de entrada da aplicação — método main padrão do Java
    public static void main(String[] args) {

        // Inicializa todo o ecossistema Spring Boot:
        //   → Sobe o servidor Tomcat embutido (padrão: porta 8080)
        //   → Conecta ao banco de dados MySQL via application.properties
        //   → Registra todos os Controllers, Services e Repositories
        //   → Deixa a API pronta para receber requisições
        SpringApplication.run(AmbydataBackendApplication.class, args);
    }
}