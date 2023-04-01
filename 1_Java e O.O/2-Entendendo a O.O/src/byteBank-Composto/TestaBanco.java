public class TestaBanco {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente();
        cliente1.nome = "Renan Fabricio";
        cliente1.cpf = "111.111.111-11";
        cliente1.profissao = "dev";

        Conta contaCliente1 = new Conta();
        contaCliente1.deposita(100);
        // Associa o cliente1 a contaCliente1
        contaCliente1.titular = cliente1; // `contaCliente1.titular` faz uma associação(atribui uma referencia) com `cliente1`

        System.out.println(contaCliente1.titular);
        System.out.println(contaCliente1.titular.nome);
    }
}
