package Escopo;
public class TestaEscopo {
    public static void main(String[] args) {
        int idade = 20;
        int quantidadePessoas = 3;
        Boolean acompanhado; // Torna a variavel global
        //System.out.println(acompanhado); // Da erro pq a variavel não foi inicializada ou não recebeu nada. No java não existe variavel temporaria 

        // boolean acompanhado = quantidadePessoas >= 2;
        if (quantidadePessoas >= 2) {
            // ainda nao existe a variavel acompanhado
            // Boolean acompanhado = true;
            // existe
            acompanhado = true;
        } else {
            // ainda nao existe a variavel acompanhado
            // Boolean acompanhado = false;
            // existe
            acompanhado = false;
        }
        // ainda nao existe a variavel acompanhado

        if (idade >= 18 && acompanhado) {
            System.out.println("Bem vindo");
        } else {
            System.out.println("Não pode entrar");
        }
    }
}
