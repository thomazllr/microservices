# Microservices

Aplicação de estudo baseada em microservices que simula operações de um sistema bancário. O projeto está dividido em três serviços independentes, cada um responsável por um domínio específico da aplicação.

## Documentação

### [1. Dockerização](https://github.com/thomazllr/microservices/blob/main/docs/1.%20Dockeriza%C3%A7%C3%A3o.md)

## Microservices

- `accounts`: gestão de contas e clientes
- `cards`: gestão de cartões
- `loans`: gestão de empréstimos

## Tecnologias

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Estrutura do projeto

```text
microservices/
|-- accounts/
|-- cards/
|-- loans/
`-- docs/
```

## Observações

- O repositório usa abordagens diferentes de containerização entre os serviços.
- A pasta `docs` concentra a documentação complementar do projeto.
