import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
    
    public static void main(String[] args) throws SQLException {
        
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES ('Teclado', 'Teclado gamer')";

        PreparedStatement stm = con.prepareStatement(
            sql, 
            Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
            );
        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("Id criado: " + id);
        }
    }
}
