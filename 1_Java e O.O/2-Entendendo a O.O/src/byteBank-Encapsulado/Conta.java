public class Conta {
    // Atributos
    private double saldo; // Não permite o acesso diretamente, ou seja, não pode se alterado e nem visualizado
    private int agencia;
    private int numero;
    private Cliente titular;
    private static int total;

    public Conta(int agencia, int numero) { // É uma rotina de inicialização e só é executada uma vez, por esse motivo não possui retorno
        if (agencia <= 0 || numero <= 0) {
            System.out.println("Não pode valor <= a 0");
            return;
        }
        Conta.total++; // O `Conta` no inicio é opcional
        System.out.println("Total de contas: " + Conta.total);
        this.agencia = agencia;
        this.numero = numero;
        System.out.println("Agencia da conta criada: " + this.agencia);
        System.out.println("Numero da conta criada: " + this.numero);
    }

    // O nome do método deve começar com letra minúscula
    // Método deposita
    public void deposita(double valor) { // void -> Indica que não é retornado nada confirmando a ação do método depositar
        this.saldo += valor; // `this` referencia a conta ao qual foi invocado o método
    }

    // Método saca
    public boolean saca(double valor) { // Retorna se a ação do método deu certo ou não
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    // Método transfere
    public boolean transfere(double valor, Conta destino) {
        if (saca(valor)) {
            destino.deposita(valor);
            return true;
        }
        return false;
    }

    // Método saldo
    public double getSaldo() {
        return this.saldo;
    }

    // Métodos numero
    public void setNumero(int numero) { // Geralmente métodos que alteram não retorna nada
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    // Métodos agencia
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getAgencia() {
        return this.agencia;
    }

    // Métodos titular
    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public static int getTotal() {
        return Conta.total;
    }
}