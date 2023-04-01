package Conversao;
public class TestaConversao {
    public static void main(String[] args) {
        double salario = 1500.50;

        float pontoFlutuante = 3.14f; // `f` -> Indica que é um literal do tipo float

        int valor = (int) salario; // `(int)` -> Casting
        System.out.println(valor);

        long numeroGrande = 542534554L;

        double valor1 = 0.2;
        double valor2 = 0.1;
        double total = valor1 + valor2;
    }
}
