public class TestaGetESet {
    public static void main(String[] args) {
        Conta conta = new Conta(1212,2424);
        // conta.numero = 1321; // Não compila
        // conta.setNumero(1321); // Já está sendo atribuido ao Construtor
        System.out.println(conta.getNumero());

        Cliente renan = new Cliente();
        // conta.titular = renan; // Não compila
        conta.setTitular(renan);
        // renan.nome = "renan fabricio" // Não compila
        renan.setNome("renan fabricio");
        renan.setProfissao("dev");

        System.out.println(conta.getTitular().getNome());
        System.out.println("Profissão Antiga: " + conta.getTitular().getProfissao());

        conta.getTitular().setProfissao("Desenvolvedor");
        System.out.println("Profissão Nova: " + conta.getTitular().getProfissao());
    }
}
