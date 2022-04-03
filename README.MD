# Mutant Checker

Em um futuro distante, na cadeia de evolução, os mutantes e os humanos estão cada vez mais próximos. Por esse motivo ficou muito difícil distinguir quem é humano e quem é mutante.

É aí que entra o Mutant Checker, uma aplicação criada para ajudar a identificar quem é humano ou mutante.

## Informações importantes sobre o projeto

**Url do repositório:** https://github.com/thiagomendes/mutant-checker

**Este repositório é privado, somente pessoas autorizadas possuem acesso!**

Realizar o clone do projeto através da URL: https://github.com/thiagomendes/mutant-checker.git

**Importante:** O repositório da aplicação está configurado para entrega contínua, sendo assim, qualquer commit realizado na branch main, iniciará o processo de teste, build e deploy no ambiente cloud Azure, utilizando os recursos do Github Actions.

## Testando, buildando e rodando localmente


### 1) Pré-Requisitos

* Java 11 ou superior
* Gradle (Versão recomendada 7.x)

### 2) Executando os testes

A partir do diretório raiz do projeto, executar o seguinte comando:

```bash
gradle clean test
```

Ao final da execução, os relatórios de teste serão gerados no diretório **"build/reports/tests"**, encontrado a partir da raiz do projeto.

### 3) Executando o build

A partir do diretório raiz do projeto, executar o seguinte comando:

```bash
gradle clean build
```

### 4) Rodando a aplicação

A partir do diretório raiz do projeto, executar o seguinte comando:

```bash
gradle bootRun
```

## Testando a API em ambiente público

Para teste da API, a aplicação foi hospedada na cloud da Microsoft (Azure), utilizando recursos gratuitos oferecidos pelo provedor.

**Documentação da API:** http://mutantchecker.azurewebsites.net/swagger-ui.html

**Url de teste:** http://mutantchecker.azurewebsites.net

No diretório "doc/api-test" do projeto, é possível encontrar algumas collections do postman para testar as apis.

No diretório "doc/load-test" do projeto, é possível encontrar um plano de testes criado para execução de testes de carga através do Jmeter.

**Importante:** Uma vez que a aplicação foi hospedada utilizando recursos gratuitos oferecidos pelo provedor (Azure), possuímos alguns limites. Sendo assim, dependendo da carga que se deseja testar, podemos ter um comprometimento de performance, uma vez que não é possível escalar a aplicação utilizando esse tipo de infraestrutura gratuita. 

## Rodando a aplicação com Mysql ao invés de H2

Com objetivo de facilitar a validação e execução do projeto, o mesmo está configurado para conectar em um banco de dados H2, em memória.

Porém a aplicação foi construída para que possa também conectar no mysql, para isso, no arquivo application.properties, presente dentro do diretório de resources da aplicação (src/main/resources/), basta remover os comentários das propriedades relacionadas ao mysql e comentar as propriedades relacionadas ao H2.

Feito isso, basta rodar a aplicação novamente apontando para uma instância de mysql.

Caso queira rodar uma instância de mysql localmente em container, basta rodar o seguinte comando:

```bash
docker run --name mutant-checker-mysql -e MYSQL_DATABASE=mutant-checker-db -e MYSQL_USER=mutant-checker-user -e MYSQL_PASSWORD=mutant-chcker-pass -e MYSQL_ROOT_PASSWORD=default-root-pass -p 3306:3306 -d mysql
```
