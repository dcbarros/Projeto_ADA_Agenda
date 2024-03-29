﻿# Projeto_ADA_Agenda

Este é um programa simples de agenda telefônica desenvolvido em Java, servido como atividade avaliativa para o primeiro módulo do curso Santander Coders - Turma 1112, com uma interface de console. O programa permite a criação, listagem, edição e remoção de contatos, armazenando as informações em um arquivo de texto.

## Estrutura do Projeto
O projeto está organizado em pacotes para melhor modularização:

- **view:** Contém a classe Console, responsável pela interface de console e interação com o usuário.
- **models:** Contém as classes Contatos e Telefone que representam os objetos de contato e telefone, respectivamente.
- **controller:** Contém a classe ContatosController, que atua como um intermediário entre a interface de console e o serviço.
- **service:** Contém a classe ContatosService, que fornece os métodos para manipulação dos contatos e interação com o sistema de arquivos.

## Requisitos Funcionais 
- RF 01 - Não é permitido armazenar contatos com o mesmo id;
- RF 02 - Não é permitido armazenar contatos com telefones já cadastrados - Foi avaliado o DDD mais o número;
- RF 03 - Para realizar as ações, de remover e atualizar, será necessário informar o id do contato;

## Requisitos Não Funcionais

- RNF 01 - Utilizar arquivos de texto para armazenar os dados.

## Funcionalidades Principais

### 1. Listar Contatos

- Opção do menu: "1";
- Lista todos os contatos armazenados no arquivo contatos.txt, exibindo ID, nome, sobrenome e telefones associados.

### 2. Adicionar Contato

- Opção do menu: "2";
- Permite a adição de um novo contato, incluindo nome, sobrenome e um ou mais números de telefone;

### 3. Listar Contatos

- Opção do menu: "3";
- Permite a edição de um contato existente com base no ID. Oferece opções para atualizar o nome, sobrenome ou adicionar um novo número de telefone ao contato.

### 4. Remover Contato

- Opção do menu: "4"
- Remove um contato existente com base no ID fornecido.

### 5. Sair

- Opção do menu: "5"
- Encerra o programa.

## Requisitos e Execução

Certifique-se de que o Java esteja instalado em seu ambiente de desenvolvimento. Para executar o programa, basta chamar o método consoleApp() na classe Console a partir do método main na classe App.

## Observações

- O sistema de arquivos utilizado está configurado para um diretório específico (Agenda-Telefonica\\src\\db). Certifique-se de ajustar conforme necessário.

- A validação de contatos inclui verificações de comprimento máximo para nome e sobrenome, além de restrições para o formato do número de telefone (2 dígitos DDD e 8 ou 9 dígitos de número).

- Ao adicionar ou atualizar um contato, o sistema verifica se o número de telefone já existe na lista de contatos.

- O ID de cada contato é gerado automaticamente com base nos contatos existentes.

- O programa utiliza uma abordagem de leitura e escrita em arquivos para armazenar os contatos. Certifique-se de que o diretório e o arquivo contatos.txt tenham as permissões necessárias.
