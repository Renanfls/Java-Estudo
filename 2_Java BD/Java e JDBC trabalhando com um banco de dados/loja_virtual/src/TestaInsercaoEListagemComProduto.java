import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDAO;
import modelo.Produto;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        Produto pc = new Produto("Pc", "Pc Gamer"); // Novo produto

        try(Connection con = new ConnectionFactory().startConnection()) { // Verifica se tem uma conexão disponiveL
            ProdutoDAO produtoDAO = new ProdutoDAO(con);
            produtoDAO.salvar(pc); // Chama o metodo salvar()
            List<Produto> listaProdutos = produtoDAO.listar(); // Chama o método listar()
            listaProdutos.stream().forEach(lp -> System.out.println(lp)); // Lista os Produtos no console
        }
    }
}
