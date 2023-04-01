## **POO**
Paradigma da Orientação a Objetos -> Dado e funcionalidade sobre ele andam juntos
#
## **Programação procedural(Utilizada antigamente)**
**Características de procedural**
- Formulário com inúmeros campos distribuídos em várias abas, tudo sendo gerenciado por um único programador
- `Copy & Paste` e `Ctrl + F` como prática regular do desenvolvedor para propagar mudanças no projeto.

**Característica de não procedural**
- Várias equipes trabalhando em um único projeto

Para que várias equipes consigam trabalhar em um mesmo projeto, é necessário que as responsabilidades de cada código estejam bem definidas e claras, evitando conflitos na hora de realizar mudanças e evoluções. Código com responsabilidades coesas é sinal do paradigma OO.
#
## **Class(Objeto)**
As características de uma classe é chamada de `Atributo`

**Definindo tipos**
- Uma classe é uma especificação de um tipo, definindo atributos e comportamentos.
- Um objeto é uma instância de uma classe onde podemos definir valores para seus atributos.
- Para criar uma instância precisamos usar a palavra chave `new`
- Não é obrigatorio preencher todos os valores dos atributos;
#
## **Definindo valor de atributos**
```java
public class Pessoa {
    String nome;
    int idade;
    int peso;
}
```
**Definindo valor:**
```java
Pessoa heroi = new Pessoa();
heroi.nome = "Jonny";
```
#
```java
Conta conta = new Conta();
```

**Por padrão quando nomeTipoVariavel(Conta) = nomeVariavel(conta) => colocamos o nome da variavel iniciando com minusculo**

- `Conta` -> tipo da variavel, que faz referencia a instancia de um OBJ
- `conta` -> nome da variavel
- `new Conta()` -> Instancia um novo OBJ
#
## **Referências de objetos**
```java
public class Conta {
    double saldo;
}
```
```java
public class Teste {
    public static void main(String [] args) {
        Conta minhaConta = new Conta();
        minhaConta.saldo = 500.0;
        Conta outraConta = minhaConta;
        outraConta.saldo += 1000.0;
        System.out.println(minhaConta.saldo);
    }
}
```
**O valor imprimido é = `1500.0`**
#
## **Métodos**
- Define o comportamento ou a maneira de fazer algo
- É possível que um método não tenha nenhum parâmetro
- Por convenção, o nome do método no mundo Java deve começar com letra minúscula.
- Precisa definir uma saída

### **Chamando um método**
`nomeDaReferencia.nomeDoMetodo();`
```java
Conta contaDoPaulo = new Conta();
contaDoPaulo.deposita(30.0);
```
**Declarações validas**
-   ```java
    void deposita(double valor, int numero){
        
    }contaRenan
    ```
    Recebe parâmetro mas não executa nada

-   ```java
    void deposita(){

    }
    ```
    Não recebe parâmetro

**Declarações que não são validas**

-   ```java
    void deposita(double valor, int numero)
    ``` 
    Não abriu `{}`

-   ```java
    deposita(double valor){
        
    }
    ```
    Não declarou o tipo de retorno do método
#
## **`Void`**
```java
public void deposita(double valor) { }
```
`void` -> não retorna nada depois da execução do método, ou seja, se o método foi executado ou não. Diferente do `boolean` que retorna `true` ou `false`
#
## **`this`**
- É uma referência a class
- O uso não é obrigatório
- É uma palavra chave
#
## **Métodos com retorno**
```java
public boolean saca(double valor) { // Retorna se a ação do método deu certo ou não
    if (this.saldo >= valor) {
        this.saldo -= valor;
        return true;
    } else {
        return false;
    }
}
```
#
## **Composição de Objetos**
```java
public class Conta {
    double saldo;
    int agencia;
    int numero;
    Cliente titular;
}
```
Indica que `titular` é do tipo `Cliente` onde contem os atributos(nome, cpf, profissao)
```java
public class Cliente {
    String nome;
    String cpf;
    String profissao;
}
```
#
## **Referencia associativa**
```java
    Cliente cliente1 = new Cliente();
    cliente1.nome = "Renan Fabricio";
    cliente1.cpf = "111.111.111-11";
    cliente1.profissao = "dev";

    Conta contaCliente1 = new Conta();
    contaCliente1.saldo = 100;

    contaCliente1.titular = cliente1; // Referencia associativa

    System.out.println(contaCliente1.titular.nome); // Retorna: Renan Fabricio 
```
# 
## **Referência Null**
**Dessa forma da erro**

`.nome` é um atributo do OBJ `Cliente`, ao qual não foi chamado em nenhum momento
```java
public class TestaContaSemCliente {
    public static void main(String[] args) {
        Conta contaCliente2 = new Conta();
        System.out.println(contaCliente2.saldo);
    
        System.out.println(contaCliente2.titular);
    
        contaCliente2.titular.nome = "Marcela";
        System.out.println(contaCliente2.titular.nome);
    }
}
```
Printa o erro: 
```java
Exception in thread "main" java.lang.NullPointerException
	at TestaContaSemCliente.main(TestaContaSemCliente.java:8)
```
#
**Forma correta**
```java
    Conta contaCliente2 = new Conta();
    System.out.println(contaCliente2.saldo);

    contaCliente2.titular = new Cliente(); // Referencia associativa
    contaCliente2.titular.nome = "Marcela";
    System.out.println(contaCliente2.titular.nome);
```
#
## **Formas de referênciar**
-   ```java
    public class Pessoa {
        String nome;
        String cpf;
        int idade;
        Endereco endereco = new Endereco(); // 1° Forma
    }
    ```

-   ```java
    public class Programa {
        public static void main(String args[]) {
            Pessoa p = new Pessoa();
            p.nome = "Paulo";
            p.endereco = new Endereco(); // 2° Forma
            p.endereco.logradouro  = "Aveninda XYZ";
        }
    }
    ```
#
## **Encapsulamento**
- Esconde determinado dado e não possibilitando a alterção direta desse determinado dado
- A implementação interna pode ser modificada sem afetar nenhum código fora da própria classe
- Como boa prática todos os atributos criados são definidos como privados(private)

A palavra chave `private` que é a responsavel por esse encapsulamento

**Visualizando**
Usamos o método `Getter`
```java
    public double getSaldo() {
        return this.saldo;
    }
```

**Modificando**
Usamos o método `Setter`
```java
    public void setNumero(int numero) {
        this.numero = numero;
    }
```
- Geralmente métodos que alteram não retorna nada por isso declaramos o `void`
- E o nome da variavel temporaria é o mesmo nome do atributo

**Atalhos**
Digitando `set` junto e sem dar espaço sendo a inicial do atributo em maiusculo `setNu` ou `getNu` e apertar enter já é criada a estrutura padrão de ambos
**`setNu`**
```java
    public void setNumero(int numero) {
        this.numero = numero;
    }
```
**`getNu`**
```java
    public int getNumero() {
        return numero;
    }
```
#
## **Construtor**
```java
public class Conta {
    // Atributos
    private int agencia;
    private int numero;

    // Construtor
    public Conta(int agencia, int numero) {
        if (agencia <= 0 || numero <= 0) {
            System.out.println("Não pode valor <= a 0");
            return;
        }
        this.agencia = agencia;
        this.numero = numero;
        System.out.println("Agencia da conta criada: " + this.agencia);
        System.out.println("Numero da conta criada: " + this.numero);
    }
}
```
- É uma rotina de inicialização
- Só é executada uma vez, por esse motivo não possui retorno
#
## **Static**
Indica que pertence a `class`, ou seja se quiser exiber o valor de algum atributo que está declarado fora do construtor não é possivel

```java
public class Conta {
    // Atributo
    private static int total;

    // Construtor
    public Conta(int agencia, int numero) { 
        // O `Conta` no inicio é opcional
        Conta.total++; 
        System.out.println("Total de contas: " + Conta.total);
    }

    // Método Getter
    public static int getTotal() {
        return Conta.total;
    }
}
```
Definindo o atributo `total` como `static`, evita que toda vez que for criado um novo objeto do `tipo Conta` não seria criado um `novo total`, mantendo o valor correto.
#
## **Reaproveitamento entre construtores**
```java
public class Carro{
    private int ano;
    private String modelo;
    private double preco;

    public Carro(int ano, String modelo, double preco){
        if(ano >= 1891){
            this.ano = ano;
        }else{
            System.out.println("O ano informado está inválido. Por isso usaremos 2017!");
            this.ano = 2017;
        }

        if( modelo != null){
            this.modelo = modelo;
        }else{
            System.out.println("O modelo não foi informado. Por isso usaremos Gol!");
            this.modelo = "Gol";
        }

        if(preco > 0){
            this.preco = preco;
        }else{
            System.out.println("O preço não é válido. Por isso usaremos 40000.0!");
            this.preco = 40000.0;
        }        
    }

    //Novo construtor AQUI!
    public Carro(String modelo, double preco){
        this(2017, modelo, preco);
    }

    //getters e setters omitidos        

}
```
- Foi definido 1891 como padrão, pois o primeiro automóvel que chegou ao Brasil, um Peugeot trazido por Santos Dumont em 1891.
- Não é permitido que o modelo fique vazio
- Não é permitido valor > 0

Nesse caso tem dois construtores, pq o sistema aceita a criação com a passagem somente do modelo e valor. Nessa situação deve-se encarar o ano como sendo 2017.