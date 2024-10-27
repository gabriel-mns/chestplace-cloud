# Chestplace - Módulo de Usuários

## Índice
- [Chestplace - Módulo de Usuários](#chestplace---módulo-de-usuários)
  - [Índice](#índice)
  - [Equipe](#equipe)
  - [Sobre a Chestplace](#sobre-a-chestplace)
  - [Sobre o Módulo de Usuários](#sobre-o-módulo-de-usuários)
  - [Recursos Implementados](#recursos-implementados)
    - [Cadastro de Usuários](#cadastro-de-usuários)
    - [Leitura de Usuário(s)](#leitura-de-usuários)
    - [Atualização de Dados de Usuário](#atualização-de-dados-de-usuário)
    - [Remoção de Usuário](#remoção-de-usuário)
  - [Documentos e Links (PjBL)](#documentos-e-links-pjbl)

## Equipe
- Carlos Krefer
- Gabriel Martins
- Guilherme Martins
- Gustavo Aymoto
- Stella Spricigo

## Sobre a Chestplace
A Chestplace é marketplace de camisetas, tanto para empreendedores individuais quanto para empresas. Visando facilitar o processo de compra e venda de camisetas.

## Sobre o Módulo de Usuários
O cadastro faz parte do conjunto de requisitos funcionais definidos para o projeto Chestplace, dessa forma, esse serviço contém a implementação dessa funcionalidade no sistema.

## Recursos Implementados

### Cadastro de Usuários
- **Endpoint**: `POST users/register`
- **Códigos de Retorno**:
    - **201 Created**: Cadastro realizado com sucesso.
    - **409 Conflict**: Já existe um usuário com o e-mail fornecido.
    - **400 Bad Request**: Algum dado informado não é válido (os campos inválidos são informados na response).

### Leitura de Usuário(s)
- **Endpoint**:
    - `GET users/{id}`: Retorna as informações do usuário específico pelo ID.
    - `GET users`: Retorna a lista de todos os usuários cadastrados.
- **Códigos de Retorno**:
    - **200 OK**: Usuário(s) encontrado(s) com sucesso.
    - **404 Not Found**: Usuário não encontrado.
    - **400 Bad Request**: O ID informado não é do tipo *Long*

### Atualização de Dados de Usuário
- **Endpoint**: `PUT users/{id}`
- **Códigos de Retorno**:
    - **204 No Content**: Dados do usuário atualizados com sucesso.
    - **404 Not Found**: Usuário não encontrado para atualização.
    - **400 Bad Request**: O ID informado não é do tipo *Long* ou algum dado informado não é válido (os campos inválidos são informados na response)

### Remoção de Usuário
- **Endpoint**: `DELETE users/{id}`
- **Códigos de Retorno**:
    - **204 No Content**: Usuário removido com sucesso.
    - **404 Not Found**: Usuário não encontrado para remoção.
    - **400 Bad Request**: O ID informado não é do tipo *Long*

## Documentos e Links (PjBL)
- [Documentação ARC42](./PJBL/CHESTPLACE%20-%20ARC42.md)
- [Software Architecture Canvas](./PJBL/CHESTPLACE%20-%20Software%20Architecture%20Canvas.pdf)
- [Azure Function GitHub (API Carrinho)](https://github.com/carloskrefer/chestplace-azure-function)
- [Backend Github (API Usuário)](https://github.com/gabriel-mns/chestplace-cloud)
- [Backend DockerHub (API Usuário)](https://hub.docker.com/repository/docker/guidossan/pjblcloud/general)

<br>

<img src="https://cdn.cookielaw.org/logos/c3f9b2c6-6e02-4c7e-a5ce-b8fdf03280c1/93559f54-c046-4574-896f-0f613cac1ee3/11f4243e-c2f9-4625-aac5-66589cc9e04d/logo_pucpr_horizontal_rgb.png" alt="Descrição da Imagem" width="200" height="auto" style="display: block; margin: auto;">