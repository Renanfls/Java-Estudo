import java.sql.SQLException;

public class TestaPoolConexoes {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            connectionFactory.startConnection();
            System.out.println("Conexão de número: " + i); // Só vai de 0 a 14 pq determinamos o max de conexoes como sendo 15 
        }
    }
}
