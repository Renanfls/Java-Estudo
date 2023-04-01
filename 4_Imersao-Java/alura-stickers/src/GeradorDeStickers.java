import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeStickers {
    public void create(InputStream inputStream, String nomeArquivo) throws Exception { 
        
        // Leitura da imagem
        BufferedImage imgOriginal = ImageIO.read(inputStream); // Identifica a imagem pela url passada
        
        // Cria nova imagem em memória com transparencia e com tamanho novo
        int largura = imgOriginal.getWidth(); // Pega a largura da img
        int altura = imgOriginal.getHeight(); // Pega a altura da img
        int novaAltura = altura + 200; // Adiciona mais 200px
        BufferedImage novaImg = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); // Cria uma imagem vazia com fundo transparente

        // Copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImg.getGraphics(); // Busca a imagem
        graphics.drawImage(imgOriginal, 0, 0, null); // Coloca a imgOriginal sobrepondo a img vazia gerada anteriormente

        // Configura a fonte
        var fonte = new Font("Comic Sans MS", Font.PLAIN, 180);
        var cor = new Color(246, 210, 88);
        graphics.setColor(cor);
        graphics.setFont(fonte);
        
        // Escrever uma frase na nova imagem
        String frase = "⭐⭐⭐⭐⭐";
        FontMetrics fm = graphics.getFontMetrics();
        int len = fm.stringWidth(frase); // Pega a largura da String
        graphics.drawString(frase, (largura - len) / 2, novaAltura - 50);

        // Escrever a nova imagem em um arquivo
        ImageIO.write(novaImg, "png", new File("imgs/saida/" + nomeArquivo + ".png")); // Renderiza a novaImg
    }
}