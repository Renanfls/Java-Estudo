import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
    
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
		try (Connection con = factory.startConnection()){

            con.setAutoCommit(false); // Dessa forma nois que desidimos quando subir o commit

            String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
            System.out.println(sql);

            try (PreparedStatement stm = con.prepareStatement( // Em vez de criar um Statement vamos preparar um, dessa forma é o JDBC que valida a entrada do usuario
                sql, 
                Statement.RETURN_GENERATED_KEYS // Retorna a chave gerada(id)
                )
            ){
                adicionarProduto("TV", "45 polegadas", stm);
                adicionarProduto("celular", "LG", stm);

                con.commit(); // Só quando todos os statements estiverem preparados e não tiver dado problema que o commit é feito

                // stm.close(); - Não é mais necessario por conta do “try-with-resources” -> `try()` que estente a interface AutoCloseable que faz fechamento
            } catch (Exception e) { // Captura a Exception
                e.printStackTrace(); // '.printStackTrace()' fala qual foi motivo da Exception gerada
                System.out.println("Roolback Executado!");
                con.rollback(); // encerra o processo e descarta (desfaz) todas as alterações (updates,deletes,inserts) realizadas durante o processo.
            }
        }
    }

    private static void adicionarProduto(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome); // Seta a variavel 'nome' no primeiro parametro do valor da clausula
        stm.setString(2, descricao);
        
        if(nome.equals("celular")) { // Identifica se o nome é igual
            throw new RuntimeException("Não foi possivel add o produto"); // Gera um erro personalizado
        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while(rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("Id criado: " + id);
            }
        }
    }
}
