package Caracteres;
public class TestaCaracteres {
    public static void main(String[] args) { // `String` com s maiusculo é um tipo referencia
        char letra = 'a'; // Se não for aspas simples não compila e só armazena um unico caracter, e não pode ser nulo
        System.out.println(letra);

        char valor = 66; // Converte para Letra que nesse caso seria o `B` lenvando em consideração na tabela ascii
        System.out.println(valor);

        // valor = valor + 1; // Int não cabe em um char, mas um char cabe em Int
        // System.out.println(valor);

        valor = (char) (valor + 1); // Fazendo o casting convertendo int para char funciona e retorna a letra `c` = 67
        System.out.println(valor);

        String palavra = "fdhdfh dfhgdhdgh fhdfh gfdg "; // Se não for aspas duplas não compila, não tem limite de caracteres e pode ser nulo
        System.out.println(palavra);

        palavra = palavra + 2020; // Converte o `2020` para string e concatena
        System.out.println(palavra);
    }
}
