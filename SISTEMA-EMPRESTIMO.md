# Sistema de Empréstimos
## Especificação do Projeto para Estudo de Java 25 + JUnit 5 + Mockito + H2 + JDBC (Sem Spring)

---

# Objetivo

Desenvolver um sistema completo de **Empréstimos e Financiamentos**, utilizando **Java 25**, com foco em:

- Regras de negócio bancárias
- Simulação de financiamentos
- Aprovação de crédito
- Cálculos financeiros
- Amortização
- Testes Unitários
- Testes de Integração
- Mockito
- JUnit 5
- JDBC Puro
- H2 Database

O sistema deverá simular todo o processo de concessão de crédito, desde a análise do cliente até a quitação do contrato.

Todo cálculo financeiro deverá utilizar **BigDecimal**.

---

# Objetivos de Aprendizado

Durante o desenvolvimento serão praticados:

- Java 25
- BigDecimal
- LocalDate
- LocalDateTime
- Records
- Enums
- Streams
- Optional
- SOLID
- Clean Code
- Repository Pattern
- Service Layer
- JDBC
- H2
- Mockito
- JUnit 5
- Testes Parametrizados
- Testes de Integração

---

# Arquitetura

```text
loan-system
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
│       ├── calculator
│       ├── enums
│       ├── exception
│       ├── report
│       ├── util
│       └── Main.java
│
├── test
│   └── java
│       ├── service
│       ├── calculator
│       ├── repository
│       ├── integration
│       ├── fixture
│       └── util
│
├── schema.sql
├── data.sql
└── pom.xml
```

---

# Entidades

## Customer

```text
id
name
cpf
birthDate
monthlyIncome
creditScore
employmentYears
status
```

---

## Loan

```text
id
customer
requestedAmount
approvedAmount
downPayment
interestRate
installments
installmentValue
system
status
contractDate
```

---

## Installment

```text
number
dueDate
principal
interest
amortization
balance
status
```

---

## Payment

```text
installment
paymentDate
amount
interest
fine
discount
```

---

# Enumerações

```text
LoanStatus

SIMULATED
REQUESTED
APPROVED
REJECTED
ACTIVE
PAID
DEFAULT
CANCELLED
```

```text
AmortizationSystem

PRICE
SAC
```

---

# Exceptions

```text
BusinessException
```

```text
LoanDeniedException
```

```text
InsufficientIncomeException
```

```text
CreditScoreException
```

```text
InvalidInstallmentException
```

```text
InvalidInterestRateException
```

---

# Repositórios

```text
CustomerRepository

LoanRepository

InstallmentRepository

PaymentRepository
```

Implementações

```text
CustomerRepositoryJdbc

LoanRepositoryJdbc

InstallmentRepositoryJdbc

PaymentRepositoryJdbc
```

---

# Services

## CustomerService

Responsabilidades

- cadastrar cliente
- consultar cliente
- atualizar renda
- atualizar score

---

## LoanSimulationService

Responsabilidades

- simular financiamento
- calcular parcelas
- comparar PRICE x SAC
- calcular juros totais
- calcular custo efetivo

---

## LoanApprovalService

Responsabilidades

- aprovar empréstimo
- reprovar empréstimo
- validar idade
- validar renda
- validar score
- validar entrada
- validar comprometimento de renda

---

## InterestService

Responsabilidades

- juros simples
- juros compostos
- juros por atraso
- juros diários
- juros mensais

---

## AmortizationService

Responsabilidades

- amortização PRICE
- amortização SAC
- saldo devedor
- liquidação antecipada

---

## PaymentService

Responsabilidades

- pagamento de parcelas
- quitação antecipada
- pagamento parcial
- cálculo de multa
- cálculo de juros por atraso

---

## ReportService

Responsabilidades

- contrato
- cronograma de parcelas
- saldo devedor
- histórico de pagamentos
- simulações

---

# Regras de Negócio

## Cliente

- CPF único
- cliente ativo
- renda maior que zero

---

## Aprovação

- idade mínima
- idade máxima configurável
- score mínimo
- renda mínima
- entrada mínima
- parcela máxima permitida

---

## Comprometimento da Renda

A parcela mensal não poderá ultrapassar um percentual da renda.

Exemplo

```text
Parcela máxima = 30% da renda
```

---

## Entrada

- obrigatória quando configurada
- percentual mínimo
- valor positivo

---

## Parcelas

- quantidade mínima
- quantidade máxima
- valor mínimo

---

## Juros

- taxa positiva
- taxa máxima permitida

---

## Pagamentos

- impedir pagamento duplicado
- permitir antecipação
- aplicar multa
- aplicar juros

---

# Fórmulas Financeiras

## Juros Simples


::contentReference[oaicite:0]{index=0}


Além dos juros compostos, implemente também a fórmula de juros simples:

```text
J = Capital × Taxa × Tempo
```

---

## Juros Compostos

Utilizar crescimento exponencial para investimentos e financiamentos.

---

## Sistema PRICE

Características

- parcelas fixas
- juros decrescentes
- amortização crescente

Métodos

```text
calculateInstallment()

generateSchedule()

calculateTotalInterest()
```

---

## Sistema SAC

Características

- amortização fixa
- parcelas decrescentes
- juros sobre saldo devedor

Métodos

```text
generateSchedule()

calculateInstallments()

remainingBalance()
```

---

## Amortização

```text
Saldo Devedor

=

Saldo Anterior

-

Amortização
```

---

## Multa por Atraso

```text
Valor da Parcela

+

Multa

+

Juros
```

---

# Cálculos

O sistema deverá calcular

- juros simples
- juros compostos
- parcelas
- saldo devedor
- amortização
- custo total
- custo efetivo
- entrada
- valor financiado
- juros por atraso
- multa
- desconto por antecipação

---

# Relatórios

- Simulação
- Contrato
- Cronograma PRICE
- Cronograma SAC
- Histórico de Pagamentos
- Saldo Devedor
- Empréstimos Ativos
- Empréstimos Quitados

---

# Testes Unitários

## LoanApprovalServiceTest

- idade mínima
- idade máxima
- score suficiente
- score insuficiente
- renda suficiente
- renda insuficiente
- comprometimento de renda
- entrada insuficiente
- aprovação
- reprovação

---

## LoanSimulationServiceTest

- simulação PRICE
- simulação SAC
- parcelas
- juros totais
- custo efetivo
- comparação entre sistemas

---

## InterestServiceTest

- juros simples
- juros compostos
- juros diários
- juros mensais
- juros por atraso

---

## AmortizationServiceTest

- saldo devedor
- amortização PRICE
- amortização SAC
- liquidação antecipada

---

## PaymentServiceTest

- pagamento normal
- pagamento parcial
- pagamento antecipado
- atraso
- multa
- juros

---

# Mockito

Treinar

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

# Testes Parametrizados

Ideal para testar:

- diferentes taxas de juros
- diferentes rendas
- diferentes scores
- diferentes idades
- diferentes prazos
- diferentes entradas
- PRICE x SAC

---

# Testes de Integração (H2)

Banco

```text
jdbc:h2:mem:loan;DB_CLOSE_DELAY=-1
```

Testar

- clientes
- empréstimos
- parcelas
- pagamentos
- consultas
- rollback
- commit

---

# Cenários de Teste

## Cliente

- idade insuficiente
- idade máxima
- score baixo
- score alto

---

## Empréstimos

- aprovado
- recusado
- renda insuficiente
- entrada insuficiente
- parcela acima do limite

---

## Juros

- simples
- compostos
- taxa zero
- taxa negativa

---

## Parcelas

- 12 meses
- 24 meses
- 36 meses
- 60 meses
- 120 meses

---

## Sistemas

### PRICE

- parcelas iguais
- juros totais
- amortização crescente

### SAC

- parcelas decrescentes
- amortização constante
- juros decrescentes

---

## Pagamentos

- antecipação
- atraso
- quitação
- multa
- juros

---

# Teste Mestre (End-to-End)

```text
shouldSimulateApproveFinanceAndPayLoan()
```

Fluxo

1. Cadastrar cliente.
2. Atualizar score.
3. Atualizar renda.
4. Simular financiamento PRICE.
5. Simular financiamento SAC.
6. Comparar resultados.
7. Solicitar empréstimo.
8. Aprovar crédito.
9. Gerar contrato.
10. Gerar cronograma.
11. Registrar pagamento de parcelas.
12. Simular atraso.
13. Calcular multa.
14. Calcular juros.
15. Quitar contrato.
16. Validar saldo devedor igual a zero.

---

# Meta de Testes

| Camada | Quantidade Estimada |
|---------|--------------------:|
| CustomerService | 20 |
| LoanApprovalService | 45 |
| LoanSimulationService | 50 |
| InterestService | 40 |
| AmortizationService | 40 |
| PaymentService | 40 |
| Repository (H2) | 35 |
| Testes End-to-End | 20 |

**Total estimado:** **290 a 350 testes automatizados**

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Orientação a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| BigDecimal | ⭐⭐⭐⭐⭐ |
| Matemática Financeira | ⭐⭐⭐⭐⭐ |
| Sistemas de Amortização (PRICE e SAC) | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐☆ |
| H2 Database | ⭐⭐⭐⭐☆ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐⭐ |
| Testes Parametrizados | ⭐⭐⭐⭐⭐ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |

---

# Resultado Esperado

Ao concluir este projeto, você terá implementado um sistema completo de **Empréstimos e Financiamentos**, abrangendo análise de crédito, simulações, aprovação, amortização, pagamentos e quitação. O projeto permitirá praticar centenas de cenários de testes automatizados com **JUnit 5**, **Mockito**, **JDBC** e **H2**, além de aprofundar conhecimentos em matemática financeira e modelagem de regras de negócio semelhantes às utilizadas por instituições financeiras.