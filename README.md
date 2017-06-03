# foodTrack

Foodtrack é uma pequena aplicação para votação de restaurantes. A aplicação mantém serviços para gerenciar funcionários, restaurantes, votação e seu resultado.

## Para Começar - Utilização do Jar

O link para o projeto está disponibilizado no https://github.com/phsgregorio/foodTrack. O projeto pode ser baixado repositório para versão de desenvolvimento ou pode ser
baixado ser jar que inicializa com auxílio do Spring Boot os serviços pelo prompt de comando do sistema operacional. Aqui cobriremos apenas a utilização da aplicação pelo jar.

### Prerequisites

Um ambiente de execução de Java 8 deve estar disponível na máquina que irá hospedar o serviço.
Você pode baixar um ambiente adequado no segunte link:

* [http://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html] - Oracle Java Runtime Environment

### Installing

Para fazer a instalação do aplicativo(ou iniciar os serviços disponíveis) confira se o Java está no Classpath do seu computador.
Após isso abra o prompt de comando do seu sistema operacional. Então execute o seguinte comando:

* java -jar foodTrack-1.0.0.jar

Ao executar o comando o Spring Boot irá inicializar os serviços. Podemos então acessar o seguinte endereço:

http://localhost:8080/foodtrack/funcionarios/

Estaremos nesse link acessando o serviço que retorna a lista de funcionários do sistema. Como ele acabou
de ser inicializado deveremos ter como retorno apenas o seguinte resultado do tipo application/json :

[]

## Serviços

A aplicação tem disponível alguns serviços que podem ser acessados através das URLs que informaremos a seguir:

### Lista de Funcionários

http://localhost:8080/foodtrack/funcionarios [GET]

Serviço responsável pelo retorno da lista de funcionários da aplicação. O serviço retorna um JSON com a seguinte estrutura:

[
	{
		"id": 1,
	    "nome": "Pedro",
	    "ativo": "ativo"
	},
	{
		"id": 2,
		"nome": "Henrique Diniz",
		"ativo": "ativo"
	},
	...
]

### Inserção de Funcionários

http://localhost:8080/foodtrack/funcionarios [POST] (application/json)
@param Funcionario funcionario

Serviço responsável pela inserção de um novo funcionário. O serviço consome um json de entrada conténdo as informações do funcionário.

{
	"id": 15,
	"nome": "Roberto Carlos",
	"ativo": "inativo"
}

### Lista de Restaurantes

http://localhost:8080/foodtrack/restaurantes [GET]

Serviço responsável pelo retorno da lista de restaurantes da aplicação. O serviço retorna um JSON com a estrutura da lista de Restaurantes:

  [
	    {
	      "id": 1,
	      "nome": "San Ro",
	      "endereco": "Rua Professor Moraes"
	    },
	    {
	      "id": 2,
	      "nome": "Yan Shan Zai",
	      "endereco": "Avenida Getulio Vargas"
	    },
	    ...
	]

### Inserção de Restaurantes

http://localhost:8080/foodtrack/restaurantes [POST] (application/json)
@param Restaurante restaurante

Serviço responsável pela inserção de um novo restaurante. O serviço consome um json como entrada conténdo as informações do restaurante.

{
	"id": 4,
	"nome": "Piu Pane",
	"endereco": "Rua Professor Moraes"
}

### Lista de Votos de Funcionário por Data

http://localhost:8080/foodtrack/votofuncionarios/2017-06-01 [GET]
@param data String conténdo a data desejada do resultado

Serviço que retorna uma lista com todos os votos realizados na data informada na requisição.O serviço retorna JSON de acordo com a seguinte estrutura:

{
	"idFuncionario": 1,
	"idRestaurante": 1,
	"dataVotacao": "2017-06-01",
	"funcionario": {
		"id": 1,
	    "nome": "Pedro",
	    "ativo": "ativo"
	},
	"restaurante": {
		"id": 1,
	    "nome": "San Ro",
	    "endereco": "Rua Professor Moraes"
	},
	...
]

### Inserção de Voto de Funcionário

http://localhost:8080/foodtrack/votofuncionarios [POST] (application/json)
@param votoFuncionario VotoFuncionario

Serviço de inserção de votos de funcionário. O serviço consome um json como entrada conténdo as informações do voto.

{
	idFuncionario : "",
	idRestaurante : "",
	dataVotacao : "", 
}

Onde idFuncionario e idRestaurante são do int e dataVotacao é uma String no formato de data yyyy-MM-dd. Caso a dataVotacao seja nula ou vazia, será selecionado o dia atual.
	  
{
	idFuncionario : 1,
	idRestaurante : 3
}

### Recupera restaurante escolhido do dia

http://localhost:8080/foodtrack/votacao/2017-06-01
@param data String conténdo a data desejada do resultado


Serviço responsável por retornar o restaurante escolhido através da votação. O serviço retorna JSON de acordo com a estrutura do Restaurante.

{
	"id": 3,
	"nome": "Autentica",
	"endereco": "Rua Alagoas"
}

Caso nenhum restaurante tenha sido escolhido(não ocorreu votação ou restaurante já escolhido durante a semana) o serviço irá retornar um JSON no seguinte formato:

{
	"timestamp": 1496489429858,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "java.lang.Exception",
	"message": "Nenhuma votação ocorreu para o dia 2017-06-03 ou todos os restaurantes votados já foram escolhidos durante a semana.",
	"path": "/foodtrack/votacao/2017-06-03"
}

## Desenvolvimento da Aplicação

Foi utilizado o Spring Boot para o desenvolvimento da aplicação. A aplicação foi baseada na arquitetura N-tier. Para isso foi criado a seguinte estrutura:

Controlador -> Service -> Dao -> Memória

Onde o controlador gerência os serviços implementados(REST) e utiliza do Spring Boot(Autowired) para acesso a classe de regra de negócio(Service). Na classse Service
estão contidas as regras estabelidas para a lógica da aplicação, que por fim fazem acesso ao nosso Dao.

Para manter os dados na memória foi utilizado uma própria estrutura de lista acessada pelos Daos criados. Eles foram marcados como repositório de dados pelo Spring Boot.
A Base de dados H2 iria ser utilizada para esse fim, então deixei a estrutura(FuncionarioH2Dao) na package dos Daos também marcada como repositório. Sendo assim se no futuro
for desejado mudar a fonte de dados para uma base de dados na memória como H2, bastaria apenas mudar o qualifier da fonte de dados.

Para retornar os erros do sistema foi optado por gerenciá-los utilizando Exceções. Dado o tempo para desenvolvimento foi optado por devolver como resposta a própria Exceções raiz Exception, mas como implementação futura seria recomendado criar exceções próprias para o gerenciamento de erros.

## Testes

Foram criadas quatro classes de testes para auxiliar no desenvolvimento das classes que contém a lógica da aplicação(Service).
Cada classe testa as funções principais de cada serviço individualmente e seus possíveis problemas. 

- FuncionarioServiceTest.java
- RestauranteServiceTest.java
- VotoFuncionarioServiceTest.java
- VotacaoServiceTest.java

Caso o desenvolvedor queira testar o sistema, deixei preparado em cada Dao da aplicação dados para testes. Os dados foram marcados com o comentário "Fake test data" e podem ser descomentados para testes manuais da aplicação e seus serviços.

## Desenvolvido Com

* [SpringBoot](https://projects.spring.io/spring-boot/) - Framework usado
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [JUNIT](http://junit.org/junit4/) - Testes Unitários

## Authors

* **Pedro Gregorio** - *Desenvolvimento* - [PedroGregorio](https://github.com/phsgregorio)

