
# Java CRUD

Criação de um CRUD para poder criar, alterar e excluir clientes, utilizando um API Rest.

Utilizar:

- Banco de dados MySql.
- Adminer para administrar o banco de dados.
- Criação de queries usando JPA respository.
- Criação da imagem com a aplicação rodando em container docker.

## Regras do CRUD de cliente

Criar:

- Se o cliente não existir, incluir.
- Se o cliente existir, exibe mensagem "Cliente já existe!".

Alterar:

- Se o cliente não existir, exibe mensagem "Cliente não encontrado!".
- Se o cliente existir, executar alteração.

Excluir:

- Se o cliente não existir, exibe mensagem "Cliente não encontrado!".
- Se o cliente existir, executar exclusão.

Listar:

- Listar os clientes cadastrados.

## Containers

Para rodar em containers executar o seguinte comando:

```
./scripts/startContainers.sh
```

Irão subir os containers:

- Mysql: Banco de dados do backend
- Adminer: Aplicação web para administrar o banco de dados
> http://localhost:7070
    Servidor: mysql
    Usuário: root
    Senha: 12345
    Banco de Dados: teste-kafka
- Crud: Aplicação de CRUD

## Serviços

## Cliente

### Criar

```
Method: POST
URL: http://localhost:8080/clientes
Body (json):
{
"nome": "nome"
}
```

### Alterar

```
Method: PUT
URL: http://localhost:8080/clientes/{id}

Body (json):
{
"nome": "nome"
}
```

### Excluir

```
Method: DELETE
URL: http://localhost:8080/clientes/{id}
```

### Listar

```
Method: GET
URL: http://localhost:8080/clientes
```

### Recuperar

```
Method: GET
URL: http://localhost:8080/clientes/{id}
```

## Scripts

_startContainersParcial_: Sobe os seguintes containers para rodar localmente:

- Mysql
- Adminer

_removeContainersParcial_: Derrubar os containers parciais

_startContainers_: Sobe os seguintes containers para rodar localmente em containers docker:

- Mysql
- Adminer 
- Crud

_removeContainers_: Derrubar os containers

_publishKubernetes_: Sobe os seguintes containers para rodar no cluster kubernetes:

- Mysql
- Adminer
- Crud

_removeKubernetes_: Derrubar os containers no cluster kubernetes

## Observação

O projeto também roda executando por intermédio de uma IDE, basta abrir o projeto na sua IDE preferencial, mas necessita que antes suba os containers, executando o comando abaixo:

```
./scripts/startContainersParcial.sh
```
E executar as aplicações pela IDE e acessar os containers

## Kubernetes

Para rodar em containers no kubernetes executar o seguinte comando:

```
./scripts/startKubernetes.sh
```

Irão subir os containers e um de apoio:

- Mysql: Banco de dados do backend
- Adminer: Aplicação web para administrar o banco de dados
> http://java.crud.adminer.marceloagmelo.net

    Servidor: mysql
    Usuário: root
    Senha: 12345
    Banco de Dados: teste-kafka
- Crud: Aplicação de CRUD

## Observação

Precisa atualizar o arquivo /etc/hosts com os seguintes dados:

```
IP_CLUSTER java.crud.marceloagmelo.net java.crud.adminer.marceloagmelo.net
```
