# Spring Boot Rest API Boilerplate 

Micro serviço que tem como objetivo ser um guia para a criação de APIs Rest com Spring Boot e Gradle, a ideia é que esse projeto seja atualizado constantemente conforme a tecnologia vai avançando e novas práticas vão surgindo   

## 🛠 Tecnologias
- **Java 21**
- **Spring Boot**
- **Gradle 8.12.1**

## 📋 Requisitos
Antes de rodar a aplicação, certifique-se de que seu ambiente contém:

### 🔹 Pré-requisitos
- **Java 21** instalado e configurado no `PATH`.
- **Gradle 8.12.1** (opcional, pois o projeto usa o Wrapper do Gradle).
- **Git** instalado para clonar o repositório (opcional).

### 🔹 Dependências Internas
A aplicação utiliza as seguintes tecnologias e dependências gerenciadas pelo Gradle:
- **Spring Boot** (para criação do backend REST).
- **Banco de Dados H2** (banco de dados em memória).
- **Spring Data JPA** (para persistência de dados).
- **Spring Web** (para expor os endpoints REST).
- **Spring Doc OpenAPI** (para documentação Swagger).

## 🚀 Instalação e Execução
### 🔹 Executando com IntelliJ IDEA
Se estiver usando o **IntelliJ IDEA**, basta importar o projeto como um projeto **Gradle** e executar a classe principal da aplicação.

### 🔹 Executando pela Linha de Comando
Caso não utilize o IntelliJ, é possível rodar a aplicação diretamente pelo terminal:

1. **Clone o repositório:**
   ```sh
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_PROJETO>
   ```
2. **Compile e construa o projeto usando Gradle:**
   ```sh
   ./gradlew build
   ```
3. **Execute a aplicação:**
   ```sh
   ./gradlew bootRun
   ```

A aplicação estará disponível em `http://localhost:8081`

## 📌 Uso
- **Swagger Open UI:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Swagger API Docs:** [http://localhost:8081/api-docs](http://localhost:8081/api-docs)
- **H2 Database Console:** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)

## 📂 Estrutura do Projeto
A estrutura do projeto segue uma abordagem bem definida, separando camadas de **aplicação, domínio, infraestrutura e apresentação**:

```plaintext
application    # Lógica da aplicação
  ├── dto         # Objeto de transferência de dados
  ├── service     # Serviços de aplicação 
  └── utils       # Utilitários gerais

domain        # Camada de domínio (regras de negócio puras)
  ├── model       # Entidades do domínio
  ├── enums       # Enumerações do domínio
  ├── exception   # Exceções customizadas
  └── utils       # Utilitários do domínio

infrastructure # Infraestrutura e integrações externas
  ├── repository  # Repositórios de dados
  ├── utils       # Utilitários para integrações externas
  └── config      # Configurações gerais da aplicação

presentation  # Interface com o usuário
  ├── controller  # Controladores REST
  └── exception   # Tratamento de erros (ResponseExceptionHandler)
```

## 🤝 Contribuição
Se desejar contribuir, sinta-se à vontade para abrir um **Pull Request** com melhorias, correções de bugs ou novas funcionalidades.
