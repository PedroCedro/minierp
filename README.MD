## Mini ERP

Versão atual: **0.3.0**

Projeto em fase inicial de construção do domínio, com foco em clareza,
coerência e evolução consciente.

Nesta versão:
– Domínio base consolidado (`Documento`, tipos e estados)
– Introdução do processo de **Conferência de Documentos**
– Comparação entre documentos (ex: pedido × nota)
– Resultado explícito de conferência (`OK` / `DIVERGENTE`)
– Regras de negócio cobertas por testes de domínio
– Estrutura de pacotes padronizada (document / conference)

Projeto de um Mini ERP construído com foco em estabilidade, clareza
e crescimento controlado do domínio.


## Propósito

Resolver problemas reais de negócio com um núcleo confiável, que rode hoje e continue funcionando amanhã.

## Stack Inicial

- Java + Spring Boot
- Maven
- PostgreSQL
- Frontend simples (Thymeleaf / REST)

## Etapas do Projeto

1. Identidade e acesso
2. Cadastros essenciais
3. Documentos (vendas/compras)
4. Financeiro básico
5. Relatórios
...

## Como rodar

1. Clone
2. Configurar DB
3. `mvn spring-boot:run`

