public class TestaContaSemCliente {
    public static void main(String[] args) {
        Conta contaCliente2 = new Conta();
        System.out.println(contaCliente2.getSaldo());
    
        contaCliente2.titular = new Cliente(); // Referencia associativa
        contaCliente2.titular.nome = "Marcela";
        System.out.println(contaCliente2.titular.nome);
    }
}
