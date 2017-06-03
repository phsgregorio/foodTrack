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

Serviço responsável pela inserção de um novo funcionário. O serviço consome um json de entrada conténdo as informações do funcionário.

@param Funcionario funcionario

{
	"id": 15,
	"nome": "Roberto Carlos",
	"ativo": "inativo"
}

### Lista de Restaurantes


### Inserção de Restaurantes

## Desenvolvimento da Aplicação

Foi utilizado o Spring Boot para o desenvolvimento da aplicação. A aplicação foi baseada na arquitetura N-tier. Para isso foi criado a seguinte estrutura:

Controlador -> Service -> Dao -> Memória

Onde o controlador gerência os serviços implementados(REST) e utiliza do Spring Boot(Autowired) para acesso a classe de regra de negócio(Service). Na classse Service
estão contidas as regras estabelidas para a lógica da aplicação, que por fim fazem acesso ao nosso Dao.

Para manter os dados na memória foi utilizado uma própria estrutura de lista acessada pelos Daos criados. Eles foram marcados como repositório de dados pelo Spring Boot.
A Base de dados H2 iria ser utilizada para esse fim, então deixei a estrutura(FuncionarioH2Dao) na package dos Daos também marcada como repositório. Sendo assim se no futuro
for desejado mudar a fonte de dados para uma base de dados na memória como H2, bastaria apenas mudar o qualifier da fonte de dados.

## Testes

Foram criadas quatro classes de testes para auxiliar no desenvolvimento das classes que contém a lógica da aplicação(Service).
Cada classe testa as funções principais de cada serviço individualmente e seus possíveis problemas. 

- FuncionarioServiceTest.java
- RestauranteServiceTest.java
- VotoFuncionarioServiceTest.java
- VotacaoServiceTest.java

## Desenvolvido Com

* [SpringBoot](https://projects.spring.io/spring-boot/) - Framework usado
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [JUNIT](http://junit.org/junit4/) - Testes Unitários

## Authors

* **Pedro Gregorio** - *Desenvolvimento* - [PedroGregorio](https://github.com/phsgregorio)

