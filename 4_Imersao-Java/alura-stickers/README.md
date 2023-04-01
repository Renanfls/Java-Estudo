## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder 30fstructure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

#
# **01 - Consumindo uma API de filmes com Java**
- [250 melhores filmes de acordo com a classificação do IMDB](https://www.imdb.com/chart/top/)
- [Documentação da API do IMDB](https://imdb-api.com/api)
- Instalação do Visual Studio Code com o [Coding Pack para Java](https://code.visualstudio.com/docs/languages/java#_install-visual-studio-code-for-java)
- Classe pronta que utiliza Expressões Regulares para fazer [parse de um JSON](https://gist.github.com/alexandreaquiles/cf337d3bcb59dd790ed2b08a0a4db7a3)
- [Biblioteca Jackson, que faz parse de JSON](https://github.com/FasterXML/jackson)
- Site que ajuda a entender [Expressões Regulares](https://regex101.com/)
- O [endpoint da API do IMDB](https://imdb-api.com/en/API/Top250Movies/) que devolve os 250 melhores filmes + **SUA-API-KEY**

## **Desafios 01**

1. Consumir o endpoint de filmes mais populares da API do IMDB. Procure também, na documentação da API do IMDB, o endpoint que retorna as melhores séries e o que retorna as séries mais populares. [Confira a resolução do desafio aqui](https://youtu.be/v4Dpul7b5bU)

2. Usar sua criatividade para deixar a saída dos dados mais bonitinha: usar emojis com código UTF-8, mostrar a nota do filme como estrelinhas, decorar o terminal com cores, negrito e itálico usando códigos ANSI, e mais! [Confira a resolução do desafio aqui](https://youtu.be/kkom8S-mCP4)

3. Colocar a chave da API do IMDB em algum lugar fora do código como um arquivo de configuração (p. ex, um arquivo .properties) ou uma variável de ambiente. [Confira a resolução do desafio aqui](https://youtu.be/uc59B0J4z1c)

## **Material complementar**

- Artigo na Alura sobre como [pintar o terminal, com tabelinha de cores e negrito](https://www.alura.com.br/artigos/decorando-terminal-cores-emojis).

## **API do IMDb**
[Documentação da API do IMDb](https://imdb-api.com/api)
#
## **Instabilidade da API do IMDb**
Caso a API do IMDb esteja instável ou fora do ar, você pode utilizar os seguintes endereços alternativos:

- https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json
- https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json
- https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
- https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json
#
## **Atalhos**
- Selecionando e dando `CTRL + .` e indo em `Assign statement to_new local variable` - cria uma variavel
- `SHIFT + ALT + O` - Faz todos os imports pendentes dentro da aplicação
#
## **Abrindo conexão**
```java
HttpClient client = HttpClient.newHttpClient();
```
ou
```java
var client = HttpClient.newHttpClient();
```
A partir das versões mais novas do java é possivel definir apenas como `var` que ele identifica o tipo. Nesse caso é o tipo `HttpClient`.
## **Lib para parsear um JSON**
- [Jackson](https://www.devmedia.com.br/introducao-ao-jackson-objectmapper/43174)
#
## **Codigo feito**
**App.java**
```java
public class App {
    public static void main(String[] args) throws Exception {
        // Conexão HTTP e buscar os filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); // Abre a conexão
        var request = HttpRequest.newBuilder(endereco).GET().build(); // Busca os dados da URL
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // Resposta retornada da requisição
                                                    // request - Faz a requisição
                                                    // BodyHandlers(class que cria as maneiras de leitura)
        String body = response.body();

        // Extrai (titulo, poster, classificação)
        var parser = new JsonParser(); // Utiliza Regex para identificar dentro do codigo JSON os atributos de cada filme
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
            // 1° String - Tipo da chave
            // 2° String - Tipo do valor
        // System.out.println(listaDeFilmes.size()); // Pega o tamanho da lista

        // Exibi e manipula os dados
        for (Map<String,String> filme : listaDeFilmes) { // Template da sintaxe do foreach(Não existe no java)
            System.out.println(filme.get("title")); // Busca dentro do JSON apenas o OBJ "title"
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
```
**JsonParser.java**
```java
public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json) {
        
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;
    }
}
```
# **Aula 02 - Gerando figurinhas para WhatsApp**

**Leitura de imagem**
```java
BufferedImage imgOriginal = ImageIO.read(new File("imgs/filme.jpg"));
```
- `ImageIO` - Class que faz a leitura de imagens
- `.read` - metodo que identifica arquivos

Será exigido o tratamento de exceção com isso podemos tratar tanto com `try/cath` ou um `throws Exception`. `Exception` é a forma mais generica e o `IOException` é especifico para arquivos.

**Leitura usando `InputStream`**
```java
BufferedImage imgOriginal = ImageIO.read(inputStream);
```
- `inputStream` - Class abstrata que representa um stream de fontes de bytes
    - 1° forma - Identifica um monte de bytes de um arquivo
    - 2° forma - Identifica atraves de um socket, porta ou url

**InputStream passando arquivo local**
```java
InputStream inputStream = 
                new FileInputStream(new File("entrada/filme-maior.jpg")); // Identifica arquivo local
```

**InputStream passando URL**
```java
InputStream inputStream = 
                new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
                .openStream();
```
#
**Buscando a imagem**
```java
Graphics2D graphics = (Graphics2D) novaImg.getGraphics();
```
- `Graphics2D` - Busca a imagem
- `(Graphics2D) novaImg.getGraphics()` - Força a tipagem
#
**Sobrepondo imagens**
```java
graphics.drawImage(imgOriginal, 0, 0, null);
```
- `.drawImage(img, x, y, observer)`
    - 1° paramentro - Imagem que ficara por trás
    - 2° paramentro - posição x que será feito o desenho
    - 3° parametro - posição y que será feito o desenho
    - 4° parametro - observação