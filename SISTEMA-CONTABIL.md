# Sistema Contábil Completo
## Projeto para estudo de Java 25 + JUnit 5 + Mockito + JDBC + H2 (Sem Spring)

## Objetivo

Desenvolver um sistema contábil completo em **Java 25**, utilizando apenas:

- Java 25
- Maven
- JUnit 5
- Mockito
- H2 Database
- JDBC Puro
- Console (JAR)
- Arquitetura em Camadas
- Orientação a Objetos
- SOLID

O objetivo **não é criar apenas um CRUD**, mas sim um sistema rico em **regras de negócio**, **cálculos financeiros**, **consistência contábil** e **testes unitários e de integração**.

A principal característica do projeto é que **tudo precisa fechar**.

Se houver erro de R$ 0,01 em qualquer cálculo, os relatórios contábeis deverão indicar inconsistência.

---

# Objetivos de aprendizado

Durante o projeto serão praticados:

- Programação Orientada a Objetos
- SOLID
- Clean Code
- Design Patterns
- Repository Pattern
- Service Layer
- JDBC Puro
- H2 Database
- BigDecimal
- Datas (LocalDate)
- Exceções personalizadas
- Testes Unitários
- Testes de Integração
- Mockito
- JUnit 5
- TDD (opcional)

---

# Estrutura do Projeto

```
accounting-system
│
├── src
│   ├── main
│   │   └── java
│   │       ├── entity
│   │       ├── dto
│   │       ├── service
│   │       ├── repository
│   │       ├── report
│   │       ├── exception
│   │       ├── util
│   │       ├── validator
│   │       ├── enums
│   │       └── Main.java
│   │
│   └── test
│       └── java
│           ├── service
│           ├── repository
│           ├── integration
│           ├── report
│           └── util
│
├── schema.sql
├── data.sql
└── pom.xml
```

---

# Entidades

## Conta Contábil (Account)

```java
code
name
type
level
parent
acceptPosting
openingBalance
```

Tipos

- Ativo
- Passivo
- Patrimônio Líquido
- Receita
- Despesa

---

## Lançamento Contábil (JournalEntry)

```java
id
number
date
history
status
items
```

---

## Item do Lançamento

```java
account
debit
credit
costCenter
```

---

## Exercício Contábil

```java
year
status
openingDate
closingDate
```

---

## Centro de Custo

```java
id
name
active
```

---

## Histórico Padronizado

```java
code
description
```

---

# Enumerações

```
AccountType
EntryStatus
FiscalYearStatus
EntryType
Nature
ReportType
```

---

# Exceptions

```
BusinessException

AccountNotFoundException

ClosedFiscalYearException

InvalidEntryException

UnbalancedEntryException

DuplicateAccountException

AccountHasMovementsException

InvalidAccountTypeException
```

---

# Repositórios

```
AccountRepository

JournalEntryRepository

FiscalYearRepository

CostCenterRepository
```

Implementações

```
JdbcAccountRepository

JdbcJournalRepository

JdbcFiscalYearRepository
```

---

# Services

## Plano de Contas

```
ChartOfAccountsService
```

Responsabilidades

- cadastrar contas
- alterar contas
- excluir contas
- impedir duplicidade
- validar estrutura do código
- validar conta analítica
- validar conta sintética

---

## Exercício Contábil

```
FiscalYearService
```

Responsabilidades

- abrir exercício
- fechar exercício
- impedir lançamentos em exercício encerrado
- validar período

---

## Lançamentos

```
JournalEntryService
```

Responsabilidades

- registrar lançamento
- validar partidas dobradas
- validar contas
- validar valores
- validar datas
- validar exercício
- numeração automática

---

## Livro Diário

```
GeneralJournalService
```

Responsabilidades

- listar lançamentos
- ordenar cronologicamente
- pesquisar período
- pesquisar conta
- exportar relatório

---

## Livro Razão

```
GeneralLedgerService
```

Responsabilidades

- gerar razão
- calcular saldo
- calcular movimentações
- saldo anterior
- saldo acumulado

---

## Balancete

```
TrialBalanceService
```

Responsabilidades

- gerar balancete
- totalizar contas
- verificar fechamento
- identificar inconsistências

---

## DRE

```
IncomeStatementService
```

Responsabilidades

- calcular receitas
- calcular despesas
- lucro
- prejuízo
- resultado líquido

---

## Balanço Patrimonial

```
BalanceSheetService
```

Responsabilidades

- gerar ativo
- gerar passivo
- gerar patrimônio líquido
- validar igualdade patrimonial

---

## Encerramento

```
ClosingEntryService
```

Responsabilidades

- zerar receitas
- zerar despesas
- calcular resultado
- gerar lançamento automático
- atualizar patrimônio líquido

---

## Abertura

```
OpeningBalanceService
```

Responsabilidades

- copiar contas patrimoniais
- gerar lançamento inicial
- validar saldos

---

## Relatórios

```
ReportService
```

Relatórios

- Livro Diário
- Livro Razão
- Balancete
- DRE
- Balanço Patrimonial
- Plano de Contas
- Diário Geral
- Razão Geral

---

# Regras de Negócio

## Plano de Contas

- código único
- nome obrigatório
- conta sintética não recebe lançamentos
- conta analítica recebe lançamentos
- não excluir conta movimentada
- validar nível hierárquico
- validar conta pai

---

## Lançamentos

- débito obrigatório
- crédito obrigatório
- débito = crédito
- mínimo dois itens
- valor positivo
- data válida
- conta existente
- exercício aberto
- conta analítica

---

## Livro Diário

- ordem cronológica
- número sequencial
- histórico obrigatório

---

## Livro Razão

- saldo acumulado
- saldo inicial
- saldo final
- movimentação por período

---

## Balancete

Obrigatoriamente

```
Total Débitos = Total Créditos
```

Caso contrário

```
throw UnbalancedEntryException
```

---

## DRE

```
Receitas

(-) Despesas

= Lucro ou Prejuízo
```

---

## Balanço Patrimonial

Obrigatoriamente

```
ATIVO = PASSIVO + PATRIMÔNIO LÍQUIDO
```

---

## Encerramento

- zerar receitas
- zerar despesas
- transferir resultado
- manter lançamento balanceado

---

## Abertura

Copiar apenas

- Ativo
- Passivo
- Patrimônio Líquido

Não copiar

- Receitas
- Despesas

---

# Fórmulas Contábeis

## Partidas Dobradas

```
Débitos == Créditos
```

---

## Saldo

```
Saldo Final

=

Saldo Inicial

+

Débitos

-

Créditos
```

---

## DRE

```
Lucro

=

Receitas

-

Despesas
```

---

## Patrimônio

```
Ativo

=

Passivo

+

Patrimônio Líquido
```

---

# Testes Unitários (JUnit)

## ChartOfAccountsServiceTest

- cadastrar conta
- impedir código duplicado
- validar tipo
- validar nível

---

## JournalEntryServiceTest

- lançamento válido
- débito diferente do crédito
- valor negativo
- lançamento vazio
- conta inexistente
- exercício encerrado

---

## LedgerServiceTest

- saldo positivo
- saldo negativo
- saldo inicial
- saldo acumulado

---

## TrialBalanceServiceTest

- balancete vazio
- balancete correto
- balancete inconsistente

---

## IncomeStatementServiceTest

- lucro
- prejuízo
- resultado zero

---

## BalanceSheetServiceTest

- ativo igual passivo + PL
- empresa sem movimento

---

## ClosingEntryServiceTest

- receitas zeradas
- despesas zeradas
- lucro transferido
- prejuízo transferido

---

## OpeningBalanceServiceTest

- abertura correta
- receitas não copiadas
- despesas não copiadas

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
- times()
- never()
- atLeast()
- atMost()
- ArgumentCaptor
- InOrder

---

# Testes de Integração (H2)

Banco

```
jdbc:h2:mem:accounting;DB_CLOSE_DELAY=-1
```

Testar

- AccountRepository
- JournalRepository
- FiscalYearRepository

Operações

- INSERT
- UPDATE
- DELETE
- SELECT
- TRANSACTION
- COMMIT
- ROLLBACK

---

# Cenários de Teste

## Lançamentos

- débito maior
- crédito maior
- lançamento sem itens
- lançamento com 1.000 itens
- data inválida
- período encerrado
- conta inexistente
- conta sintética
- valor negativo

---

## Livro Razão

- saldo positivo
- saldo negativo
- saldo zero
- múltiplos exercícios

---

## Balancete

- empresa sem movimento
- empresa com milhares de lançamentos
- balancete fechado
- balancete inconsistente

---

## DRE

- lucro
- prejuízo
- empate
- somente receitas
- somente despesas

---

## Balanço Patrimonial

- empresa recém-aberta
- empresa lucrativa
- empresa com prejuízo
- patrimônio líquido negativo

---

## Encerramento

- lucro
- prejuízo
- encerramento duplicado
- encerramento sem lançamentos

---

## Abertura

- saldos corretos
- receitas zeradas
- despesas zeradas
- balanço permanece fechado

---

# Teste Mestre (End-to-End)

Criar um único teste que valide todo o fluxo do sistema.

```
shouldCloseFiscalYearAndOpenNextFiscalYear()
```

Fluxo

1. Abrir exercício.
2. Cadastrar plano de contas.
3. Registrar capital social.
4. Registrar compras.
5. Registrar vendas.
6. Registrar despesas.
7. Registrar recebimentos.
8. Registrar pagamentos.
9. Gerar Livro Diário.
10. Gerar Livro Razão.
11. Gerar Balancete.
12. Gerar DRE.
13. Gerar Balanço Patrimonial.
14. Encerrar exercício.
15. Validar contas de resultado zeradas.
16. Abrir novo exercício.
17. Gerar saldos iniciais.
18. Confirmar que o novo balanço continua consistente.

---

# Funcionalidades Futuras

- Fluxo de Caixa
- Contas a Pagar
- Contas a Receber
- Controle Bancário
- Conciliação Bancária
- Centros de Custos
- Plano de Contas Hierárquico
- Orçamento Empresarial
- Depreciação de Ativos
- Controle de Estoque
- Livro Caixa
- SPED Contábil (simulado)
- Exportação para CSV/PDF
- Multiempresa
- Multimoeda
- Auditoria de Lançamentos
- Histórico de Alterações
- Controle de Usuários e Permissões

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Orientação a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐☆ |
| H2 Database | ⭐⭐⭐⭐☆ |
| BigDecimal | ⭐⭐⭐⭐⭐ |
| Regras Contábeis | ⭐⭐⭐⭐⭐ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐☆ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |
| Design de Domínio | ⭐⭐⭐⭐⭐ |
| Validação de Regras de Negócio | ⭐⭐⭐⭐⭐ |

---

# Resultado Esperado

Ao concluir este projeto, você terá implementado um sistema contábil completo, com foco em regras de negócio reais, consistência matemática, arquitetura limpa e alta cobertura de testes. O projeto servirá como um excelente laboratório para dominar **JUnit 5**, **Mockito**, **JDBC**, **H2** e boas práticas de desenvolvimento em Java, simulando desafios encontrados em sistemas corporativos de gestão financeira e contábil.