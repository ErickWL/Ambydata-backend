# AmbyData — Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![License](https://img.shields.io/badge/license-uso%20pessoal%2Facad%C3%AAmico-lightgrey)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Backend do **AmbyData**, um dashboard de monitoramento industrial em tempo real: captura dados de máquinas/produtos e acompanha OEE, produtividade, lucros, desvios, necessidade de manutenção e temperatura.

Este repositório contém apenas a API. O frontend está em [`Ambydata-frontend`](https://github.com/ErickWL/Ambydata-react).

## Stack

- Java 17
- Spring Boot 4.0.6 (Web MVC + Data JPA)
- MySQL 8.x (via `mysql-connector-j`)
- Lombok
- Maven (wrapper incluso — não precisa Maven instalado)
- Docker (Dockerfile incluso)

## Pré-requisitos

- JDK 17+
- MySQL 8.x rodando localmente (ou acessível via rede)
- Docker (opcional, para rodar em container)

## Instalação e execução

Clone o repositório:

```bash
git clone https://github.com/ErickWL/Ambydata-backend.git
cd Ambydata-backend
```

### Configuração do banco de dados

Copie o arquivo de exemplo e preencha com suas credenciais:

```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
```

`application-example.properties` (versionado, sem segredos):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambydata
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

> ⚠️ `application.properties` com credenciais reais **não deve ser commitado**. Certifique-se de que está no `.gitignore`. Prefira exportar `DB_USERNAME` e `DB_PASSWORD` como variáveis de ambiente em vez de colocar valores fixos no arquivo.

O schema é criado/atualizado automaticamente pelo Hibernate (`ddl-auto=update`). Não há migrations versionadas (Flyway/Liquibase) neste momento — isso está no roadmap.

### Rodando a aplicação

Com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API sobe por padrão em `http://localhost:8080`.

### Rodando os testes

```bash
./mvnw test
```

### Rodando com Docker

```bash
docker build -t ambydata-backend .
docker run -p 8080:8080 \
  -e DB_USERNAME=seu_usuario \
  -e DB_PASSWORD=sua_senha \
  ambydata-backend
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

## Principais endpoints

> Lista baseada nos controllers atuais. Atualize conforme o projeto evoluir. Recomenda-se adicionar Swagger/OpenAPI (`springdoc-openapi`) para documentação automática e interativa.

| Método | Rota                    | Descrição                                                  |
|--------|-------------------------|-------------------------------------------------------------|
| GET    | `/api/usuarios`         | Lista todos os usuários cadastrados                          |
| POST   | `/api/usuarios`         | Cria um novo usuário                                          |
| POST   | `/api/usuarios/login`   | Verifica e-mail/senha (retorna 200 + usuário ou 401)          |
| GET    | `/api/dados`            | Lista todas as métricas registradas                           |

## Autenticação

⚠️ **A API não possui autenticação real implementada.**

Existe um endpoint `POST /api/usuarios/login` que valida e-mail/senha contra o banco, mas ele **não emite token** (JWT ou outro) — apenas retorna o objeto `Usuario` com status 200 se as credenciais baterem, ou 401 caso contrário. Isso significa que:

- Todas as rotas estão abertas — não há verificação de sessão/token nas requisições seguintes.
- Não há `SecurityConfig`, filtros JWT ou anotações `@PreAuthorize`/`@Secured` no projeto.

**Recomendado para produção:** implementar emissão e validação de JWT (ex.: `io.jsonwebtoken:jjwt` + um `OncePerRequestFilter`), ou usar `spring-boot-starter-security` com um `SecurityFilterChain` que exija token nas rotas protegidas.

## Roadmap

- [ ] Implementar autenticação real (JWT ou Spring Security) — atualmente `/login` não emite token e as rotas não são protegidas
- [ ] Migrations versionadas (Flyway ou Liquibase) no lugar de `ddl-auto=update`
- [ ] Documentação OpenAPI/Swagger
- [ ] Testes automatizados com cobertura
- [ ] CI (build + testes em pull requests)

## Licença

Projeto de uso pessoal/acadêmico. Não distribuído sob licença open source.
