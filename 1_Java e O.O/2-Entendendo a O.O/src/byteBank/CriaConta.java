package byteBank;
public class CriaConta {
    public static void main(String[] args) {
        Conta Conta1 = new Conta(); // `Conta` no inicio se refere ao tipo e `new Conta()` instancia um novo OBJ
        Conta1.saldo = 200; // Atribuição a um atributo
        System.out.println(Conta1.saldo); // Acesso de um atributo de um OBJ

        Conta1.saldo += 100;
        System.out.println(Conta1.saldo);

        Conta Conta2 = Conta1; // Referencia a um unico OBJ
        Conta2.saldo = 50;

        System.out.println("Conta1 tem " + Conta1.saldo);
        System.out.println("Conta2 tem " + Conta2.saldo);
    }
}
