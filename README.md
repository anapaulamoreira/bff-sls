# BFF SLS

Este projeto é um exemplo de Backend For Frontend (BFF) desenvolvido em Java utilizando o framework Spring Boot. O objetivo do BFF é servir como uma camada intermediária entre o frontend e os serviços de backend/legado, facilitando a integração, agregação de dados e adaptação de APIs para as necessidades do frontend.

## Estrutura do Projeto

- **src/main/java/com/example/bff**: Código-fonte principal da aplicação.
  - **controller**: Contém os controladores REST (ex: `BffController.java`).
  - **service**: Contém as classes de serviço responsáveis pela lógica de negócio e integração com sistemas legados (ex: `LegadoService.java`).
- **src/main/resources**: Arquivos de configuração, como `application.properties`.
- **src/test/java/com/example/bff**: Testes automatizados.

## Como executar

1. Certifique-se de ter o Java 17+ e o Maven instalados.
2. No terminal, execute:
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou, no Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```
3. A aplicação estará disponível em `http://localhost:8080`.

## Configuração

As configurações podem ser ajustadas no arquivo `src/main/resources/application.properties`.

## Testes

Para rodar os testes automatizados:
```bash
./mvnw test
```
