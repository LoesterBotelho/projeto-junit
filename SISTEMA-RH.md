# Sistema de Folha de Pagamento
## Especificação do Projeto para Estudo de Java 25 + JUnit 5 + Mockito + H2 + JDBC (Sem Spring)

---

# Objetivo

Desenvolver um sistema completo de **Folha de Pagamento** utilizando **Java 25**, com foco em:

- Regras de negócio trabalhistas
- Cálculos financeiros
- Testes Unitários
- Testes de Integração
- Mockito
- JUnit 5
- JDBC Puro
- H2 Database
- Arquitetura em Camadas
- Orientação a Objetos

O projeto deverá simular o processamento completo da folha de pagamento de uma empresa, incluindo admissões, cálculos salariais, benefícios, férias, décimo terceiro salário, horas extras, adicionais, descontos e tributação.

Todo cálculo deverá utilizar **BigDecimal**, evitando erros de arredondamento.

---

# Objetivos de Aprendizado

Durante o desenvolvimento serão praticados:

- Java 25
- BigDecimal
- LocalDate
- LocalDateTime
- Period
- Records
- Enums
- Optional
- Streams
- SOLID
- Clean Code
- Repository Pattern
- Service Layer
- JDBC
- H2
- Mockito
- JUnit 5
- Testes de Integração
- Testes Parametrizados
- Testes Baseados em Tabelas

---

# Arquitetura

```
payroll-system
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
│       ├── enums
│       ├── report
│       ├── exception
│       └── Main.java
│
├── test
│   └── java
│       ├── service
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

## Employee

```
id
name
cpf
birthDate
hireDate
terminationDate
position
department
salary
status
```

---

## Payroll

```
id
employee
referenceMonth
referenceYear
grossSalary
netSalary
processedDate
```

---

## PayrollItem

```
description
type
quantity
unitValue
total
```

---

## Overtime

```
employee
date
hours
percentage
approved
```

---

## Benefit

```
employee
type
amount
```

---

## Vacation

```
employee
startDate
endDate
days
soldDays
```

---

## ThirteenthSalary

```
employee
year
installment
amount
```

---

# Enumerações

```
EmployeeStatus

ACTIVE
VACATION
LEAVE
TERMINATED
```

```
BenefitType

MEAL
FOOD
TRANSPORT
HEALTH_PLAN
LIFE_INSURANCE
```

```
PayrollItemType

EARNING
DISCOUNT
TAX
BENEFIT
```

---

# Exceptions

```
BusinessException
```

```
EmployeeNotFoundException
```

```
PayrollAlreadyProcessedException
```

```
InvalidSalaryException
```

```
VacationAlreadyScheduledException
```

---

# Repositórios

```
EmployeeRepository

PayrollRepository

BenefitRepository

VacationRepository
```

Implementações

```
EmployeeRepositoryJdbc

PayrollRepositoryJdbc

BenefitRepositoryJdbc

VacationRepositoryJdbc
```

---

# Services

## EmployeeService

Responsabilidades

- cadastrar funcionário
- alterar salário
- demitir funcionário
- consultar funcionário

---

## PayrollService

Responsabilidades

- calcular folha
- salário bruto
- salário líquido
- descontos
- proventos
- fechamento da folha
- geração de holerite

---

## OvertimeService

Responsabilidades

- hora extra 50%
- hora extra 100%
- cálculo por hora
- adicional em domingos e feriados

---

## AdditionalService

Responsabilidades

- adicional noturno
- adicional de periculosidade
- adicional de insalubridade

---

## BenefitService

Responsabilidades

- vale transporte
- vale alimentação
- vale refeição
- plano de saúde
- seguro de vida

---

## TaxService

Responsabilidades

- INSS
- FGTS
- IRRF
- contribuição sindical (opcional)

---

## IncomeTaxService

Responsabilidades

- calcular IRRF
- calcular base tributável
- calcular faixa
- calcular alíquota
- calcular imposto devido

---

## VacationService

Responsabilidades

- calcular férias
- vender férias
- adicional constitucional de 1/3
- abono pecuniário

---

## ThirteenthSalaryService

Responsabilidades

- primeira parcela
- segunda parcela
- cálculo proporcional
- descontos

---

## PayrollReportService

Responsabilidades

- holerite
- folha mensal
- relatório por departamento
- encargos
- benefícios

---

# Regras de Negócio

## Funcionário

- CPF único
- salário maior que zero
- funcionário ativo

---

## Hora Extra

- 50% em dias úteis
- 100% domingos
- 100% feriados
- horas positivas

---

## Adicional Noturno

- percentual configurável
- cálculo sobre horas noturnas
- somente horário noturno

---

## Vale Transporte

- desconto máximo permitido
- benefício opcional

---

## Vale Alimentação

- valor fixo
- desconto configurável

---

## Plano de Saúde

- desconto em folha
- incluir dependentes

---

## INSS

- cálculo por faixas
- teto previdenciário
- contribuição progressiva

---

## FGTS

- percentual sobre salário bruto

---

## IRRF

- cálculo progressivo
- dependentes
- previdência
- pensão alimentícia
- plano de saúde
- deduções legais

---

## Férias

- direito após período aquisitivo
- adicional constitucional de 1/3
- venda de até 1/3 das férias

---

## Décimo Terceiro

- proporcional aos meses trabalhados
- primeira parcela
- segunda parcela
- descontos legais

---

# Fórmulas

## Valor da Hora

```
Salário Mensal

/

220 horas
```

---

## Hora Extra 50%

```
Hora

×

1,5

×

Quantidade
```

---

## Hora Extra 100%

```
Hora

×

2

×

Quantidade
```

---

## Adicional Noturno

```
Hora

×

Percentual

×

Horas Noturnas
```

---

## Salário Bruto

```
Salário

+

Horas Extras

+

Adicionais

+

Bônus
```

---

## Base do INSS

```
Salário Bruto

-

Descontos Permitidos
```

---

## Base do IRRF

```
Salário Bruto

-

INSS

-

Dependentes

-

Previdência

-

Pensão

-

Plano de Saúde
```

---

## Salário Líquido

```
Salário Bruto

-

INSS

-

IRRF

-

Benefícios

-

Outros Descontos
```

---

# IncomeTaxService

## Entradas

```
Salário

Dependentes

Plano de Saúde

Previdência Privada

Pensão Alimentícia

Bônus

Outros Rendimentos
```

---

## Calcula

- Base de Cálculo
- Faixa Tributária
- Alíquota
- Parcela a Deduzir
- Valor do IRRF

---

## Métodos

```
calculateTax()

calculateTaxBase()

calculateRate()

calculateDeduction()

calculateNetTax()
```

---

# Relatórios

- Holerite
- Folha Mensal
- Encargos
- Benefícios
- Horas Extras
- Funcionários
- INSS
- FGTS
- IRRF
- Férias
- Décimo Terceiro

---

# Testes Unitários

## PayrollServiceTest

- salário bruto
- salário líquido
- descontos
- bônus
- faltas
- férias
- décimo terceiro

---

## OvertimeServiceTest

- hora extra 50%
- hora extra 100%
- hora negativa
- domingo
- feriado

---

## AdditionalServiceTest

- adicional noturno
- periculosidade
- insalubridade

---

## BenefitServiceTest

- vale transporte
- vale alimentação
- plano de saúde
- múltiplos benefícios

---

## TaxServiceTest

- INSS
- FGTS
- IRRF
- salário baixo
- salário alto
- teto do INSS

---

## IncomeTaxServiceTest

- sem imposto
- primeira faixa
- segunda faixa
- terceira faixa
- quarta faixa
- dependentes
- previdência
- pensão
- bônus
- múltiplas deduções

---

## VacationServiceTest

- férias integrais
- férias proporcionais
- venda de férias
- adicional de 1/3

---

## ThirteenthSalaryServiceTest

- primeira parcela
- segunda parcela
- proporcional
- descontos

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

Ideal para cálculos.

Exemplos:

- salários diferentes
- horas extras diferentes
- quantidade de dependentes
- faixas de IRRF
- faixas de INSS
- diferentes percentuais de benefícios

---

# Testes de Integração (H2)

Banco

```
jdbc:h2:mem:payroll;DB_CLOSE_DELAY=-1
```

Testar

- cadastro de funcionários
- processamento da folha
- persistência de benefícios
- férias
- encargos
- consultas
- rollback
- commit

---

# Cenários de Teste

## Funcionário

- salário inválido
- CPF duplicado
- funcionário inativo

---

## Horas Extras

- 50%
- 100%
- domingo
- feriado
- valor negativo

---

## Benefícios

- transporte
- alimentação
- saúde
- múltiplos benefícios

---

## Tributos

- INSS mínimo
- INSS máximo
- teto previdenciário
- todas as faixas do IRRF

---

## Férias

- período aquisitivo
- venda de férias
- férias proporcionais

---

## Décimo Terceiro

- funcionário admitido em janeiro
- admitido no meio do ano
- demissão antes do pagamento

---

# Teste Mestre (End-to-End)

```
shouldProcessCompletePayroll()
```

Fluxo

1. Cadastrar funcionário.
2. Definir salário.
3. Registrar horas extras.
4. Registrar adicional noturno.
5. Registrar benefícios.
6. Registrar faltas.
7. Calcular INSS.
8. Calcular FGTS.
9. Calcular IRRF.
10. Calcular salário bruto.
11. Calcular salário líquido.
12. Gerar holerite.
13. Persistir folha.
14. Validar todos os cálculos.
15. Confirmar consistência dos totais.

---

# Meta de Testes

| Camada | Quantidade Estimada |
|---------|--------------------:|
| EmployeeService | 20 |
| PayrollService | 50 |
| OvertimeService | 35 |
| AdditionalService | 25 |
| BenefitService | 30 |
| TaxService | 45 |
| IncomeTaxService | 50 |
| VacationService | 40 |
| ThirteenthSalaryService | 35 |
| Repository (H2) | 35 |
| Testes End-to-End | 20 |

**Total estimado:** **380 a 450 testes automatizados**

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Orientação a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| BigDecimal | ⭐⭐⭐⭐⭐ |
| Cálculos Financeiros | ⭐⭐⭐⭐⭐ |
| Legislação Trabalhista (Modelagem) | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐☆ |
| H2 Database | ⭐⭐⭐⭐☆ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐⭐ |
| Testes Parametrizados | ⭐⭐⭐⭐⭐ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |

---

# Resultado Esperado

Ao concluir este projeto, você terá implementado um sistema completo de **Folha de Pagamento**, abrangendo processamento salarial, horas extras, benefícios, férias, décimo terceiro e tributação. O projeto oferecerá centenas de cenários de testes, proporcionando domínio prático de **JUnit 5**, **Mockito**, **JDBC**, **H2** e modelagem de regras de negócio complexas, semelhantes às encontradas em sistemas corporativos de RH e Departamento Pessoal.