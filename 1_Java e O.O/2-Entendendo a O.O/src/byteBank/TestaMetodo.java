package byteBank;
public class TestaMetodo {
    public static void main(String[] args) {
        Conta conta1 = new Conta();
        conta1.saldo = 100;
        conta1.deposita(50);
        System.out.println("Saldo conta1: " + conta1.saldo);

        boolean sacou = conta1.saca(50);
        System.out.println("Saldo conta1: " + conta1.saldo);
        System.out.println(sacou);

        Conta conta2 = new Conta();
        conta2.saldo = 1000;

        // boolean resultadoTransferencia = conta2.transfere(300, conta1);

        if (conta2.transfere(300, conta1)) {
            System.out.println("Transferencia feita com sucesso");
        } else {
            System.out.println("Não tem saldo suficiente para essa transferencia");
        }
        System.out.println("Saldo conta2: " + conta2.saldo);

        System.out.println("Saldo conta1: " + conta1.saldo);
    }
}
