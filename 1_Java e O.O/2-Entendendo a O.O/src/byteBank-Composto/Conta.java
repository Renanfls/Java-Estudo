public class Conta {
    // Atributos
    private double saldo; // Não permite o acesso diretamente, ou seja, não pode se alterado e nem visualizado
    int agencia;
    int numero;
    Cliente titular;

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

    // Método Getter
    public double getSaldo() {
        return this.saldo;
    }
}