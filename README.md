# AmbyData — Backend

Backend do **AmbyData**, um dashboard de monitoramento industrial em tempo real: captura dados de máquinas/produtos e acompanha OEE, produtividade, lucros, desvios, necessidade de manutenção e temperatura.

Este repositório contém apenas a API. O frontend está em [`Ambydata-frontend`](https://github.com/ErickWL/Ambydata-react).

## Stack

- Java 17
- Spring Boot 4.0.6 (Web MVC + Data JPA)
- MySQL (via `mysql-connector-j`)
- Lombok
- Maven (wrapper incluso — não precisa Maven instalado)
- Docker (Dockerfile incluso)

## Pré-requisitos

- JDK 17+
- MySQL rodando localmente (ou acessível via rede)
- Docker (opcional, para rodar em container)

## Instalação e execução

Clone o repositório:

```bash
git clone https://github.com/ErickWL/Ambydata-backend.git
cd Ambydata-backend
```

Configure a conexão com o banco em `src/main/resources/application.properties` (ou `application.yml`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambydata
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

Rode a aplicação com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API sobe por padrão em `http://localhost:8080`.

### Rodando com Docker

```bash
docker build -t ambydata-backend .
docker run -p 8080:8080 ambydata-backend
```

## Estrutura do projeto

```
src/
  main/
    java/com/ambydata/   # controllers, models, repositories, services
    resources/            # application.properties, configs
  test/                   # testes
pom.xml
Dockerfile
```

## Roadmap / Status

Projeto em desenvolvimento ativo — API e schema do banco ainda sujeitos a mudanças.
