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