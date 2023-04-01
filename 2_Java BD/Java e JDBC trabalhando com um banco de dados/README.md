## **Instalação no Linux**
### **Ver config**
`lsb_release -a`
#
### **Instalar MySQL Server**
`apt-get install mysql-server`
#
### **Inicia o mysql**
`systemctl start mysql`
#
### **Habilita o mysql**
`systemctl enable mysql`
#
### **Reinicia o mysql**
`systemctl restart mysql`
#
### **Vê o status do mysql**
`systemctl status mysql`
#
### **Acessar o MySQL sem senha**
**Passo a passo:**
- `sudo mysql -h localhost -u root -p` ou `sudo mysql -u root -p`
    - `mysql`      -- evocamos o servidor
    - `-h`         -- dizemos que o próximo dado é referente ao host
    - `localhost`  -- informamos o o host
    - `-u`         -- dizemos que o próximo dado é referente ao usuário
    - `root`       -- informamos o usuário
    - `-p`         -- dizemos que o próximo dado é referente a senha
- Senha do usuario
- Aperte `ENTER`
#
## **Interface JDBC - (java.sql)**
Java DataBase Connectivity

Camada de abstração que fica antes dos drivers de cada Banco de Dados, assim a aplicação só precisa se conectar apenas com o JDBC.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TextaConexao {

	public static void main(String[] args) throws SQLException {

	Connection connection = DriverManager
			.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "root");
	connection.close();
	}

}
```
- Digite `Connection` e de ctrl + espaço para importar a interface
- `connection` - nome
- Digite `DriverManager` e de ctrl + espaço para importar o Gerenciador de Drivers
- `getConnection(url, user, password)` 
	- `url` - passamos o tipo do BD utilizado, porta e nome da DataBase 

	    **Ex:** `"jdbc:mysql://127.0.0.1:3306/loja_virtual"`
	- `user` - passamos o nome do usuario para acesso a DataBase, por padrão é definido como `root`
	- `password` - passamos o senha utilizada para acesso a DataBase
- `throws SQLException` - gera um erro indicando o seu tipo 
- `.close();` - método que fecha a conexao
#
## **Adicionando Drive do BD no projeto**

### **No Eclipse:**

- Botão direito do mouse na pasta do projeto
- Build Path
- Add External Archives...
- Selecione o drive com base na versão do BD utilizado
#
## **`Statement` - (java.sql)**
As clausulas(SELECT, DELETE...) do SQL no java são consideradas como `statements`

```java
Statement stm = con.createStatement();
stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
ResultSet rst = stm.getResultSet();
while(rst.next()) { 
	Integer id = rst.getInt("ID");
	System.out.println(id);
	String nome = rst.getString("NOME");
	System.out.println(nome);
	String descricao = rst.getString("DESCRICAO");
	System.out.println(descricao);
}
```

- `Statement` - Interface do pacote java.sql
- `.createStatement()` - método usado para criação de um statement
- `.execute("")` - método que executa a cláusula desejada e devolve um valor boolean, retorna true quando o resultado for uma lista(SELECT) e retorna false quando não tiver retorno(DELETE, INSERT, UPDATE)
- `ResultSet` - Interface do pacote java.sql
- `.getResultSet()` - pega os resultados da lista
- `.next()` - método que retorna o proximo da lista, esse método é fornecido pelo `ResultSet`
- `getInt` - pega o Int desejado, possui duas formas de busca, uma é utilizando o Index(`getInt(int columnIndex)` lembrando que o Index da lista gerada no MySQL se inicia pelo 1) e a outra é buscando pelo nome da coluna(`getInt(int columnLabel)`)
- `getString` - funciona da mesma forma que o `getInt`, só que pega retornos sendo do tipo String(nome, descricao)
#
## **Class `ConnectionFactory`**
Local onde se cria a conexão, sendo assim toda vez que for preciso abrir uma conexao é só instanciar novo OBJ(`new ConnectionFactory()`). Atribuindo a estrutura da conexao em um só lugar, ajuda na manutenção, ou seja, caso um dia for necessario fazer a troca de BD

**Arquivo ConnectionFactory.java**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection startConnection() throws SQLException {
        return DriverManager
            .getConnection(
                "jdbc:mysql://127.0.0.1:3306/loja_virtual", 
                "root", 
                "root"
            );

    }
}
```
#
## **Inserindo com `Statement`**
```java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
    
    public static void main(String[] args) throws SQLException {
        
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        Statement stm = con.createStatement();
        stm.execute(
            "INSERT INTO PRODUTO (nome, descricao) VALUES ('Teclado', 'Teclado gamer')",
            Statement.RETURN_GENERATED_KEYS // Retorna o id gerado sempre que a clausula for executada
        );

        ResultSet rst = stm.getGeneratedKeys(); // Pega o id gerado
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("Id criado: " + id);
        }
    }
}
```
Como o método `.execute()` nesse caso retorna `false`, por conter `INSERT` na clausula, que por padrão não retorna nada, dessa forma podemos passar mais um parametro que retorna um feedback, que é o `Statement`
#
## **Removendo com `Statement`**
```java
public class TestaRemocao {
    
    public static void main(String[] args) throws SQLException {
        
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        Statement stm = con.createStatement();
        stm.execute("DELETE FROM PRODUTO WHERE ID > 2"); // Remove os ids maior que 2

        Integer linhasModificadas = stm.getUpdateCount(); // Retorna a quantidade de linhas que foram modificadas depois da execução

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
    }
}
```
#
## **Inserção com parametros**
```java
public class TestaInsercaoComParametro {
    
    public static void main(String[] args) throws SQLException {
        String nome = "Mouse'";
        String descricao = "Mouse sem fio";
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES ('" + nome + "', '" + descricao + "')";
        System.out.println(sql);

        Statement stm = con.createStatement();
        stm.execute(
            sql,
            Statement.RETURN_GENERATED_KEYS
        );

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("Id criado: " + id);
        }
    }
}
```
Definindo o codigo dessa forma, ficamos com problemas de segurança, caso o usuario erre e coloque `''` por engano no nome, isso afetará o codigo dando erro de syntax:
```java
String nome = "Mouse'";
```
**Resultado da query gerada:** `INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse'', 'Mouse sem fio')`

Ou até mesmo o usuario que tiver noção de sql, ele podera definir um comando que deleta por exemplo a tabela toda, essa ação é chamada de `SQL Injection`:
```java
String descricao = "Mouse sem fio); delete from Produto;";
```
#
## **`prepareStatement()`**
O JDBC valida a entrada do usuario:

Evita as `SQL Injections`

Se o usuario colocar `''` o JDBC identifica com sendo uma String e assim não afetando o codigo em si
```java
    public static void main(String[] args) throws SQLException {
        String nome = "Mouse'"; 
        String descricao = "Mouse sem fio";
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
        System.out.println(sql);

        PreparedStatement stm = con.prepareStatement( 
            sql, 
            Statement.RETURN_GENERATED_KEYS
        );  
        
        stm.setString(1, nome); // Passamos a posição do parametro desejado e o nome da variavel -> Resultado: `INSERT INTO PRODUTO (nome, descricao) VALUES (nome, ?)`
        stm.setString(2, descricao);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("Id criado: " + id);
        }
    }
```
#
## **Extraindo um metodo**
### **No VS Code** 
- selecionamos o codigo desejado 
- Clicamos na lampada amarela que aparece no inicio da seleção feita
- depois Clicamos em Extrair metodo

### **No Eclipse:**
- Selecionamos o codigo desejado
- E damos `CTRL + 3` 
#
## **Desativando o autoCommit do JDBC**
```java
    ConnectionFactory factory = new ConnectionFactory();
    Connection con = factory.startConnection();
    con.setAutoCommit(false); // Dessa forma nois que desidimos quando subir o commit
```
#
## **Criando Erro `RuntimeException("")`**
```java
    if(nome.equals("celular")) { //`.equals()` - Identifica se é igual
        throw new RuntimeException("Não foi possivel add o produto");
    }
```
#
## **Lidando com commit e rollback**
```java
public static void main(String[] args) throws SQLException {
    ConnectionFactory factory = new ConnectionFactory();
    Connection con = factory.startConnection();

    con.setAutoCommit(false);

    String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
    System.out.println(sql);

    try {
        PreparedStatement stm = con.prepareStatement( 
            sql, 
            Statement.RETURN_GENERATED_KEYS 
        );  
        
        adicionarProduto("TV", "45 polegadas", stm);
        adicionarProduto("celular", "LG", stm);

        con.commit(); // Só quando todos os statements estiverem preparados e não tiver dado problema que o commit é feito

        stm.close();
        con.close();
    } catch (Exception e) { // Captura a Exception
        e.printStackTrace(); // Fala qual foi motivo da Exception gerada
        System.out.println("Roolback Executado!");
        con.rollback(); // Encerra o processo e descarta (desfaz) todas as alterações (updates,deletes,inserts) realizadas durante o processo.
    }
}

private static void adicionarProduto(String nome, String descricao, PreparedStatement stm) throws SQLException {
    stm.setString(1, nome); 
    stm.setString(2, descricao);
    
    if(nome.equals("celular")) {
        throw new RuntimeException("Não foi possivel add o produto");
    }

    stm.execute();

    ResultSet rst = stm.getGeneratedKeys();
    while(rst.next()) {
        Integer id = rst.getInt(1);
        System.out.println("Id criado: " + id);
    }
}
```
#
## **try-with-resources(`try()`)**
Usado para evitar o esquecimento do fechamento de conexões no código, evitando problemas de performance no futuro
```java
public class TestaInsercaoComParametro {
    
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
		try (Connection con = factory.startConnection()){

            con.setAutoCommit(false);

            String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
            System.out.println(sql);

            try (PreparedStatement stm = con.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS
                )
            ){
                adicionarProduto("TV", "45 polegadas", stm);
                adicionarProduto("celular", "LG", stm);

                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Roolback Executado!");
                con.rollback();
            }
        }
    }

    private static void adicionarProduto(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);
        
        if(nome.equals("celular")) { 
            throw new RuntimeException("Não foi possivel add o produto");
        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while(rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("Id criado: " + id);
            }
        }
    }
}
```
Dessa forma não precisamos colocar `stm.close()` ao final do `try` para fazer o fechamento do `Statement`, pois o **try-with-resources(`try()`)** estende a `interface AutoCloseable` que faz fechamento. O mesmo fazemos no fechamento da `Connection` e `ResultSet` 

**Caminho até a interface `AutoCloseable`:**
- Segure o `CTRL` e clique em `PreparedStatement` no seu código
- Depois segure o `CTRL` e clique em `Statement`
- Em seguida faça o msm em `AutoCloseable`
#
## **POOL de Conexões**
É adicionado atraves de um drive, da mesma forma que é feita com o conexão com BD que é necessario por o drive especifico do BD utilizado

Com ele podemos determinar uma quantidade minima e maxima de conexões abertas

**Nome de um dos drives que existem:** `C3P0(.JAR)`
#
## **Interface Datasource**
Onde vamos expor todas as configurações do `POOL de conexões`

Dessa forma não precisamos mais solicitar a abertura de uma conexão para a `ConnectionFactory` e sim perguntar para o `Datasource` se existe conexões abertas(livres)

### **Hoje todas as aplicações que trabalham com BD fazem uso do `POOL de conexões` com interface `Datasource`**
#
## **Implementação da interface `Datasource`**
**Adicionar os drivers abaixo:**
- `c3p0-0.9.5.4.jar` - Drive do pool de conexões
- `mchange-commons-java-0.2.16.jar` - detalha os processos feitos quando executamos o codigo

**Conexão antiga:**
```java
public class ConnectionFactory {

    public Connection startConnection() throws SQLException {
        return DriverManager
            .getConnection(
                "jdbc:mysql://127.0.0.1:3306/loja_virtual", // Url
                "root", // User
                "root"  // Password
            );
    }
}
```

**Nova Conexão:**
```java

public class ConnectionFactory {

    public DataSource dataSource; // A interface `DataSource` expoe as informações do pool de conexões

    public ConnectionFactory() { // Config do pool de conexões
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource(); // A class `ComboPooledDataSource` é responsavel pela configuração do pool de conexões
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/loja_virtual");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        comboPooledDataSource.setMaxPoolSize(15); // Define o numero max de conexões

        this.dataSource = comboPooledDataSource; // Passa as configurações para o `dataSource`
    }

    public Connection startConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
```
Dessa forma não é mais necessario esperar um processamento terminar para que o outro entre, conseguimos fazer os dois simuntaneamente, atraves da reciclagem de um conjunto de conexões abertas de tamanho fixo ou dinâmico. O processo fica esperando caso as 15 conexoes abertas estiverem em uso e assim que um dos processos em andamento terminar o que estava esperando inicia.
#
**Testando o Pool de Conexões:**

```java
public class TestaPoolConexoes {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            connectionFactory.startConnection();
            System.out.println("Conexão de número: " + i); // Só vai de 0 a 14 pq determinamos o max de conexoes como sendo 15 
        }
    }
}
```
#
**Class Produto:**
```java
public class Produto {
    
    private Integer id;
    private String nome;
    private String descricao;

    public Produto(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() { // Atalho vscode: Digite `to` e de enter
        return String.format( // Personaliza a exibição no terminal
            "O produto criado foi: %d, %s, %s", // `%d` é substituido pelo id, `%s` substituido pelo nome, `%s` substituido pelo descricao
            this.id, this.nome, this.descricao
        );
    }
}
```
**Testando Insercao com Produto:**
```java
public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        Produto pc = new Produto("Pc", "Pc Gamer"); // Novo produto

        try(Connection con = new ConnectionFactory().startConnection()) { // Verifica se tem uma conexão disponiveL
            String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
            try (PreparedStatement pstm = con.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
                )
            ){
                pstm.setString(1, pc.getNome()); // Add  o nome na 1 parametro do valor da clausula
                pstm.setString(2, pc.getDescricao()); // Add a descricao na 2 parametro do valor da clausula

                pstm.execute(); // Executo o PreparedStatement

                try (ResultSet rst = pstm.getGeneratedKeys()) { // Gera id do produto
                    while(rst.next()) {
                        pc.setId(rst.getInt(1)); // Seta o id do produto gerado
                    }
                }
            }
        }
        System.out.println(pc);
    }
}
```
**Resultado no terminal:**

`O produto criado foi: 10, Pc, Pc Gamer`
#
## **DAO - Data Access Object**
Trabalha com as informações no BD, ou seja, tudo que é referente ao acesso ao BD pra trabalhar com as informações do objeto vão ficar dentro desse arquivo. Toda parte de persistecia da aplicação fica toda centralizada na DAO e tem por objetivo conversar com BD.

O termo `DAO` é um padrão da linguagem, que tudo que é sobre acesso a OBJs, seja BD ou algo externo da aplicação, geralmente ele terá o sufixo DAO. Não é obrigatorio, mas tudo que é conveção ou padrão é recomendado fazer o uso.

```java
public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(
            sql, 
            Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
            )
        ){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while(rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) { // Recupera a proxima linha do BD
                    Produto produto = new Produto( // Transforma em um produto
                        rst.getInt(1), 
                        rst.getString(2), 
                        rst.getString(3)
                    );
                    produtos.add(produto); // Adiciona na lista
                }
            }
        }
        return produtos; // Retorna a lista
    }
}
```
#
**Class Produto**
```java
public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(
            sql, 
            Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
            )
        ){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while(rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) { // Recupera a proxima linha do BD
                    Produto produto = new Produto( // Transforma em um produto
                        rst.getInt(1), 
                        rst.getString(2), 
                        rst.getString(3)
                    );
                    produtos.add(produto); // Adiciona na lista
                }
            }
        }
        return produtos; // Retorna a lista
    }
}
```
#
**Insercao e Listagem com Produto**
```java
public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        Produto pc = new Produto("Pc", "Pc Gamer"); // Novo produto

        try(Connection con = new ConnectionFactory().startConnection()) { // Verifica se tem uma conexão disponiveL
            ProdutoDAO produtoDAO = new ProdutoDAO(con);
            produtoDAO.salvar(pc); // Chama o metodo salvar()
            List<Produto> listaProdutos = produtoDAO.listar(); // Chama o método listar()
            listaProdutos.stream().forEach(lp -> System.out.println(lp)); // Lista os Produtos no console
        }
    }
}
```
