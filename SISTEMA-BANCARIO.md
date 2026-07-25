# Sistema Bancário
## Especificação do Projeto para Estudo de Java 25 + JUnit 5 + Mockito + H2 + JDBC (Sem Spring)

---

# Objetivo

Desenvolver um sistema bancário completo em **Java 25**, utilizando apenas tecnologias da plataforma Java, com foco em:

- Regras de negócio
- Testes Unitários
- Testes de Integração
- Mockito
- JUnit 5
- JDBC Puro
- H2 Database
- Arquitetura em Camadas
- Orientação a Objetos
- SOLID
- Clean Code

O sistema será executado em modo **Console (JAR)**, sem utilizar Spring Framework.

O principal objetivo é praticar a criação de regras de negócio complexas e aprender a escrever testes automatizados para todos os cenários possíveis.

---

# Objetivos de Aprendizado

Durante o desenvolvimento serão praticados:

- Java 25
- Records
- Enums
- Generics
- Optional
- Streams
- BigDecimal
- LocalDate
- LocalDateTime
- LocalTime
- Exceptions
- JDBC
- H2 Database
- Repository Pattern
- Service Layer
- DTO
- Builder Pattern
- Factory Pattern
- JUnit 5
- Mockito
- Testes de Integração
- Cobertura de Testes
- TDD (Opcional)

---

# Arquitetura

```
bank-system
│
├── src
│
├── main
│   └── java
│       ├── entity
│       ├── dto
│       ├── service
│       ├── repository
│       ├── validator
│       ├── util
│       ├── exception
│       ├── enums
│       ├── report
│       └── Main.java
│
├── test
│   └── java
│       ├── service
│       ├── repository
│       ├── integration
│       ├── util
│       └── fixture
│
├── schema.sql
├── data.sql
└── pom.xml
```

---

# Entidades

## Customer

```
id
name
cpf
birthDate
email
phone
score
status
createdAt
```

---

## Account

```
id
agency
number
digit
customer
balance
overdraftLimit
availableLimit
status
openedAt
closedAt
```

---

## Transaction

```
id
account
type
amount
balanceBefore
balanceAfter
dateTime
description
```

---

## PixTransfer

```
id
sender
receiver
amount
pixKey
dateTime
status
```

---

## Loan

```
id
customer
principal
interestRate
installments
approved
contractDate
```

---

## Investment

```
id
customer
type
amount
annualRate
startDate
endDate
```

---

## Statement

```
account
initialDate
finalDate
transactions
```

---

# Enumerações

```
AccountStatus

ACTIVE
BLOCKED
CLOSED
```

```
TransactionType

DEPOSIT
WITHDRAW
TRANSFER
PIX
TED
FEE
INTEREST
LOAN
INVESTMENT
```

```
InvestmentType

SAVINGS
CDB
LCI
LCA
TESOURO
```

---

# Exceptions

```
BusinessException
```

```
AccountNotFoundException
```

```
InsufficientBalanceException
```

```
AccountBlockedException
```

```
AccountClosedException
```

```
DailyLimitExceededException
```

```
InvalidAmountException
```

```
LoanDeniedException
```

```
InvalidTransferException
```

---

# Repositórios

```
CustomerRepository

AccountRepository

TransactionRepository

LoanRepository

InvestmentRepository
```

Implementações

```
CustomerRepositoryJdbc

AccountRepositoryJdbc

TransactionRepositoryJdbc

LoanRepositoryJdbc

InvestmentRepositoryJdbc
```

---

# Services

## CustomerService

Responsabilidades

- cadastrar cliente
- atualizar dados
- consultar cliente
- validar CPF
- validar idade
- validar score

---

## AccountService

Responsabilidades

- abrir conta
- encerrar conta
- bloquear conta
- desbloquear conta
- consultar saldo
- depósito
- saque
- cobrança de tarifa
- cálculo do limite disponível

---

## TransferService

Responsabilidades

- TED
- DOC (opcional)
- transferência interna
- validar horário
- validar saldo
- validar contas

---

## PixService

Responsabilidades

- cadastrar chave PIX
- transferir via PIX
- validar limite diário
- validar saldo
- validar chave
- cancelar PIX (quando permitido)

---

## LoanService

Responsabilidades

- simular empréstimo
- aprovar empréstimo
- reprovar empréstimo
- calcular parcelas
- calcular juros
- calcular multa
- amortização
- liquidação antecipada

---

## InvestmentService

Responsabilidades

- aplicar investimento
- resgatar investimento
- rendimento diário
- rendimento mensal
- rentabilidade acumulada
- imposto de renda
- IOF

---

## StatementService

Responsabilidades

- gerar extrato
- listar movimentações
- filtrar período
- saldo inicial
- saldo final

---

## FeeService

Responsabilidades

- tarifa de manutenção
- tarifa TED
- tarifa saque
- tarifa transferência

---

## InterestService

Responsabilidades

- juros simples
- juros compostos
- juros do cheque especial
- juros de empréstimos

---

# Regras de Negócio

## Cliente

- CPF único
- idade mínima de 18 anos
- score válido
- cliente ativo

---

## Conta

- conta ativa
- conta bloqueada não movimenta
- conta encerrada não movimenta
- saldo inicial igual a zero
- agência obrigatória
- número único

---

## Depósito

- valor positivo
- conta ativa
- registrar transação

---

## Saque

- saldo suficiente
- utilizar limite especial quando permitido
- conta ativa
- registrar transação

---

## Transferência

- origem diferente do destino
- saldo suficiente
- conta ativa
- horário permitido
- registrar duas movimentações

---

## PIX

- limite diário
- chave válida
- conta ativa
- saldo suficiente
- valor positivo

---

## TED

- somente em horário comercial
- tarifa obrigatória
- saldo suficiente

---

## Empréstimo

- idade mínima
- score mínimo
- renda mínima (opcional)
- parcela máxima
- taxa de juros
- aprovação automática

---

## Investimentos

- saldo suficiente
- rendimento diário
- rendimento mensal
- IOF
- Imposto de Renda
- resgate

---

# Fórmulas

## Juros Simples

```
J = C × i × t
```

---

## Montante

```
M = C + J
```

---

## Juros Compostos

```
M = C × (1 + i)^t
```

---

## Saldo

```
Saldo Atual

=

Saldo Inicial

+

Depósitos

+

Rendimentos

-

Saques

-

Transferências

-

Tarifas
```

---

## Limite Disponível

```
Saldo

+

Limite Especial
```

---

## Rentabilidade

```
Lucro

/

Valor Investido

×

100
```

---

## IOF

Aplicar tabela regressiva conforme quantidade de dias.

---

## Imposto de Renda

Aplicar tabela regressiva conforme prazo do investimento.

---

# Relatórios

- Extrato Bancário
- Histórico de Transferências
- Histórico PIX
- Empréstimos
- Investimentos
- Clientes
- Contas
- Tarifas
- Rentabilidade

---

# Testes Unitários

## CustomerServiceTest

- cadastrar cliente
- CPF duplicado
- idade inválida
- score inválido

---

## AccountServiceTest

- abrir conta
- depósito
- saque
- saldo insuficiente
- conta bloqueada
- conta encerrada
- limite especial
- cobrança de tarifa
- encerrar conta

---

## TransferServiceTest

- transferência válida
- saldo insuficiente
- mesma conta
- conta bloqueada
- horário inválido
- tarifa aplicada

---

## PixServiceTest

- PIX válido
- chave inválida
- limite diário excedido
- saldo insuficiente
- conta bloqueada

---

## LoanServiceTest

- empréstimo aprovado
- score insuficiente
- idade insuficiente
- juros simples
- juros compostos
- amortização
- atraso
- multa

---

## InvestmentServiceTest

- aplicação
- resgate
- rendimento diário
- rendimento mensal
- imposto de renda
- IOF
- saldo insuficiente

---

## StatementServiceTest

- extrato vazio
- extrato com movimentações
- período inválido

---

# Mockito

Praticar

- mock()
- spy()
- @Mock
- @InjectMocks
- when()
- thenReturn()
- thenThrow()
- doThrow()
- verify()
- verifyNoInteractions()
- verifyNoMoreInteractions()
- ArgumentCaptor
- InOrder
- times()
- never()
- atLeast()
- atMost()

---

# Testes de Integração (H2)

Banco

```
jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
```

Testar

- abertura de conta
- persistência de clientes
- persistência de transações
- empréstimos
- investimentos
- consultas
- transações
- rollback
- commit

---

# Cenários de Teste

## Conta

- abrir conta
- abrir conta duplicada
- bloquear
- desbloquear
- encerrar
- saldo negativo
- saldo positivo

---

## Saques

- saque válido
- saldo insuficiente
- usando limite
- valor negativo
- conta encerrada

---

## Depósitos

- depósito válido
- valor zero
- valor negativo

---

## PIX

- limite diário
- múltiplos PIX
- chave inexistente
- saldo insuficiente
- conta bloqueada

---

## Transferências

- TED
- transferência interna
- fora do horário
- mesma conta
- tarifa

---

## Empréstimos

- aprovado
- recusado
- score baixo
- idade insuficiente
- juros
- atraso
- multa
- amortização
- quitação

---

## Investimentos

- aplicação
- rendimento diário
- rendimento mensal
- resgate parcial
- resgate total
- IOF
- IR

---

# Teste Mestre (End-to-End)

Criar um único teste de integração cobrindo todo o fluxo bancário.

```
shouldExecuteCompleteBankOperationFlow()
```

Fluxo

1. Cadastrar cliente.
2. Abrir conta.
3. Depositar dinheiro.
4. Realizar saque.
5. Efetuar transferência.
6. Efetuar PIX.
7. Cobrar tarifa.
8. Contratar empréstimo.
9. Aplicar investimento.
10. Simular rendimento diário.
11. Simular rendimento mensal.
12. Resgatar investimento.
13. Gerar extrato.
14. Validar saldo final.
15. Confirmar consistência de todas as transações.

---

# Meta de Testes

| Camada | Quantidade Estimada |
|---------|--------------------:|
| CustomerService | 20 |
| AccountService | 45 |
| TransferService | 35 |
| PixService | 30 |
| LoanService | 50 |
| InvestmentService | 45 |
| StatementService | 20 |
| Repository (H2) | 35 |
| Testes End-to-End | 15 |

**Total estimado:** **295 a 350 testes automatizados**

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Orientação a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| BigDecimal | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐☆ |
| H2 Database | ⭐⭐⭐⭐☆ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐⭐ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |
| Regras Bancárias | ⭐⭐⭐⭐⭐ |
| Cálculos Financeiros | ⭐⭐⭐⭐⭐ |
| TDD | ⭐⭐⭐⭐☆ |

---

# Resultado Esperado

Ao concluir este projeto, você terá desenvolvido um sistema bancário com regras de negócio realistas, cobrindo operações de contas, transferências, PIX, empréstimos, investimentos e geração de extratos. Com aproximadamente **300 testes automatizados**, o projeto proporcionará domínio prático de **JUnit 5**, **Mockito**, **JDBC**, **H2** e arquitetura em camadas, simulando cenários encontrados em sistemas financeiros corporativos.