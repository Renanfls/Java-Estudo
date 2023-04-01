package PontoFlutuante;
public class TestaPontoFlutuante {
    public static void main(String[] args) {
        double salario;
        salario = 1132.50;
        System.out.println(salario);

        double idade = 37;

        double divisao = 3.14 / 2;
        System.out.println(divisao);

        int outraDivisao = 5 / 2; // Int com Int da Int
        System.out.println(outraDivisao);

        double novaTentativa = 5.0 / 2; // Double com Int prevalece o Double, por ser maior em questão de casas numericas
        System.out.println(novaTentativa);
    }
}
