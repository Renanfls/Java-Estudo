package Condicional;
public class TestaCondicional {
    public static void main(String[] args) {
        int idade = 20;
        int quantidadePessoas = 3;
        boolean acompanhado = quantidadePessoas >= 2;

        /* 
        if (idade >= 18) {
            System.out.println("Maior de 18, pode entrar!");
        } else {
            if (quantidadePessoas >= 2) {
                System.out.println("Não tem 18, mas pode entrar acompanhado!");
            } else {
                System.out.println("Menor de 18 e não estando acompanhado não pode entrar!");
            }
        }
        */

        /*
        if (idade >= 18 || quantidadePessoas >= 2) {
            System.out.println("Bem vindo");
        } else {
            System.out.println("Não pode entrar");
        }
        */

        if (idade >= 18 && acompanhado) {
            System.out.println("Bem vindo");
        } else {
            System.out.println("Não pode entrar");
        }
    }
}
