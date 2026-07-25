# Sistema de Faturamento de Energia Elétrica
## Especificação do Projeto para Estudo de Java 25 + JUnit 5 + Mockito + H2 + JDBC (Sem Spring)

---

# Objetivo

Desenvolver um sistema completo de **Faturamento de Energia Elétrica**, utilizando **Java 25**, com foco em:

- Regras de negócio do setor elétrico
- Cálculos tarifários
- Tributos
- Consumo de energia
- Bandeiras tarifárias
- Descontos sociais
- Testes Unitários
- Testes de Integração
- Mockito
- JUnit 5
- JDBC Puro
- H2 Database

O sistema será uma aplicação **Console (JAR)** sem Spring Framework, priorizando arquitetura limpa e separação entre regras de negócio e persistência.

Todos os cálculos monetários deverão utilizar **BigDecimal**.

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
- JDBC Puro
- H2 Database
- Repository Pattern
- Service Layer
- DTO
- Mockito
- JUnit 5
- Testes Parametrizados
- Testes de Integração

---

# Arquitetura

```text
energy-billing-system
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
│       ├── report
│       ├── exception
│       ├── enums
│       ├── util
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

## Customer

```text
id
name
cpfCnpj
consumerUnit
installationNumber
socialTariff
status
```

---

## EnergyMeter

```text
id
consumerUnit
previousReading
currentReading
readingDate
```

---

## Tariff

```text
id
referenceMonth
energyPricePerKwh
flagValuePerKwh
icmsRate
pisRate
cofinsRate
```

---

## EnergyBill

```text
id
customer
referenceMonth
referenceYear
consumption
subtotal
taxes
discounts
totalAmount
dueDate
status
```

---

## Payment

```text
id
bill
paymentDate
amount
```

---

# Enumerações

```text
TariffFlag

GREEN
YELLOW
RED_LEVEL_1
RED_LEVEL_2
```

```text
BillStatus

OPEN
PAID
OVERDUE
CANCELED
```

---

# Exceptions

```text
BusinessException
```

```text
InvalidReadingException
```

```text
InvalidTariffException
```

```text
CustomerNotFoundException
```

```text
BillAlreadyPaidException
```

---

# Repositórios

```text
CustomerRepository

MeterRepository

TariffRepository

EnergyBillRepository

PaymentRepository
```

Implementações

```text
CustomerRepositoryJdbc

MeterRepositoryJdbc

TariffRepositoryJdbc

EnergyBillRepositoryJdbc

PaymentRepositoryJdbc
```

---

# Services

## CustomerService

Responsabilidades

- cadastrar consumidor
- atualizar dados
- ativar/desativar consumidor
- consultar unidade consumidora

---

## MeterReadingService

Responsabilidades

- registrar leitura
- validar leitura
- calcular consumo
- impedir leitura regressiva

---

## TariffService

Responsabilidades

- cadastrar tarifas
- cadastrar bandeiras
- atualizar impostos
- consultar tarifas

---

## EnergyBillService

Responsabilidades

- calcular consumo
- calcular valor da energia
- aplicar bandeira tarifária
- calcular impostos
- aplicar desconto social
- gerar fatura

---

## TaxService

Responsabilidades

- calcular ICMS
- calcular PIS
- calcular COFINS
- calcular total de tributos

---

## SocialTariffService

Responsabilidades

- validar elegibilidade
- calcular desconto
- aplicar desconto conforme faixa de consumo

---

## PaymentService

Responsabilidades

- registrar pagamento
- calcular juros
- calcular multa
- quitar fatura

---

## ReportService

Responsabilidades

- segunda via
- histórico de consumo
- histórico de pagamentos
- demonstrativo de impostos
- estatísticas de consumo

---

# Regras de Negócio

## Leitura

- leitura atual maior que anterior
- consumo maior ou igual a zero
- apenas uma leitura por competência

---

## Consumo

```
Consumo

=

Leitura Atual

-

Leitura Anterior
```

---

## Tarifa

- valor do kWh obrigatório
- bandeira obrigatória
- impostos configuráveis

---

## Bandeiras Tarifárias

- Verde
- Amarela
- Vermelha Patamar 1
- Vermelha Patamar 2

Cada bandeira adiciona um valor por kWh consumido.

---

## Desconto Social

- somente consumidores elegíveis
- desconto aplicado por faixa de consumo
- não permitir desconto duplicado

---

## Pagamentos

- impedir pagamento duplicado
- calcular multa
- calcular juros por atraso

---

# Fórmulas

## Consumo

```text
Consumo = Leitura Atual - Leitura Anterior
```

---

## Valor da Energia

```text
Consumo

×

Tarifa do kWh
```

---

## Bandeira Tarifária

```text
Consumo

×

Valor da Bandeira
```

---

## Subtotal

```text
Energia

+

Bandeira
```

---

## ICMS

```text
Subtotal

×

Alíquota
```

---

## PIS

```text
Subtotal

×

Alíquota
```

---

## COFINS

```text
Subtotal

×

Alíquota
```

---

## Total de Impostos

```text
ICMS

+

PIS

+

COFINS
```

---

## Valor Final

```text
Subtotal

+

Impostos

-

Desconto Social
```

---

# Entradas do Sistema

- Leitura anterior
- Leitura atual
- Valor do kWh
- Bandeira tarifária
- ICMS
- PIS
- COFINS
- Consumidor elegível à tarifa social

---

# Calcula

- Consumo
- Valor da energia
- Valor da bandeira
- ICMS
- PIS
- COFINS
- Tributos totais
- Desconto social
- Valor final da fatura

---

# Relatórios

- Conta de Energia
- Histórico de Consumo
- Consumo Mensal
- Tributos
- Pagamentos
- Inadimplência
- Estatísticas de Consumo

---

# Testes Unitários

## MeterReadingServiceTest

- leitura válida
- leitura menor que anterior
- consumo zero
- consumo elevado

---

## EnergyBillServiceTest

- consumo correto
- cálculo da tarifa
- bandeira verde
- bandeira amarela
- bandeira vermelha patamar 1
- bandeira vermelha patamar 2
- desconto social
- cálculo final

---

## TaxServiceTest

- ICMS
- PIS
- COFINS
- total de tributos
- alíquota zero
- alíquota inválida

---

## SocialTariffServiceTest

- consumidor elegível
- consumidor não elegível
- desconto parcial
- desconto integral
- sem desconto

---

## PaymentServiceTest

- pagamento normal
- pagamento duplicado
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

Ideal para:

- diferentes consumos
- diferentes tarifas
- diferentes bandeiras
- diferentes impostos
- consumidores com desconto social
- consumidores sem desconto

---

# Testes de Integração (H2)

Banco

```text
jdbc:h2:mem:energy;DB_CLOSE_DELAY=-1
```

Testar

- cadastro de consumidores
- registro de leituras
- cadastro de tarifas
- geração de faturas
- pagamentos
- consultas
- rollback
- commit

---

# Cenários de Teste

## Leituras

- consumo zero
- leitura inválida
- leitura regressiva
- consumo elevado

---

## Tarifas

- tarifa normal
- tarifa promocional
- bandeira verde
- amarela
- vermelha patamar 1
- vermelha patamar 2

---

## Tributos

- ICMS
- PIS
- COFINS
- todas as combinações de alíquotas

---

## Tarifa Social

- elegível
- não elegível
- desconto parcial
- desconto integral

---

## Pagamentos

- pagamento em dia
- pagamento em atraso
- multa
- juros
- segunda tentativa de pagamento

---

# Teste Mestre (End-to-End)

```text
shouldGenerateEnergyBillAndReceivePayment()
```

Fluxo

1. Cadastrar consumidor.
2. Registrar leitura anterior.
3. Registrar leitura atual.
4. Calcular consumo.
5. Definir bandeira tarifária.
6. Aplicar tarifa do kWh.
7. Calcular ICMS.
8. Calcular PIS.
9. Calcular COFINS.
10. Aplicar desconto social.
11. Gerar fatura.
12. Registrar pagamento.
13. Validar baixa da fatura.
14. Confirmar todos os cálculos.

---

# Meta de Testes

| Camada | Quantidade Estimada |
|---------|--------------------:|
| CustomerService | 20 |
| MeterReadingService | 30 |
| TariffService | 20 |
| EnergyBillService | 50 |
| TaxService | 40 |
| SocialTariffService | 35 |
| PaymentService | 35 |
| Repository (H2) | 35 |
| Testes End-to-End | 20 |

**Total estimado:** **280 a 330 testes automatizados**

---

# Competências Desenvolvidas

| Área | Nível |
|------|:-----:|
| Java 25 | ⭐⭐⭐⭐⭐ |
| Orientação a Objetos | ⭐⭐⭐⭐⭐ |
| SOLID | ⭐⭐⭐⭐⭐ |
| Clean Code | ⭐⭐⭐⭐⭐ |
| BigDecimal | ⭐⭐⭐⭐⭐ |
| Cálculos Tributários | ⭐⭐⭐⭐⭐ |
| Regras de Faturamento | ⭐⭐⭐⭐⭐ |
| JDBC Puro | ⭐⭐⭐⭐☆ |
| H2 Database | ⭐⭐⭐⭐☆ |
| Arquitetura em Camadas | ⭐⭐⭐⭐⭐ |
| JUnit 5 | ⭐⭐⭐⭐⭐ |
| Mockito | ⭐⭐⭐⭐⭐ |
| Testes Parametrizados | ⭐⭐⭐⭐⭐ |
| Testes de Integração | ⭐⭐⭐⭐⭐ |

---

# Resultado Esperado

Ao concluir este projeto, você terá desenvolvido um sistema completo de faturamento de energia elétrica, abrangendo leitura de medidores, cálculo de consumo, aplicação de tarifas e bandeiras, cálculo de ICMS, PIS e COFINS, desconto de tarifa social, emissão de faturas e registro de pagamentos. O projeto proporcionará centenas de cenários de testes automatizados com **JUnit 5**, **Mockito**, **JDBC** e **H2**, simulando regras de negócio semelhantes às utilizadas por concessionárias de energia.