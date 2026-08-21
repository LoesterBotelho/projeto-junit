# App Test (Java 25 + JUnit 5 + Mockito)

Aplicação console em Java 25 com foco em testes unitários e de integração modernos.

## ⚙️ Configuração para Execução no Eclipse

Devido às restrições de segurança de agentes dinâmicos nas versões recentes do JDK (Java 21/25+), ao rodar os testes via IDE (Eclipse), configure os **VM Arguments** nas configurações de Execução (`Run Configurations` -> `Arguments` -> `VM arguments`):

```text
-ea -XX:+EnableDynamicAgentLoading -Xshare:off


---


# App Test - JUnit e Mockito

Projeto Java Maven desenvolvido para praticar testes unitários utilizando **JUnit 5** e **Mockito**, sem dependência de banco de dados, frameworks web ou Spring Boot.

O objetivo é focar exclusivamente em conceitos de programação orientada a objetos, testes automatizados e boas práticas de desenvolvimento Java.

A aplicação possui execução em **modo console**, podendo ser empacotada como um arquivo **JAR Maven executável**.

---

## Tecnologias Utilizadas

- Java 25
- Maven
- JUnit 5
- Mockito

---

## Objetivos do Projeto

- Praticar testes unitários com JUnit 5
- Aprender criação de mocks com Mockito
- Exercitar injeção de dependências
- Validar regras de negócio
- Utilizar assertions e verificações de comportamento
- Desenvolver código desacoplado e testável
- Criar aplicações Java console utilizando Maven
- Gerar e executar arquivos JAR

---

## Estrutura do Projeto

```text
app-test
│
├── src
│   ├── main
│   │   └── java
│   │       └── br/com/exemplo
│   │           └── App.java
│   │
│   └── test
│       └── java
│           └── br/com/exemplo
│               └── AppTest.java
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# Comandos Maven

## Limpar arquivos compilados

Remove todos os arquivos gerados pelo Maven.

```bash
mvn clean
```

---

## Executar os testes

Executa todos os testes unitários utilizando JUnit 5.

```bash
mvn test
```

---

## Compilar o projeto

Compila o código-fonte Java.

```bash
mvn compile
```

---

## Gerar o JAR

Compila o projeto, executa os testes e gera o arquivo JAR.

```bash
mvn package
```

O arquivo será criado em:

```text
target/app-test-1.0.jar
```

---

## Executar ciclo completo de build

Executa:

- Limpeza
- Compilação
- Testes
- Empacotamento do JAR

```bash
mvn clean package
```

---

## Instalar no repositório local Maven

Executa todo o ciclo de build e instala o projeto no repositório Maven local.

```bash
mvn clean install
```

---

# Executando a Aplicação Console

Após gerar o JAR:

```bash
mvn clean package
```

Execute:

```bash
java -jar target/app-test-1.0.jar
```

Exemplo de saída:

```text
Aplicação App Test executando...
Projeto Java Maven com JUnit 5 e Mockito
```

---

# Conceitos Praticados

## JUnit 5

- `@Test`
- `@BeforeEach`
- `@AfterEach`
- `@DisplayName`
- `@Nested`
- `@ParameterizedTest`
- `assertEquals`
- `assertTrue`
- `assertFalse`
- `assertThrows`
- `assertAll`

---

## Mockito

- `mock()`
- `@Mock`
- `@InjectMocks`
- `@Spy`
- `when()`
- `thenReturn()`
- `thenThrow()`
- `verify()`
- `times()`
- `never()`
- `ArgumentCaptor`

---

# Exemplo de Execução dos Testes

Executar todos os testes:

```bash
mvn test
```

Resultado esperado:

```text
BUILD SUCCESS
```

---

# Gerando e Executando o JAR

Gerar o arquivo executável:

```bash
mvn clean package
```

Arquivo gerado:

```text
target/app-test-1.0.jar
```

Executar:

```bash
java -jar target/app-test-1.0.jar
```

---

# Finalidade

Este projeto possui finalidade exclusivamente educacional, servindo como laboratório para estudo de:

- Programação Orientada a Objetos (POO)
- Testes Unitários
- Testes Automatizados
- Qualidade de Software
- Boas Práticas de Desenvolvimento Java
- Desenvolvimento Maven
- Aplicações Java Console
- JUnit 5
- Mockito

---

# Autor

Loester Botelho

---

# 📚 Documentação

Este repositório reúne diversos projetos para estudo de **Java 25**, **JUnit 5**, **Mockito**, **JDBC** e **H2 Database**, com foco em regras de negócio complexas, testes unitários, testes de integração e arquitetura em camadas.

Cada documento descreve um sistema completo, contendo:

- Arquitetura do projeto
- Modelagem de entidades
- Services
- Regras de negócio
- Cálculos
- Casos de uso
- Cenários de testes
- Testes unitários (JUnit 5)
- Testes com Mockito
- Testes de integração utilizando H2 (JDBC puro)

---

# 📖 Projetos Disponíveis

| Projeto | Descrição |
|----------|-----------|
| 📒 [Sistema Contábil](SISTEMA-CONTABIL.md) | Livro Diário, Livro Razão, Balancete, DRE, Balanço Patrimonial, Encerramento do Exercício e Abertura do Próximo Exercício. |
| 🏦 [Sistema Bancário](SISTEMA-BANCARIO.md) | Contas bancárias, PIX, TED, transferências, investimentos, empréstimos, extratos e cartões de crédito. |
| 👨‍💼 [Sistema de RH / Folha de Pagamento](SISTEMA-RH.md) | Folha de pagamento, INSS, IRRF, FGTS, férias, décimo terceiro, benefícios e horas extras. |
| 💰 [Sistema de Empréstimos](SISTEMA-EMPRESTIMO.md) | Simulação de financiamentos, análise de crédito, Tabela Price, SAC, amortização e pagamentos. |
| ⚡ [Sistema de Energia](SISTEMA-ENERGIA.md) | Cálculo de consumo, bandeiras tarifárias, ICMS, PIS, COFINS, tarifa social e faturamento. |
| 🚀 [Financial Management System](SISTEMA-FMS.md) | Projeto principal contendo dezenas de módulos integrados, simulando um ERP financeiro completo para estudo avançado de JUnit e Mockito. |

---

# 🎯 Objetivo

O objetivo deste repositório é criar uma coleção de projetos que permitam praticar situações reais encontradas em sistemas corporativos, desenvolvendo habilidades em:

- Java 25
- Orientação a Objetos
- SOLID
- Clean Code
- Design Patterns
- Arquitetura em Camadas
- JDBC Puro
- H2 Database
- JUnit 5
- Mockito
- Testes Parametrizados
- Testes de Integração
- TDD (Test Driven Development)
- Regras de Negócio Complexas
- Cálculos Financeiros
- Cobertura de Código

---

# 📈 Evolução dos Projetos

| Projeto | Dificuldade |
|----------|:----------:|
| Sistema Contábil | ⭐⭐⭐⭐⭐ |
| Sistema Bancário | ⭐⭐⭐⭐⭐ |
| Sistema RH | ⭐⭐⭐⭐⭐ |
| Sistema de Empréstimos | ⭐⭐⭐⭐⭐ |
| Sistema de Energia | ⭐⭐⭐⭐⭐ |
| Financial Management System | ⭐⭐⭐⭐⭐⭐ |

> **Recomendação de estudo:** comece pelos projetos individuais e, após dominar cada domínio de negócio, avance para o **Financial Management System**, que integra todos os conceitos em um único sistema de grande porte.