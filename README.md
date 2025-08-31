# BFF SLS

Este projeto é um Backend For Frontend (BFF) desenvolvido em Java com Spring Boot, focado em experimentos de resiliência e otimização de chamadas a sistemas legados. O objetivo é servir como base para testes e análise de técnicas modernas de tolerância a falhas, desempenho e escalabilidade em arquiteturas distribuídas.

## Versionamento das Técnicas

O projeto está versionado por controllers e services, permitindo testar incrementalmente diferentes estratégias:

- **v1**: BFF puro, sem técnicas de resiliência.
- **v2**: Circuit Breaker + Fallback (Resilience4j).
- **v3**: Retentativas automáticas (Retry - Resilience4j).
- **v4**: Caching (Redis + Resilience4j).
- **v5**: Paralelização de chamadas (Java CompletableFuture).
- **v6**: Comunicação assíncrona (Spring @Async).

Cada versão possui seu próprio controller (`BffControllerVx`) e service (`LegadoServiceVx`), expondo endpoints separados para facilitar testes e coleta de resultados.

## Estrutura do Projeto

- **src/main/java/com/example/bff/controller**: Controllers REST versionados.
- **src/main/java/com/example/bff/service**: Services versionados, cada um implementando uma técnica específica.
- **src/main/resources/application.properties**: Configurações de portas, URLs, cache, retry, etc.

## Como executar

1. Certifique-se de ter Java 17+ e Maven instalados.
2. Inicie o Redis localmente para testar caching (ex: `docker run -d -p 6379:6379 redis`).
3. Execute o BFF:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse os endpoints conforme a versão desejada:
   - `/v1/*` (puro)
   - `/v2/*` (circuit breaker)
   - `/v3/*` (retry)
   - `/v4/*` (cache)
   - `/v5/parallel` (paralelo)
   - `/v6/*-async` (assíncrono)

## Observações

- O projeto é modular e pode ser expandido para novas técnicas.
- Recomenda-se consultar a documentação do Resilience4j e do Spring para ajustes finos.

---

Projeto desenvolvido para fins acadêmicos e experimentais.
