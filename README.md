# 🚀 Sistema de Rastreamento de Encomendas

Este projeto é uma aplicação **full stack** composta por um **backend em Spring Boot (Java)** e um **frontend em React (JavaScript)**.  
O sistema permite:

- 🧾 Cadastrar pedidos (**Orders**)  
- ⚙️ Registrar ocorrências (**Ocurrences**) associadas a um pedido  
- 🔍 Consultar o status de rastreamento das ocorrências  

---

## ⚙️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Validation**
- **Lombok**
- **Mockito / JUnit 5**
- **Jacoco** (relatórios de cobertura de testes)
- **Swagger (OpenAPI)**

### Frontend
- **React 18**
- **React Router DOM**
- **CSS**
- **Fetch API**

---

## 🧱 Funcionalidades Principais

### 📦 Módulo de Encomendas (Orders)
- Cadastrar uma nova encomenda com código de rastreio, nome do cliente e endereço.

### ⚙️ Módulo de Ocorrências (Ocurrences)
- Registrar uma nova ocorrência vinculada a uma encomenda existente.  
- Consultar o status de rastreamento por código de rastreio.

### 📊 Testes
- Cobertura de testes com **Jacoco** (Controllers, Services, Mappers, DTOs e Enums).  
- Testes executados com **Mockito** e **Spring Boot Test**.

---

## 🧰 Como Executar o Projeto

### 🔹 1. Pré-requisitos

Certifique-se de ter instalado:
- [Java 21+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [Node.js 18+](https://nodejs.org/)
- [npm](https://www.npmjs.com/)

---

### 🔹 2. Executar o Backend

1. Abra o terminal dentro da pasta `backend`
2. Rode o comando:

   ```bash
   mvn spring-boot:run
3. O servidor será iniciado em: http://localhost:8080
4. Documentação Swagger em: http://localhost:8080/swagger-ui.html

### 🔹 3. Executar o Frontend

1. Vá até a pasta frontend
2. Instale as dependências com o comando
   ```bash
   npm install
4. Inicie o servidor React com o comando
    ```bash
   npm start
5. A aplicação será iniciada em: http://localhost:3000

⚠️ O backend deve estar rodando antes de iniciar o frontend.

## Endpoints

<img width="1442" height="282" alt="swagger" src="https://github.com/user-attachments/assets/71fa928f-d3bc-445a-851d-cdd2ab983779" />

## Executar testes e gerar relatório JaCoCo

1. Rode o comando:

   ```bash
   mvn clean test
2. Após rodar, abra o relatório em: backend/target/site/jacoco/index.html

## Estrutura das telas

| Tela                                            | Descrição                                               |
| ----------------------------------------------- | ------------------------------------------------------- |
| 🧾 **Cadastro de Pedido (OrderForm)**           | Permite cadastrar novos pedidos.                        |
| ⚙️ **Cadastro de Ocorrência (OcurrenceForm)**   | Permite registrar ocorrências associadas a pedidos.     |
| 🔍 **Consulta de Ocorrências (TrackingStatus)** | Permite consultar o status de rastreamento pelo código. |

## 👩‍💻 Autora

Clara Santos
Desenvolvedora Java Backend
💻 GitHub: Clara-Santos02
