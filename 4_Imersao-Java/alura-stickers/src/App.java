import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Conexão HTTP e buscar os filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); // Abre a conexão
        var request = HttpRequest.newBuilder(endereco).GET().build(); // Busca os dados da URL
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // Resposta retornada da requisição
        String body = response.body();

        // Extrai (titulo, poster, classificação)
        var parser = new JsonParser(); // Utiliza Regex para identificar dentro do codigo JSON os atributos de cada filme
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibi e manipula os dados
        GeradorDeStickers gerador = new GeradorDeStickers();
        for (Map<String,String> filme : listaDeFilmes) { // Template da sintaxe do foreach(Não existe no java)

            String urlImagem = filme.get("image"); // Busca dentro do JSON o OBJ "image" que contem a URL
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream(); // Captura a imgOriginal
            String nomeArquivo = titulo + ".png";

            gerador.create(inputStream, nomeArquivo); // Cria o Sticker

            System.out.println(titulo + " Foi gerado");
            System.out.println();
        }
    }
}