import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    public DataSource dataSource; // A interface `DataSource` expoe as informações do pool de conexões

    public ConnectionFactory() { // Config do pool de conexões
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource(); // A class `ComboPooledDataSource` é responsavel pela configuração do pool de conexões
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/loja_virtual");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        comboPooledDataSource.setMaxPoolSize(15); // Define o numero max de conexões

        this.dataSource = comboPooledDataSource; // Passa as configurações para o `dataSource`
    }

    public Connection startConnection() throws SQLException {
        return this.dataSource.getConnection(); // Dessa forma não é mais necessario esperar um processamento terminar para que o outro entre, conseguimos fazer os dois simuntaneamente atraves do reaproveitamento de uma conexao que já está aberta

        /* conexão antiga, abre uma conexão direta com o BD
        return DriverManager
            .getConnection(
                "jdbc:mysql://127.0.0.1:3306/loja_virtual", 
                "root", 
                "root"
            );
        */
    }
}
