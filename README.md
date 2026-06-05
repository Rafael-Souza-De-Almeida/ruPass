# 💳 ruPass

<img width="1887" height="908" alt="Captura de tela 2026-06-05 191059" src="https://github.com/user-attachments/assets/6ecc0636-cb31-458e-9cb6-42fa683d194c" />

O **ruPass** é um sistema digital de identificação e gestão de créditos voltado para Restaurantes Universitários. Ele tem como objetivo modernizar o acesso dos estudantes, substituindo as carteirinhas físicas e o uso de dinheiro em espécie por uma solução totalmente digital, integrada e segura.

---

## ✨ Funcionalidades

### Gestão de Carteira (Wallet)
Sistema de recarga e controle de saldo para refeições.
<img width="1887" height="908" alt="Captura de tela 2026-06-05 191059" src="https://github.com/user-attachments/assets/e210a601-cc86-4116-a061-d84df86c031b" />

### Autenticação Segura
Login e controle de sessão gerenciados via tokens JWT.
<img width="1392" height="782" alt="image" src="https://github.com/user-attachments/assets/b0d8ea40-7c18-4855-9510-40a9823a7464" />

### Carrinho de compras
<img width="1492" height="519" alt="image" src="https://github.com/user-attachments/assets/a74b84a2-0881-4761-93c5-b33123f81c70" />

### Histórico de compras
<img width="1271" height="301" alt="image" src="https://github.com/user-attachments/assets/469cdb07-ecd4-4a0a-a417-bc881f129adb" />

---

## 🛠️ Tecnologias Utilizadas

Este projeto utiliza uma arquitetura *Monorepo*, abrigando tanto o frontend quanto o backend no mesmo repositório para facilitar o desenvolvimento integrado.

### Frontend
* **Framework:** Next.js (React)
* **Linguagem:** TypeScript / JavaScript
* **Ambiente:** Node.js 20+
* **Estilização:** Tailwind CSS

### Backend
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Build Tool:** Gradle
* **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security + JWT

### Infraestrutura
* **Containers:** Docker e Docker Compose
* Utilização de arquitetura hexagonal no backend.

---

## 📂 Estrutura do Projeto

```text
ruPass/
├── backend/               # API em Spring Boot (Java 21)
├── frontend/              # Aplicação Web em Next.js (Node 20)
├── docker-compose.yml     # Orquestração dos containers (DB, API, Web)
└── README.md
```

## 🚀 Como Executar o Projeto Localmente
A maneira mais fácil e rápida de rodar a aplicação completa (Banco de Dados, Backend e Frontend) é utilizando o Docker.

### Pré-requisito:
* Docker e Docker Compose instalados na sua máquina.
* Portas 3000, 8080 e 5432 disponíveis.

## Passo a passo

### 1. Clone este repositório:

```text
git clone [https://github.com/Rafael-Souza-De-Almeida/ruPass.git](https://github.com/Rafael-Souza-De-Almeida/ruPass.git)
```

### 2. Acesse a pasta do projeto:

```text
cd ruPass
```

### 3. Gerar as chaves JWT

Por questões de segurança, as chaves de criptografia não são enviadas para o repositório. Antes de rodar o projeto, você precisa gerar um par de chaves RSA na pasta de recursos do backend.

1. Navegue até a pasta de recursos:
   `cd backend/src/main/resources`

2. Gere a chave privada e a pública usando o OpenSSL:
   `openssl genrsa -out app.key 2048`
   `openssl rsa -in app.key -pubout -out app.pub`

3. Volte para a raiz do projeto para o próximo passo:
   `cd ../../../../`

### 4. Suba todos os containers com o Docker Compose:

```text
docker compose up --build
```

### 5. Acesse o serviço nos seguintes enndereços:
* Frontend: http://localhost:3000
* Backend (API): http://localhost:8080

## 🔐 Configuração de Variáveis de Ambiente
O projeto utiliza variáveis de ambiente geradas dinamicamente pelo Docker, mas caso deseje rodar os projetos isoladamente fora dos containers, atente-se aos seguintes arquivos:

Backend: As chaves RSA para o JWT (app.pub e app.key) devem estar presentes em backend/src/main/resources/. As credenciais do banco podem ser sobreescritas no application.properties.
Frontend: Crie um arquivo .env.local na pasta frontend/ com a URL da API, caso necessário (ex: NEXT_PUBLIC_API_URL=http://localhost:8080).
