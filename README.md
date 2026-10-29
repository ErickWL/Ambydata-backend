# AmbyData — Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.9-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green)
![License](https://img.shields.io/badge/license-uso%20pessoal%2Facad%C3%AAmico-lightgrey)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Backend do **AmbyData**, um dashboard de monitoramento industrial. Este repositório contém a API desenvolvida em Java e Spring Boot. O frontend está em [`Ambydata-frontend`](https://github.com/ErickWL/Ambydata-react).

## Stack

- Java 17+
- Spring Boot 4.0.9
- Spring Web MVC
- Spring Data MongoDB
- Spring Security
- BCrypt para hash de senhas
- JJWT — dependências presentes, mas a emissão e validação de JWT ainda não estão implementadas
- Lombok
- Maven Wrapper
- Docker

## Pré-requisitos

- JDK 17 ou superior
- Instância MongoDB acessível pela aplicação, como MongoDB Atlas ou MongoDB local
- Docker, opcional

## Instalação e execução

Clone o repositório:

```bash
git clone https://github.com/ErickWL/Ambydata-backend.git
cd Ambydata-backend
```

### Configuração

A aplicação lê a URI do MongoDB pela variável de ambiente `MONGODB_URI`. O banco configurado é `pi`, e a coleção de usuários é `usuario`.

O `application.yml` também define a propriedade `JWT_SECRET`. Embora as dependências JWT estejam no projeto, a autenticação por token ainda não foi implementada.

Configure as variáveis no terminal que iniciará o backend. Não coloque credenciais no GitHub.

No PowerShell:

```powershell
$env:MONGODB_URI = "mongodb+srv://<usuario>:<senha>@<cluster>/?retryWrites=true&w=majority"
$env:JWT_SECRET = "<segredo-local>"
```

No Bash:

```bash
export MONGODB_URI="mongodb+srv://<usuario>:<senha>@<cluster>/?retryWrites=true&w=majority"
export JWT_SECRET="<segredo-local>"
```

Substitua os valores de exemplo pelos dados do seu ambiente. Um arquivo `.env` local é ignorado pelo Git, mas o Spring Boot não carrega automaticamente esse arquivo sem configuração adicional.

### Executando a aplicação

Com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A API fica disponível em `http://localhost:8080`.

### Executando os testes

```bash
./mvnw test
```

No Windows:

```powershell
.\mvnw.cmd test
```

Configure as variáveis de ambiente antes de executar a aplicação ou os testes, caso sejam necessárias para carregar o contexto do Spring.

### Executando com Docker

Construa a imagem:

```bash
docker build -t ambydata-backend .
```

Inicie o container, informando as variáveis de ambiente:

```bash
docker run --rm -p 8080:8080 \
  -e MONGODB_URI="mongodb+srv://<usuario>:<senha>@<cluster>/?retryWrites=true&w=majority" \
  -e JWT_SECRET="<segredo-local>" \
  ambydata-backend
```

No PowerShell, o comando pode ser escrito assim:

```powershell
docker run --rm -p 8080:8080 `
  -e "MONGODB_URI=mongodb+srv://<usuario>:<senha>@<cluster>/?retryWrites=true&w=majority" `
  -e "JWT_SECRET=<segredo-local>" `
  ambydata-backend
```

Não publique credenciais reais em comandos, capturas de tela ou arquivos versionados.

## Banco de dados

O backend atual usa **MongoDB**, não MySQL nem JPA.

- URI: variável de ambiente `MONGODB_URI`
- Banco: `pi`
- Coleção de usuários: `usuario`

## Endpoints implementados

| Método | Rota | Descrição | Acesso atual |
|--------|------|-----------|--------------|
| GET | `/api/usuarios` | Lista os usuários, retornando `UsuarioResponse` sem o hash da senha | Público temporariamente |
| POST | `/api/usuarios` | Cria um usuário e aplica BCrypt à senha recebida | Requer autenticação Spring Security |
| POST | `/api/usuarios/login` | Valida e-mail, senha e estado ativo do usuário | Público |

O login recebe JSON neste formato:

```json
{
  "email": "usuario@exemplo.com",
  "senha": "senha-original"
}
```

Envie a senha original em texto no corpo da requisição; não envie o hash BCrypt armazenado no banco. O endpoint de login retorna os dados públicos do usuário quando as credenciais são válidas. Ele ainda não emite JWT.

## Autenticação e segurança

O projeto usa Spring Security e BCrypt. Atualmente:

- `POST /api/usuarios/login` está liberado para permitir o login.
- `GET /api/usuarios` também está público temporariamente para testes. Essa rota deve ser protegida antes de disponibilizar a API em produção.
- As demais requisições exigem autenticação Spring Security.
- A autenticação JWT ainda não está implementada, apesar das dependências JJWT e da propriedade `JWT_SECRET` estarem configuradas.
- O login rejeita usuários inativos e senhas que não correspondam ao hash BCrypt salvo.

Antes de produção, implemente a emissão e validação de tokens e revise as permissões das rotas.

## Métricas

A rota antiga `/api/dados` não está implementada na versão atual: o controller e o repositório de métricas estão comentados. A configuração atual do backend não usa MySQL/JPA.

## Estrutura do projeto

```text
src/
  main/
    java/com/ambydata/ambydata_backend/
      config/       # Configurações, incluindo o PasswordEncoder
      controller/   # Endpoints REST
      dto/          # Objetos de entrada e resposta
      model/        # Modelos MongoDB
      repository/   # Repositórios Spring Data MongoDB
      security/     # Configuração de acesso do Spring Security
      service/      # Regras de negócio
    resources/
      application.yml
  test/
pom.xml
Dockerfile
mvnw
mvnw.cmd
```

## Roadmap

- [ ] Implementar emissão e validação de JWT
- [ ] Proteger a listagem de usuários e revisar o acesso às demais rotas
- [ ] Adicionar testes para cadastro e autenticação
- [ ] Documentar a API com OpenAPI/Swagger
- [ ] Configurar CI para build e testes

## Licença

Projeto de uso pessoal/acadêmico. Não distribuído sob licença open source.
