# Chestplace: Arquitetura
Por:
- Carlos Krefer
- Gabriel Martins
- Guilherme Martins
- Gustavo Aymoto
- Stella Spricigo

# 1. Introdução e objetivos    

O Projeto Chestplace é um software que:
- Permite a divulgação de vendas de camisetas
- Permite a compra de camisetas


## 1.1. Contextualização
Tendo em vista que, de maneira geral, no mercado de camisetas, há uma grande segmentação (entre o mercado de camisetas novas e usadas, entre vendedores de pequeno ou grande porte, entre diferentes marcas), este software irá permitir a sua centralização, ou seja, tornará possível a venda de camisetas novas e usadas em um único espaço, tanto por vendedores individuais como por empresas.

## 1.2. Overview dos Requisitos
O sistema deverá permitir a compra e venda de camisetas novas e usadas, além do gerenciamento de vendas e compras. Será possível que os usuários gerenciem seus próprios perfis, cadastrem novos usuários e autentiquem os já cadastrados. O sistema também deverá oferecer opções de pagamento por cartão de crédito, débito e Pix.

### 1.2.1. _Principais funcionalidades_
- Cadastrar anúncios de camisetas
- Comprar camisetas
- Gerenciar vendas realizadas
- Gerenciar compras realizadas

## 1.3. Objetivos de Qualidade

| Nr. |       Qualidade        |                                                                                Motivação                                                                                 |
| :-: | :--------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|  1  |    Disponibilidade     |                       Como a fonte de renda é a venda de produtos, é necessário estar sempre disponível para que os clientes comprem os produtos.                        |
|  2  |     Escalabilidade     |                Por ser uma startup em crescimento, é necessário ser escalável para suportar uma quantidade crescente de usuários conforme a necessidade.                 |
|  3  |   Confidencialidade    | Dado que um e-commerce trata de dados pessoais sensível (CPF, e-mail, endereço, número de cartão de crédito, CEP) um vazamento poderia causar um prejuízo significativo. |
|  4  | Engajamento de Usuário |                                               Se o cliente ficar e voltar ao site, ele irá comprar mais e dar mais renda.                                                |


## 1.4. Stakeholders
A lista a seguir contém os stakeholders do sistema.


|   Nome    |                                 Objetivos/Limites                                  |
| :-------: | :--------------------------------------------------------------------------------: |
| Comprador | Ter que acessar diversos sites de camisetas para encontrar um modelo de seu gosto. |
| Vendedor  |                       Divulgar o seu empreendimento online.                        |


# 2. Restrições Arquiteturais

## 2.1. Restrições técnicas

|  ID  |                   Restrição                    |                                                         Motivação                                                         |
| :--: | :--------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------: |
| RT01 |             Implementação backend              |                           A aplicação deve utlizar Azure Functions para criação de controllers.                           |
| RT02 | Implementação frontend em JavaScript (Angular) |                                      A aplicação deve utlizar JavaScript e Angular.                                       |
| RT03 | A aplicação deve suportar diferentes browsers  | A aplicação deverá ser compatível com os browsers Google Chrome, Microsoft Edge, Firefox, Opera, Opera GX, Safari e Brave |
| RT04 |        Integração com sistemas externos        |                 A aplicação deverá ser integrada com o sistema do ViaCep, Firebase e sistema de pagamento                 |

## 2.2. Restrições Organizacionais

|  ID  |                   Restrição                   |                                     Motivação                                      |
| :--: | :-------------------------------------------: | :--------------------------------------------------------------------------------: |
| RO01 |                     Time                      | Carlos Krefer, Gabriel Martins, Guilherme Martins, Gustavo Aymoto, Stella Spricigo |
| RO02 |                IDE do Projeto                 |                      Será usado o VSCode para desenvolvimento                      |
| RO03 | Configuração e Gerenciamento de versionamento |            O versionamento será feito por meio de repositório no GitHub            |
| RO04 |                  Cronograma                   |                      A data final do projeto será 15/nov/2024                      |

## 2.3. Convenções
### 2.3.1. _Lista de Convençoes_
| ID  |          Restrição          |                                     Motivação                                      |
| :-: | :-------------------------: | :--------------------------------------------------------------------------------: |
| C1  |           Idioma            | Português. A documentação e implementação do projeto será totalmente em Português. |
| C2  | Documentação da Arquitetura |              Será utilizado o modelo em Português do ARC42 e C4 Model              |

# 3. Escopo e Contexto do Sistema
**Diagrama Nível I**

    C4Context
        title Marketplace de camisetas

        Person(usuario, "Usuário", "Usuário comprador ou vendedor de camisetas.")
        System(marketplace, "Marketplace de camisetas", "Plataforma para vender e comprar camisetas.")

        BiRel(usuario, marketplace, "Usa")

        System_Ext(apiCEP, "ViaCEP", "API do sistema que retorna informações sobre o CEP fornecido.")

        System_Ext(sistemaFinanceiro, "Sistema Financeiro", "API do sistema financeiro.")

        System_Ext(firebase, "Firebase", "Sistema de autenticação.")

        BiRel(marketplace, apiCEP, "Usa")
        BiRel(marketplace, sistemaFinanceiro, "Usa")
        BiRel(marketplace, firebase, "Usa")

**Diagrama Nível II**
     
    C4Context
        title Marketplace de camisetas

        Person(usuario, "Usuário", "[Pessoa]<br>Usuário comprador ou vendedor de camisetas.")
        System(marketplace, "Marketplace de camisetas", "[Angular]<br>Frontend da plataforma para vender e comprar camisetas.")

        Boundary(back, "Sistema backend") {
            System(apiAuth, "API Autenticação", "[Java]<br>Microserviço para autenticação.")
            System(apiGateway, "API Gateway", "[Amazon API Gateway]<br>Roteamento para serviços.")
            System(apiMarketplace, "API Marketplace", "[Java]<br>Microserviço para as<br>principais funcionalidades do marketplace.")
            System(apiCarrinho, "API Carrinho", "[Javascript]<br>Azure Function para o<br>carrinho de compras.")
            SystemDb(bancoMongo, "Banco de dados<br>não relacional", "[MongoDB]<br>Armazena<br>dados de carrinhos.")
            SystemDb(bancoSQL, "Banco de dados<br>relacional", "[SQLServer]<br>Armazena<br>dados do marketplace.")
        }

        System_Ext(apiCEP, "ViaCEP", "[Sistema de software]<br>API do sistema que retorna informações sobre o CEP fornecido.")
        System_Ext(firebase, "Firebase", "[Sistema de software]<br>Sistema de autenticação.")
        System_Ext(sistemaFinanceiro, "Sistema Financeiro", "[Sistema de software]<br>API do sistema financeiro.")

        BiRel(usuario, marketplace, "Usa")
        BiRel(marketplace, apiGateway, "Usa", "HTTP/JSON")
        BiRel(marketplace, apiCEP, "Usa", "HTTP/JSON")
        BiRel(apiGateway, apiAuth, "Usa", "HTTP/JSON")
        BiRel(apiGateway, apiCarrinho, "Usa", "HTTP/JSON")
        BiRel(apiGateway, apiMarketplace, "Usa", "HTTP/JSON")
        BiRel(apiAuth, firebase, "Usa", "HTTP/JSON")
        BiRel(apiCarrinho, bancoMongo, "Usa", "<br>MongoDB<br>Wire<br>Protocol")
        BiRel(apiMarketplace, bancoSQL, "Usa", "TDS")
        BiRel(apiMarketplace, sistemaFinanceiro, "Usa", "HTTP/JSON")

        UpdateRelStyle(apiAuth, firebase, $offsetY="-80", $offsetX="-20")
        UpdateRelStyle(apiGateway, apiAuth, $offsetY="-40", $offsetX="-30")
        UpdateRelStyle(apiGateway, apiMarketplace, $offsetY="-40", $offsetX="-30")
        UpdateRelStyle(apiMarketplace, bancoSQL, $offsetY="0", $offsetX="10")
        UpdateRelStyle(apiCarrinho, bancoMongo, $offsetY="-50", $offsetX="-20")
        UpdateRelStyle(apiGateway, apiCarrinho, $offsetY="-20", $offsetX="-60")
        UpdateRelStyle(marketplace, apiGateway, $offsetY="-180", $offsetX="60")
        UpdateRelStyle(marketplace, apiCEP, $offsetY="0", $offsetX="-80")
        UpdateRelStyle(usuario, marketplace, $offsetY="-10", $offsetX="0")
        UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")

### 3.1.1. _Backend_

O sistema e o banco de dados estão disponiblizados em um servidor no Azure.

O sistema se comunica com a API do ViaCep por meio de requisições GET para consultar dados de endereço.

Há também a utilização de uma API de pagamentos para realização de ações financeiros dentro do sistema.

Além disso, há a utlização do Firebase para autenticação de usuários no sistema.


### 3.1.2. _Frontend_

O frontend será desenvolvido em Angular, sendo disponibilizado em como um sistema Web, se comunicando com o usuário via requisições HTTP.

# 4. Solução estratégica

O sistema "Chestplace" é baseado em três entidades principais: comprador, vendedor e produto. Nele, um vendedor publica um produto, que pode ser adquirido posteriormente por um comprador.

O sistema é desenvolvido em Spring Boot, escolhida pela familiaridade da equipe com a tecnologia e sua extensa base de bibliotecas que facilitam tanto o desenvolvimento quanto a documentação. Além disso, utiliza-se o Azure Functions, conforme exigido pela disciplina.

Segue o padrão "database per service", distribuído da seguinte forma:

- Carrinho de compras: Banco de dados não relacional com MongoDB.
- Autenticação: Banco relacional com Firebase. 
- Demais funções do sistema: Banco relacional em SQLServer.

A comunicação entre APIs segue a arquitetura REST, padronizando o uso dos verbos HTTP e formato de dados JSON.

# 5. Build Block view

**Diagrama Nível III - API Marketplace**

    C4Component
        title Diagrama de componentes (Nível III) - API Marketplace

        Container(apiGateway, "API Gateway", "Amazon API Gateway", "Roteamento para serviços.")
        Container(bd, "Banco de dados relacional", "SQLServer", "Armazena dados do marketplace.")
        
        Container_Boundary(apiMarketplace, "API Marketplace") {
            Component(user, "User", "Java", "Trata de funcionalidades relacionadas a usuários.")
            Component(image, "Image", "Java", "Trata de funcionalidades relacionadas<br>a imagens de produtos.")
            Component(product, "Product", "Java", "Trata de funcionalidades relacionadas<br>aos produtos.")
            Component(sale, "Sale", "Java", "Trata de funcionalidades relacionadas<br>a vendas.")
            Component(common, "Common", "Java", "Contém recursos comuns, como autenticação JWT e exceções.")
        }

        Rel(apiGateway, user, "Uses")
        Rel(apiGateway, image, "Uses")
        Rel(apiGateway, product, "Uses")
        Rel(apiGateway, sale, "Uses")
        Rel(image, bd, "Uses")
        Rel(user, bd, "Uses")
        Rel(product, bd, "Uses")
        Rel(sale, bd, "Uses")
        Rel(image, common, "Uses")
        Rel(user, common, "Uses")
        Rel(product, common, "Uses")
        Rel(sale, common, "Uses")

        UpdateLayoutConfig($c4ShapeInRow="4", $c4BoundaryInRow="1")
        UpdateRelStyle(apiGateway, user, $offsetY="80", $offsetX="-5")
        UpdateRelStyle(apiGateway, image, $offsetY="90", $offsetX="90")
        UpdateRelStyle(apiGateway, product, $offsetY="120", $offsetX="260")
        UpdateRelStyle(apiGateway, sale, $offsetY="140", $offsetX="450")
        UpdateRelStyle(user, bd, $offsetY="-95", $offsetX="10")
        UpdateRelStyle(image, bd, $offsetY="-95", $offsetX="-95")
        UpdateRelStyle(product, bd, $offsetY="-85", $offsetX="-160")
        UpdateRelStyle(sale, bd, $offsetY="-130", $offsetX="-320")
        UpdateRelStyle(image, common, $offsetY="-10", $offsetX="-20")
        UpdateRelStyle(product, common, $offsetY="0", $offsetX="-20")

**Diagrama Entidade Relacionamento do SQLServer**
```
erDiagram
    tb_sale }o--|| tb_user : relacao
    tb_sale {
        string id
        datetime data_hora_venda
        integer status_entrega
        float valor_venda
        string comprador_id
        string estoque_id
    }
    tb_user ||--|{ tb_user_tb_address : relacao
    tb_user {
        string id
        string name
        string senha
        string email
        string created_at
    }
    tb_user_tb_address {
        string comprador_id
        string endereco_id
        string tipo_endereco
    }
    tb_user_tb_address }|--|| tb_address : relacao
    tb_address {
        string id
        string cep
        string state
        string city
        string street
        string number
        string district
        string extra
    }
    tb_product_type {
        string id
        string title
        float price
        float price_discount
        string description
    }
    tb_product_type ||--o{ tb_supply : relacao
    tb_sale |o--|| tb_supply : relacao
    tb_supply {
        string id
        string product_type_id
    }
    tb_product_type ||--o{ tb_image : relacao
    tb_image {
        string id
        string name
        long size
        string extencion
        datetime uploadTime
        string tags
    }
```

**Dicionário de variáveis do SQLServer**

| Tabela | Variável | Descrição                 | Tipo    | Pode ser nula? |
| :----: | :------: | :-----------------------: | :-----: | :------------: |
| tb_sale | id | Identificador da venda | string | Não |
| tb_sale | data_hora_venda | Data e hora que a venda foi realizada | datetime | Não |
| tb_sale | status_entrega | Status da entrega do produto | string | Não |
| tb_sale | valor_venda | Valor da venda | float | Não |
| tb_sale | comprador_id | Identificador do comprador | integer | Não |
| tb_sale | estoque_id | Identificador do produto no estoque | string | Não |
| tb_user | id | Identificador do usuário | string | Não |
| tb_user | name | Nome do usuário | string | Não |
| tb_user | senha | Senha do usuário | string | Não |
| tb_user | email | E-mail do usuário | string | Não |
| tb_user_tb_address | comprador_id | Identificador do usuário comprador | string | Não |
| tb_user_tb_address | endereco_id | Identificador do endereço do usuário comprador | string | Não |
| tb_user_tb_address | tipo_endereco | Informa tipo de endereço do comprador (entrega ou cobrança) | string | Não |
| tb_address | id | Identificador do endereço | string | Não |
| tb_address | cep | Código do CEP do endereço | string | Não |
| tb_address | state | Nome do estado | string | Não |
| tb_address | city | Nome da cidade | string | Não |
| tb_address | street | Nome da rua | string | Não |
| tb_address | number | Número da residência | string | Sim |
| tb_address | district | Bairro do endereço | string | Sim |
| tb_address | extra | Informação adicional para auxiliar na localização | string | Não |
| tb_supply | id | Identificador do produto físico | string | Não |
| tb_supply | product_type_id | Identificador do tipo de produto | string | Não |
| tb_product_type | id | Identificador do tipo de produto | integer | Não |
| tb_product_type | title | Nome do tipo de produto | string | Não |
| tb_product_type | price | Preço do produto | float | Não |
| tb_product_type | price_discount | Preço do produto (caso haja promoção) | float | Sim |
| tb_product_type | description | Descrição do produto | string | Sim |
| tb_image | id | Identificador da imagem do produto | string | Não |
| tb_image | name | Nome da imagem | string | Não |
| tb_image | size | Tamanho da imagem | long | Não |
| tb_image | extencion | Extensão da imagem | string | Não |
| tb_image | uploadTime | Data de upload da imagem | datetime | Não |
| tb_image | tags | Tags para busca da imagem | string | Não |

**Estrutura do documento no MongoDB**

```
{
  "_id": {
    "$oid": ""
  },
  "produtos": [
    {
      "id": {
        "$numberInt": "0"
      },
      "titulo": "",
      "preco": {
        "$numberDecimal": "0"
      },
      "preco_promocional": {
        "$numberDecimal": "0"
      },
      "descricao": ""
    }
  ],
  "compradorId": {
    "$numberInt": "0"
  }
}
```

**Dicionário de variáveis do MongoDB**

| Coleção | Variável | Descrição                 | Tipo    | Obrigatório? |
| :-----: | :------: | :-----------------------: | :-----: | :------------: |
| carrinhos | _id | Identificador único do carrinho na coleção | ObjectId | Sim |
| carrinhos | produtos | Array de produtos do carrinho | Array | Sim |
| carrinhos | id | Identificador do tipo de produto | Int32 | Sim |
| carrinhos | titulo | Título do produto | String | Sim |
| carrinhos | preco | Preço do produto | Decimal128 | Sim |
| carrinhos | preco_promocional | Preço do produto em promoção | Decimal128 | Sim |
| carrinhos | descricao | Descrição do produto | String | Sim |
| carrinhos | compradorId | Identificador do comprador dono do carrinho | Int32 | Sim |

**Rotas da API Carrinho com Azure Functions**
| URL   | Body (JSON) | Método | Descrição |
| :---: | :---------: | :----: | :-------: |
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Nenhum | GET | Busca o carrinho a partir do identificador do comprador.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Nenhum | DELETE | Remove o carrinho a partir do identificador do comprador.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Array de produtos. A estrutura do array de produtos pode ser conferido na propriedade"produtos" mencionado na "Estrutura do Documento no MongoDB" | PUT | Atualiza o carrinho, substituindo os produtos existentes para os produtos informados no Body da requisição, para o compradorId informado.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos | Carrinho. A estrutura do carrinho pode ser conferida na "Estrutura do Documento no MongoDB" | POST | Insere o carrinho com os produtos informados no Body da requisição, para o compradorId informado.

**Rotas da API de Usuário**
| URL   | Body (JSON) | Método | Descrição |
| :---: | :---------: | :----: | :-------: |
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Nenhum | GET | Buscar os dados do usuário com o ID especificado.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users | Nenhum | GET | Buscar os dados de todos os usuários.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Nenhum | DELETE | Remove o usuário a partir do ID.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Objeto com os dados do usuário atualizados. | PUT |Atualiza o usuário com o id Especificado
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/register | Objeto com dados do usuário (nome, email, senha e tipo de usuário) | POST | Cadastra um novo usuário no banco de dados.

**Rotas da API Gateway**
| URL   | Body (JSON) | Método | Descrição |
| :---: | :---------: | :----: | :-------: |
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Nenhum | GET | Buscar os dados do usuário com o ID especificado.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users | Nenhum | GET | Buscar os dados de todos os usuários.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Nenhum | DELETE | Remove o usuário a partir do ID.
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/{id} | Objeto com os dados do usuário atualizados. | PUT |Atualiza o usuário com o id Especificado
| https://chestplace-user-service.victoriousrock-5abecb91.australiaeast.azurecontainerapps.io/users/register | Objeto com dados do usuário (nome, email, senha e tipo de usuário) | POST | Cadastra um novo usuário no banco de dados.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Nenhum | GET | Busca o carrinho a partir do identificador do comprador.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Nenhum | DELETE | Remove o carrinho a partir do identificador do comprador.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos/{compradorId} | Array de produtos. A estrutura do array de produtos pode ser conferido na propriedade"produtos" mencionado na "Estrutura do Documento no MongoDB" | PUT | Atualiza o carrinho, substituindo os produtos existentes para os produtos informados no Body da requisição, para o compradorId informado.
| https://chestplace-carrinho.azurewebsites.net/api/carrinhos | Carrinho. A estrutura do carrinho pode ser conferida na "Estrutura do Documento no MongoDB" | POST | Insere o carrinho com os produtos informados no Body da requisição, para o compradorId informado.
| https://chestplace-bff.livelywave-bcf1a711.brazilsouth.azurecontainerapps.io/api/aggregate/usuario-carrinho/{usuarioID}| Nenhum | GET | Retorna os dados do usuário agregado com o carrinho.


# 6. Visão de execução
---

#### 6.1 Visão Geral dos Componentes em Tempo de Execução

O sistema é estruturado com os seguintes componentes principais durante a execução:
| Componente| Descrição |
| :----: | :------: |
| Frontend | Responsável por renderizar as interfaces de usuário, lidar com interações dos usuários e se comunicar com os serviços de backend por meio de APIs REST.  |
| Backend |  Funciona como a camada de lógica central do sistema, implementado com Spring Boot , processando requisições, autenticação de usuários, processamento de pedidos e interação e conexão com microserviços|
| Banco de dados | Armazena todos os dados relevantes, incluindo listagens de produtos, informações de usuários, transações e dados de sessão em SQL Server em Microsoft Azure. |



#### 6.2 Principais Cenários de Execução

**Visão do usuário  na compra**
O usuário Comprador realiza a ação da navegação até a compra
   - O usuário acessa o site através de um navegador, iniciando a comunicação com o frontend.
   - O frontend busca as listagens de produtos do serviço de backend usando uma chamada à API REST.
   - O usuário seleciona um produto e inicia a compra.
   - O frontend comunica a intenção de compra ao serviço de backend.
  - O backend, valida a sessão do usuário, processa o pedido e atualiza o inventário de maneira segura e escalável.
   - Um serviço de pagamento é invocado para lidar com a transação (crédito, débito ou Pix).
   - Após a conclusão bem-sucedida, os detalhes da transação são armazenados no banco de dados, e a confirmação é enviada ao usuário.

**Visão dos vendedores no gerenciamento de produtos**
   - Os vendedores podem listar novos produtos através de uma interface dedicada.
   - O frontend coleta e envia os dados do produto para o backend.
   - O backend valida e armazena os dados do produto no banco de dados, utilizando recursos do Spring Boot.
   - Atualizações nas listagens de produtos, como mudanças de preço ou disponibilidade, são tratadas de forma semelhante, através de um ciclo de interação validado entre o frontend e o backend.

**Visão do usuário na autenticação**
   - Um novo usuário pode optar por se registrar.
   - Após o envio, o frontend envia uma solicitação ao backend, que cria um novo registro de usuário após validação.
   - Usuários já existentes podem se autenticar com seus dados; estas são validadas contra os registros armazenados no banco de dados, e passados para uma sessão é criada em caso de sucesso.



#### 6.3 Comunicação e Fluxo de Dados
- **Comunicação Frontend-Backend**: API REST sobre HTTPS, garantindo a transferência segura de dados.
- **Comunicação Backend-Banco de Dados**: Utiliza uma conexão segura para acessar e gerenciar os dados, seguindo padrões de integridade transacional e consistência de dados.

#### 6.4 Tratamento de Falhas
- **Erros de Rede**: O sistema realiza novas tentativas de requisições falhas com uma política de retrocesso limitada.
- **Erros de Transação**: As transações são tratadas utilizando procedimentos de rollback para manter a consistência dos dados.

---


# 7. Conceitos de implementação
---


#### Tecnológias e Framework usados
- **Framework:** Spring Boot
- **Linguagem:** JavaScript
- **Ferramenta de Build:** Maven
- **Containerização:** Suporte ao Docker presente .
- **Nuvem para banco de dados:**  Microsoft Azure

| Node/Artifact                  | Descrição                                                                                 |
|-----------------------|-------------------------------------------------------------------------------------------|
| **Servidor de Aplicação** | Contêiner Docker em  Microsoft Azure que processa requisições e fornece APIs REST. |
| **Servidor de Banco de Dados** | Servidor SQLServer no Microsoft Azure. |

### Pré-requisitos

  - Docker instalado e configurado.
  - Imagem da aplicação gerada com Maven.
  - Variáveis de ambiente para configuração.
  - Imagem do banco de dados.
  - Configuração inicial do banco.

---

# 9. Decisão de design
### 9.1. Problema
O problema apresentado é a fragmentação do mercado de camisetas, com a venda separada entre camisetas novas e usadas e por diferentes tipos de vendedores. Além disso, a exigência de cadastro para compras pode tornar a experiência do usuário mais lenta e onerosa. O software proposto visa centralizar a venda de camisetas de diversos segmentos e permitir compras sem a obrigatoriedade de cadastro.
 
### 9.2. Restrições
- A aplicação deve suportar arquivos de imagem dos produtos em diversos formatos.
- O foco da aplicação deve-se sentrar em uma aplicação backend estruturada em slices moderna para Spring boot.


### 9.3. Suposições
- Uma aplicação sem padrões de componentes podem prejudicar a usabilidade do usuário no site
- Tipos variados de arquivos de imagem podem prejudicar o desempenho da aplicação


# 10. Cenários de qualidade
## Árvore de qualidade
    graph LR
    subgraph Qualidades
        direction LR
        A((Eficiência)) --> B((Fast entering of mileages))
        A --> C((Testability))
        C --> D((Coverage))
        C --> E((Independent from external services))
        A --> F((Interoperabilidade))
        F --> G((REST endpoints))
        A --> H((Atractiveness))
        H --> I((Intuitive charts))
    end
## Cenários de avaliação

| Nº | Cenário   | Atributo de Qualidade | Decisão Arquitetural | Tradeoffs | Resultados |
| :---: | :---------: | :----: | :-------: | :-------: |:-------: |
| 1 | Alta demanda durante temporada de promoções | Disponibilidade, Escalabilidade, Confidencialidade e Engajamento de Usuário | Utilização de API Gateway e arquitetura de microsserviços ou arquitetura monolítica | API Gateway ajuda na implementação de políticas de segurança e no balancemanto de carga, no entanto gera um ponto único de falha no sistema, aumenta a latência do sistema, torna a manutenção mais complexa e aumenta o custo do sistema. <br><br> Os microsserviços melhoram a escalibilidade e resiliência do sistema, porém pode sobrecarregar a rede. <br><br> A arquitetura monolítica pode reduzir a latência em caso de poucos acessos mas é pouco escalável. | Melhora a experiência do usuário com a utilização de um API Gateway pois facilita o gerenciamento de tráfego e o balanceamento de carga, garantindo alta disponibilidade mesmo em cenários de alta demanda. A arquitetura de microsserviços é altamente escalável, pois permite que cada serviço seja escalado independentemente, atendendo de forma eficiente à alta demanda durante promoções, enquanto a arquitetura monolítica pode enfrentar dificuldades com picos de demanda, exigindo reestruturação.
| 2 | Indisponibilidade de Serviços Externos.| Disponibilidade e Engajamento de Usuário | Utilização de um arquitetura de microserviços com circuit breaker para gerenciar as falhas em serviços externos, como APIs de pagamento, em contra partida aumenta a complexidade do sistema e demanda mais testes e monitoramento.  | Com essas implementações, o sistema melhora a resiliência ao lidar com falhas de serviços externos. Em caso de queda de uma API de pagamento, por exemplo, o sistema pode ativar métodos de pagamento alternativos, mantendo o serviço disponível para o usuário e reduzindo o impacto na experiência de compra. | O sistema continua funcional e evita quedas totais durante a indisponibilidade de serviços externos, pois os circuit breakers evitam que a falha se espalhe, para manter o engajamento o  cliente é informado de forma transparente sobre eventuais atrasos ou problemas, aumentando a confiança e incentivando a permanência e o retorno ao site. 
| 3 | Recuperação Após Desastre Total: Simular a perda completa de um data center e testar a recuperação a partir de backups em um local diferente. |Disponibilidade e Engajamento do Usuário | Utilização de serviços de backups  automático da Azure | Backups automáticos costumam custar caro, e também se a parte em específica do sistema for crítica e precisar de recuperação imediata pode ser necessário contratar o serviço de recuperação por zona de redundância que aumenta ainda mais o custo  | Os usuários percebem uma recuperação rápida, minimizando a frustração e o impacto negativo na experiência, o que reduz o risco de abandono e de insatisfação. A arquitetura de recuperação em zona geograficamente redundante permite que o sistema restabeleça rapidamente a operação após um desastre total

### Intereperabilidade/REST Endpoints

A arquitetura deve ser concebida para garantir que a aplicação possa interagir e operar de forma eficiente com sistemas e serviços externos, independentemente das tecnologias ou plataformas utilizadas. 
A arquitetura deve seguir boas práticas de verbos http status, ao menos deve conter uma saída para sucesso e outra para erro. 

# 11. Visão de riscos e dívidas técnicas
Chestplace não entrou em produção e está em desenvolvimento, porém é identificado riscos.
Há possibilidade de perda de conexão com hspodagem azure, ou ainda falha na integração contínua para deploy automático. O risco é mitigado através de backups regulares do arquivo de banco de dados relacional e utilizar multicloud nos microserviços. 

# 12. Dívidas Técnicas
  1. Falta de Testes Unitários nos Serviços e no BFF
  2. Falta de testes de escalabilidade
  3. Hardcoding de URLs no BFF
  4. Falta de Integração Contínua/Entrega Contínua (CI/CD)
  5. Desconsideração de Segurança


# 13. Glosário 
| Termo | Definição |
| :----: | :------: |
| Chesplace | Nome do projeto |
| ViaCEP | Aplicação externa utilizada para consultar CEP |
| Produto | Refere-re a qualquer camiseta que esteja sendo vendinda |
| Usuário | Todo comprador e vendedor |
| API | Framework para aplicações web |
| SpringBoot | Gerenciador de dependências e build |
| Maven | Interface de comunicação entre sistemas. |
| tb | Tabela |
| Framework | Estrutura para desenvolvimento de software |
