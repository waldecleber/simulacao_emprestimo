# Sistema para cálculo de taxas de juros para simulação de empréstimo

# Backend
	Microserviço em back-end para manter os dados do cliente e calcular a taxa de juros para uma
simulação de empréstimo.

## Arquitetura	
* Spring Boot Rest
* Hibernate
* Liquibase
* Junit5
* MySQL
* Docker
 

## Instalando o sistema

- Faça o clone do projeto: 'https://github.com/waldecleber/simulacao_emprestimo.git'
- Navegue até o diretório: '/simulacao_emprestimo'
- Execute a instancia do docker através de um terminal: 'docker-compose up -d --build'
- Neste primeiro processo, a aplicação está configurando as dependencias, criando o banco de dados e as tabelas 
	de acordo com as configurações utilizadas pelo Liquibase.
- O backend está acessível pelo endereço: 'http://localhost:7001'

## Acessando o sistema

- Abra o navegador de sua preferencia no endereço: `http://localhost:4200/`. 


### Configurações
As configurações de conexões com o Banco de dados, estão declaradas como properties dentro do arquivo 'pom.xml'.

- application.yaml
	Neste arquivos estão descritas algumas configurações como porta, conexão com banco de dados e configurações de jpa.
	
- MySQL
	o nome do banco utilizado é 'simulacao_juros'
  
# Frontend

Este projeto foi gerado com [Angular CLI](https://github.com/angular/angular-cli) version 9.0.7.

## Configurando a aplicação
- Baixe o projeto e navegue até o diretório: '\TNT\front-end\emprestimo-app'
- Execute o seguinte comando no terminal para baixar as dependencias:
    - npm install

## Development server

Para executar o sistema, digite o comando: 'npm run start'.
Abra o navegador de sua preferencia no endereço: `http://localhost:4200/`. 


  
	
	

