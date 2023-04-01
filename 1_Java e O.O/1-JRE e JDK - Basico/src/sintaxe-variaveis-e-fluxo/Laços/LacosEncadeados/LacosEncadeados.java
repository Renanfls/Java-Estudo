package Laços.LacosEncadeados;

public class LacosEncadeados {
    public static void main(String[] args) {
        /*      
        // Versão 1
        for (int multi = 1; multi <= 10; multi++) {
            for (int cont = 0; cont <= 10; cont++) {
                System.out.print(multi * cont);
                System.out.print(" ");
            }
            System.out.println();
        } 
        */


        // Versão 2
        for (int linha = 0; linha <= 10; linha++) {
            for (int coluna = 0; coluna <= linha; coluna++) {
                /*              
                if (coluna > linha) {
                    break;
                } 
                */
                System.out.print("*");
            }
            System.out.println();
        } 
    }
}
