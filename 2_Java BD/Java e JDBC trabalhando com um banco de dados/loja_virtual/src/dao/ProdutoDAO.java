package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Produto;

public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(
            sql, 
            Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
            )
        ){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while(rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) { // Recupera a proxima linha do BD
                    Produto produto = new Produto( // Transforma em um produto
                        rst.getInt(1), 
                        rst.getString(2), 
                        rst.getString(3)
                    );
                    produtos.add(produto); // Adiciona na lista
                }
            }
        }
        return produtos; // Retorna a lista
    }
}
