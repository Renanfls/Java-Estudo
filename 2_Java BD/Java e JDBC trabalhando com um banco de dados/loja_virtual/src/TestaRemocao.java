import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {
    
    public static void main(String[] args) throws SQLException {
        
        ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

        PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stm.setInt(1, 2);
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount(); // `.getUpdateCount()` retorna a quantidade de linhas que foram modificadas depois da execução

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
    }
}
