package br.joaoeliaslv.prototipocadastro.repository.imagem;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
@Repository
public class AnexoArquivoRepositoryImpl implements ArquivoRepository
{
    @Value("${app.anexoLocation}")
    private String anexoLocation;

    public AnexoArquivoRepositoryImpl()
    {
    }
    
    @Override
    public Arquivo ler(int id) throws IOException
    {
        Arquivo arquivo = new Arquivo();
        arquivo.setId(id);

        ler(anexoLocation + "/" + arquivo.getId(), arquivo);
        return arquivo;
    }

    @Override
    public void escrever(Arquivo arquivo) throws IOException
    {
        if (arquivo == null || arquivo.getBytes() == null)
        {
            return;
        }

        escrever(anexoLocation + "/" + arquivo.getId(), arquivo);
    }

    private void escrever(String caminho, Arquivo arquivo) throws IOException
    {
        File file = new File(caminho);
        File pasta = new File(file.getParent());
        if (!pasta.exists())
        {
            pasta.mkdirs();
        }

        Files.write(Paths.get(file.toURI()), arquivo.getBytes());
    }

    private void ler(String caminho, Arquivo arquivo) throws IOException
    {
        File file = new File(caminho);
        if (!file.exists())
        {
            throw new IOException("Esse arquivo não existe.");
        }

        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        arquivo.setBytes(bytes);
    }
}
