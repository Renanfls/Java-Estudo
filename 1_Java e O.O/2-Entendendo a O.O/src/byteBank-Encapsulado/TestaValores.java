public class TestaValores {
    public static void main(String[] args) {
        Conta conta = new Conta(1313,2424);

        Conta conta2 = new Conta(1313,2555);
        /*
        // Antes de utilizar o construtor a conta estava inconsistente
        conta.setAgencia(-50);
        conta.setNumero(-200);
        System.out.println(conta.getAgencia()); // Retornava 0
        */
    }
}
