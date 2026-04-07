API REST Clínica: Back-end com Spring Boot
Este repositório contém o desenvolvimento do back-end da Clínica, focado na criação de uma API REST robusta para gestão de dados. O projeto foi construído utilizando o ecossistema Spring Boot, aplicando as melhores práticas de desenvolvimento Java e persistência de dados.

🏥 Sobre o Projeto
Esta API serve como o motor de regras de negócio para o sistema da clínica, permitindo o gerenciamento eficiente de cadastros e atendimentos por meio de uma interface REST segura e validada.

🛠️ Tecnologias e Ferramentas
Java & Spring Boot: Núcleo da aplicação.

Spring Initializr: Bootstrap do projeto e gerenciamento de dependências.

Maven (pom.xml): Gerenciamento de build e bibliotecas.

Spring Data JPA: Abstração da camada de persistência e acesso a dados.

SQL SERVE: Banco de dados relacional.

Flyway: Controle de versionamento e migrations (histórico de evolução do banco da clínica).

Lombok: Redução de código boilerplate.

Bean Validation: Validação de regras de negócio nos campos da clínica.

📚 Aprendizados Chave
Desenvolvimento de API REST
Criação de Controllers para exposição de endpoints de gestão da clínica.

Mapeamento de Entidades JPA (Médicos, Pacientes, Consultas).

Uso de Repositories para simplificar o acesso ao banco de dados MySQL.

Gestão e Evolução de Dados
Implementação de Migrations com Flyway para garantir que a estrutura do banco de dados da clínica evolua de forma organizada.

Configuração de diretórios e propriedades do Spring para ambientes de desenvolvimento.

📂 Funcionalidades Implementadas
CRUD Completo: Cadastro, leitura, atualização e exclusão lógica de registros.

Validações: Regras para garantir que nenhum dado inconsistente entre no sistema.

Histórico de Banco: Controle total via migrations.
