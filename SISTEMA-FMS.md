````markdown id="m8g4xp"
# Financial Management System (FMS)
## Projeto Master para Dominar Java 25, JUnit 5, Mockito, JDBC e H2

> **Objetivo:** Construir um único sistema corporativo de grande porte que concentre dezenas de módulos de negócio, centenas de regras, milhares de cálculos e centenas de testes automatizados.

Este projeto foi pensado para simular um ERP financeiro utilizado por bancos, empresas de grande porte, fintechs e sistemas de gestão empresarial.

A ideia não é apenas aprender **JUnit** ou **Mockito**, mas aprender **como testar sistemas reais**, onde várias regras de negócio dependem umas das outras.

---

# Objetivos do Projeto

Ao concluir este projeto você terá praticado:

- Java 25
- Orientação a Objetos
- SOLID
- Clean Architecture
- Clean Code
- Domain Driven Design (DDD Lite)
- Repository Pattern
- Service Layer
- DTO
- Builder Pattern
- Factory Pattern
- Strategy Pattern
- Template Method
- Observer
- Command
- State
- Specification Pattern
- Validation Pattern
- JDBC puro
- H2 Database
- Maven
- JUnit 5
- Mockito
- Testes Parametrizados
- Testes de Integração
- Testes End-to-End
- Cobertura de Código
- Mutation Testing (PIT)
- Testcontainers (opcional)
- TDD
- Refatoração Guiada por Testes

---

# Tecnologias

- Java 25
- Maven
- H2 Database
- JDBC
- JUnit Jupiter
- Mockito
- AssertJ
- Hamcrest
- JaCoCo
- PIT Mutation Testing
- SLF4J
- Logback

---

# Arquitetura

```text
financial-management-system

src
│
├── main
│   ├── java
│   │
│   ├── entity
│   ├── dto
│   ├── enums
│   ├── exception
│   ├── repository
│   ├── repository.jdbc
│   ├── validator
│   ├── calculator
│   ├── service
│   ├── report
│   ├── util
│   ├── config
│   ├── audit
│   ├── notification
│   ├── security
│   └── Main.java
│
├── test
│   ├── fixture
│   ├── service
│   ├── calculator
│   ├── validator
│   ├── repository
│   ├── integration
│   ├── acceptance
│   ├── performance
│   └── util
│
├── schema.sql
├── data.sql
└── pom.xml
```

---

# Módulos do Sistema

## Cadastro

- CustomerService
- AddressService
- ContactService
- CompanyService
- EmployeeService
- UserService

---

## Banco

- AccountService
- SavingsAccountService
- CurrentAccountService
- TransferService
- PixService
- TedService
- StatementService
- BankFeeService

---

## Crédito

- LoanSimulationService
- LoanApprovalService
- LoanContractService
- LoanPaymentService
- InterestService
- AmortizationService

---

## Cartão de Crédito

- CreditCardService
- CreditLimitService
- CardTransactionService
- CardInvoiceService
- CashbackService
- RewardPointService

---

## Investimentos

- InvestmentService
- FixedIncomeService
- SavingsService
- TreasuryService
- PortfolioService
- ProfitabilityService

---

## Contabilidade

- ChartOfAccountsService
- JournalEntryService
- GeneralLedgerService
- TrialBalanceService
- IncomeStatementService
- BalanceSheetService
- ClosingEntryService
- OpeningBalanceService

---

## Departamento Pessoal

- PayrollService
- OvertimeService
- BenefitService
- VacationService
- ThirteenthSalaryService
- IncomeTaxService
- PayrollReportService

---

## Financeiro

- BudgetService
- ExpenseService
- RevenueService
- CashFlowService
- AccountsPayableService
- AccountsReceivableService
- BankReconciliationService

---

## Energia

- MeterReadingService
- TariffService
- EnergyBillService
- TaxService
- SocialTariffService

---

## Segurança

- AuthenticationService
- AuthorizationService
- PasswordService
- JwtService
- SessionService

---

## Auditoria

- AuditService
- HistoryService
- EventLogService

---

## Comunicação

- EmailService
- SmsService
- NotificationService
- PushNotificationService

---

## Relatórios

- DashboardService
- FinancialReportService
- PdfReportService
- CsvReportService
- ExcelReportService

---

# Quantidade Aproximada de Services

| Categoria | Quantidade |
|------------|-----------:|
| Cadastro | 6 |
| Banco | 7 |
| Crédito | 6 |
| Cartão | 6 |
| Investimentos | 6 |
| Contabilidade | 8 |
| RH | 7 |
| Financeiro | 7 |
| Energia | 5 |
| Segurança | 5 |
| Auditoria | 3 |
| Comunicação | 4 |
| Relatórios | 5 |

**Total:** **75 Services**

---

# Entidades

- Customer
- User
- Employee
- Address
- Account
- SavingsAccount
- CurrentAccount
- Transaction
- PixTransfer
- Loan
- Installment
- Payment
- CreditCard
- CardInvoice
- CardTransaction
- Investment
- Portfolio
- Payroll
- PayrollItem
- Vacation
- Benefit
- Tax
- Expense
- Revenue
- Budget
- CashFlow
- JournalEntry
- JournalItem
- AccountingAccount
- TrialBalance
- BalanceSheet
- EnergyBill
- MeterReading
- Tariff
- Notification
- AuditLog

**Mais de 40 entidades.**

---

# Regras de Negócio

O projeto deve conter aproximadamente:

- 500 regras de negócio

Exemplos

- saldo insuficiente
- conta bloqueada
- conta encerrada
- limite diário PIX
- TED somente horário comercial
- cartão vencido
- cartão bloqueado
- crédito insuficiente
- cálculo de cashback
- juros simples
- juros compostos
- SAC
- PRICE
- IOF
- IRRF
- INSS
- FGTS
- férias
- décimo terceiro
- desconto social
- bandeiras tarifárias
- partida dobrada
- balanço patrimonial
- encerramento contábil
- saldo inicial
- amortização
- multa
- desconto
- rendimento diário
- rendimento mensal
- rentabilidade
- controle de orçamento
- contas vencidas
- conciliação bancária
- autenticação
- autorização
- auditoria automática

---

# Calculadoras

Separar toda lógica matemática.

```
InterestCalculator

CompoundInterestCalculator

PriceCalculator

SacCalculator

TaxCalculator

PayrollCalculator

IncomeTaxCalculator

InssCalculator

FgtsCalculator

EnergyBillCalculator

InvestmentCalculator

CashbackCalculator

BudgetCalculator

InvoiceCalculator

BalanceCalculator
```

---

# Validators

```
CpfValidator

CnpjValidator

PasswordValidator

PixKeyValidator

AccountValidator

LoanValidator

PayrollValidator

BudgetValidator

InvoiceValidator
```

---

# Testes Unitários

Cada Service deverá possuir no mínimo:

- cenário de sucesso
- cenário de erro
- cenário de exceção
- cenário de borda
- cenário inválido
- cenário nulo
- cenário extremo

Estimativa

```
75 Services

×

20 testes

=

1.500 testes unitários
```

---

# Mockito

Praticar absolutamente tudo.

## Mocks

- mock()

## Spies

- spy()

## Annotations

- @Mock
- @Spy
- @Captor
- @InjectMocks

## Stubbing

- when()
- thenReturn()
- thenThrow()
- thenAnswer()
- doReturn()
- doThrow()
- doNothing()
- doCallRealMethod()

## Verify

- verify()
- verifyNoInteractions()
- verifyNoMoreInteractions()
- times()
- never()
- only()
- atLeast()
- atMost()

## Ordem

- InOrder

## Captura

- ArgumentCaptor

## Matchers

- any()
- anyString()
- anyInt()
- eq()
- argThat()

---

# JUnit 5

Treinar

- @Test
- @BeforeEach
- @AfterEach
- @BeforeAll
- @AfterAll
- @Nested
- @DisplayName
- @RepeatedTest
- @ParameterizedTest
- @CsvSource
- @MethodSource
- @EnumSource
- @ValueSource
- @NullSource
- @EmptySource
- @NullAndEmptySource
- @TestFactory
- DynamicTest

Assertions

- assertEquals
- assertNotEquals
- assertTrue
- assertFalse
- assertNull
- assertNotNull
- assertSame
- assertNotSame
- assertThrows
- assertDoesNotThrow
- assertTimeout
- assertAll

---

# Testes Parametrizados

Exemplo

- 500 salários
- 500 taxas de juros
- 500 valores de empréstimos
- 500 contas bancárias
- 500 consumos de energia

---

# Testes de Integração

Todos os repositórios deverão possuir testes.

Banco

```text
jdbc:h2:mem:fms
```

Testar

- INSERT
- UPDATE
- DELETE
- SELECT
- JOIN
- TRANSACTION
- COMMIT
- ROLLBACK
- FOREIGN KEY
- UNIQUE
- INDEX

Estimativa

```
250 testes
```

---

# Testes End-to-End

Criar fluxos completos.

## Fluxo Bancário

Abrir conta

↓

Depositar

↓

Transferir

↓

PIX

↓

Extrato

---

## Fluxo Empréstimo

Cadastrar cliente

↓

Simular

↓

Aprovar

↓

Gerar contrato

↓

Pagar parcelas

↓

Quitar

---

## Fluxo RH

Cadastrar funcionário

↓

Horas extras

↓

Benefícios

↓

INSS

↓

IRRF

↓

Folha

↓

Holerite

---

## Fluxo Contábil

Plano de contas

↓

Lançamentos

↓

Livro Diário

↓

Livro Razão

↓

Balancete

↓

DRE

↓

Balanço

↓

Encerramento

↓

Novo Exercício

---

## Fluxo Energia

Registrar leitura

↓

Calcular consumo

↓

Aplicar bandeira

↓

Calcular impostos

↓

Gerar conta

↓

Pagamento

---

# Cobertura de Código

Meta

```
Linhas

100%

Branches

95%

Methods

100%

Classes

100%
```

---

# Mutation Testing

Utilizar PIT.

Meta

```
Mutation Score

> 90%
```

---

# Meta Final

| Tipo | Quantidade Estimada |
|------|--------------------:|
| Services | 75 |
| Entidades | 40+ |
| Repositórios | 30+ |
| Calculadoras | 15 |
| Validators | 20 |
| Regras de Negócio | 500+ |
| Testes Unitários | 1.500+ |
| Testes Parametrizados | 300+ |
| Testes de Integração | 250+ |
| Testes End-to-End | 100+ |
| Classes Java | 250+ |

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Programação Orientada a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| Design Patterns | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐⭐ |
| H2 Database | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐⭐ |
| Testes Parametrizados | ⭐⭐⭐⭐⭐ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |
| TDD | ⭐⭐⭐⭐⭐ |
| Mutation Testing | ⭐⭐⭐⭐⭐ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| Regras de Negócio Complexas | ⭐⭐⭐⭐⭐ |
| Matemática Financeira | ⭐⭐⭐⭐⭐ |

---

# Resultado Esperado

Ao finalizar este projeto, você terá construído um sistema equivalente, em complexidade, ao núcleo de um ERP financeiro ou de uma fintech. O código abrangerá dezenas de módulos independentes, centenas de regras de negócio e milhares de cenários de teste, proporcionando domínio avançado de **JUnit 5**, **Mockito**, **JDBC**, **H2**, **TDD** e arquitetura de software. Esse projeto servirá como um laboratório completo para praticar desenvolvimento backend profissional e poderá ser expandido continuamente com novos domínios, integrações e funcionalidades.
````
