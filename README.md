# javabeans-ia

MVP inicial do simulador JavaBeans com IA (futuro Azure OpenAI).

## Configuração da API Key

Para executar o projeto, é necessário configurar a chave da API DeepSeek:

1. Solicite a chave API (`DEEPSEEK_API_KEY`) com o líder da squad.
2. Defina a variável de ambiente:
   - No Linux/Mac: `export DEEPSEEK_API_KEY=sk-...`
   - No Windows: `set DEEPSEEK_API_KEY=sk-...`
3. Opcionalmente, defina o endpoint: `export DEEPSEEK_ENDPOINT=https://api.deepseek.com/v1`

**Nota:** Nunca commite chaves API no código-fonte. Use variáveis de ambiente.

## Endpoints atuais
- GET /game/health       → "Game is running!" ou similar
- GET /game/cenario      → "Primeiro cenário de teste"

## Como rodar
mvn spring-boot:run

## Tecnologias
- Spring Boot 3.3.4
- Java 21
- Maven
