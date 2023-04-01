import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.startConnection();

		System.out.println("Fechando conexao!!");
		
		con.close();
		
	}

}
