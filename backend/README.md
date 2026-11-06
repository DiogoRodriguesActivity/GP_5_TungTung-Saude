## Getting Started

## Executar o web app com Spring Boot

O backend usa Java 17 e Maven. A partir da pasta `backend`, execute:

```powershell
mvn spring-boot:run
```

Depois acesse:

- Aplicação: http://localhost:8080/
- API de verificação: http://localhost:8080/api/health
- CRUD de pacientes: http://localhost:8080/pacientes
- CRUD de profissionais: http://localhost:8080/profissionais
- CRUD de consultas: http://localhost:8080/consultas
- CRUD de internacoes: http://localhost:8080/internacoes
- CRUD de quartos: http://localhost:8080/quartos

Os recursos suportam:

- `GET /{recurso}` para listar
- `GET /{recurso}/{id}` para buscar
- `POST /{recurso}` para cadastrar
- `PUT /{recurso}/{id}` para atualizar
- `DELETE /{recurso}/{id}` para excluir

O frontend existente continua em `frontend/`. Spring Boot o serve diretamente
e os controladores REST ficam em `src/main/java/com/tungtungsaude/api`.

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
