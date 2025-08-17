# 📢 API ForumHub

Bem-vindo ao **ForumHub**!  
Esta é uma **API REST** desenvolvida como desafio final de backend do programa **Alura ONE + Oracle Next Education (ONE)**.  

O objetivo foi construir um **sistema de controle de tópicos para um fórum**, aplicando os conceitos mais importantes do desenvolvimento backend com o ecossistema **Spring**.

---

## ✅ Funcionalidades Implementadas
- 🔐 **Segurança**: Sistema de login e registro com geração de token JWT.  
- 🛡️ **Controle de Acesso**: Endpoints protegidos, exigindo um token de autenticação válido.  
- 📄 **Gestão de Tópicos**: CRUD completo com exclusão lógica e validação de propriedade.  
- 💬 **Gestão de Respostas**: CRUD completo para as respostas dos tópicos com validação de propriedade.  
- 👥 **Gestão de Usuários**: Registro e autenticação com diferentes roles.  
- 📚 **Gestão de Cursos**: Sistema básico de cursos para categorização de tópicos.  
- ⚙️ **Tratamento de Erros**: Respostas de erro padronizadas para todos os cenários (400, 401, 403, 404, 500).  
- 📖 **Documentação**: Interface interativa da API com Swagger (OpenAPI).  
- 👑 **Sistema de Permissões (Roles)**: Diferenciação de permissões entre `USER` e `ADMIN`.  
- 🗄️ **Migrações de Banco**: Versionamento com Flyway (7 migrações implementadas).  
- 🧪 **Testes Automatizados**: Testes unitários e de integração para controllers principais.  
- 🔒 **Validação de Propriedade**: Usuários só podem modificar/excluir seus próprios tópicos e respostas (ou admins).  

---

## 🚧 Futuras Funcionalidades
- Expansão de testes para validação de JWT e autorização por roles.  
- Testes para endpoints de autenticação (`/login` e `/usuarios/registrar`).  
- Implementação de perfis para diferentes ambientes (dev, test, prod).  
- Métricas e monitoramento com health checks e métricas de performance.  

---

## 🔌 API Endpoints

| Verbo HTTP | Endpoint                        | Descrição                                       | Acesso              |
|------------|---------------------------------|------------------------------------------------|---------------------|
| POST       | `/login`                        | Autentica um usuário e devolve um token JWT     | Público             |
| POST       | `/usuarios/registrar`           | Registra um novo usuário                        | Público             |
| POST       | `/topicos`                      | Cadastra um novo tópico                         | Protegido           |
| GET        | `/topicos`                      | Lista todos os tópicos ativos (paginado)        | Protegido           |
| GET        | `/topicos/{id}`                 | Detalha um tópico específico                    | Protegido           |
| PUT        | `/topicos/{id}`                 | Atualiza um tópico existente                    | Autor ou Admin      |
| DELETE     | `/topicos/{id}`                 | Apaga um tópico (exclusão lógica)               | Autor ou Admin      |
| POST       | `/respostas`                    | Cadastra uma nova resposta a um tópico          | Protegido           |
| GET        | `/respostas/topico/{idTopico}`  | Lista todas as respostas de um tópico           | Protegido           |
| PUT        | `/respostas/{id}`               | Atualiza uma resposta existente                 | Autor ou Admin      |
| DELETE     | `/respostas/{id}`               | Apaga uma resposta existente                    | Autor ou Admin      |

---

## 🚀 Tecnologias Utilizadas
- ☕ **Java 17** (LTS)  
- 🍃 **Spring Boot 3**  
- 🔐 **Spring Security**  
- 💾 **Spring Data JPA**  
- 🔑 **JWT** (JSON Web Tokens)  
- 📦 **Maven**  
- 🐬 **MySQL 8**  
- 🛠️ **Flyway** (migrações do banco)  
- ✨ **Lombok**  
- 📖 **SpringDoc OpenAPI (Swagger)**  
