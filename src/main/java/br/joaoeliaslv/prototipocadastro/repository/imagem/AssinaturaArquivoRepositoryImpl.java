package br.joaoeliaslv.prototipocadastro.repository.imagem;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
@Repository
public class AssinaturaArquivoRepositoryImpl implements ArquivoRepository
{
    @Value("${app.assLocation}")
    private String assLocation;
    @Value("${app.maxImgSize}")
    private long imgMaxSize;

    public AssinaturaArquivoRepositoryImpl()
    {
    }

    @Override
    public Arquivo ler(int id) throws IOException
    {
        Arquivo arquivo = new Arquivo();
        arquivo.setId(id);

        ler(assLocation + "/" + arquivo.getId(), arquivo);
        return arquivo;
    }

    @Override
    public void escrever(Arquivo arquivo) throws IOException
    {
        if (arquivo == null || arquivo.getBytes() == null)
        {
            return;
        }

        escrever(assLocation + "/" + arquivo.getId(), arquivo);
    }

    private void escrever(String caminho, Arquivo arquivo) throws IOException
    {
        File file = new File(caminho);
        File pasta = new File(file.getParent());
        if (!pasta.exists())
        {
            pasta.mkdirs();
        }

        if (arquivo.getBytes() != null && !Arrays.toString(Arrays.copyOf(arquivo.getBytes(), 4)).equals("[37, 80, 68, 70]"))
        {
            byte[] copiaImg = scale(arquivo.getBytes());
            Files.write(Paths.get(file.toURI()), copiaImg);
            return;
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

    public byte[] scale(byte[] imgBytes) throws IOException
    {
        if (imgBytes == null || imgBytes.length < imgMaxSize)
        {
            return imgBytes;
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(imgBytes);
        BufferedImage img = ImageIO.read(inputStream);
        inputStream.close();
        if (img == null)
        {
            return new byte[0];
        }

        int targetWidth = img.getWidth() / 2;
        int targetHeight = img.getHeight() / 2;

        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;
        BufferedImage scratchImage = null;
        Graphics2D g2 = null;

        int w = img.getWidth();
        int h = img.getHeight();

        int prevW = w;
        int prevH = h;

        do {
            if (w > targetWidth) {
                w /= 2;
                w = (w < targetWidth) ? targetWidth : w;
            }

            if (h > targetHeight) {
                h /= 2;
                h = (h < targetHeight) ? targetHeight : h;
            }

            if (scratchImage == null) {
                scratchImage = new BufferedImage(w, h, type);
                g2 = scratchImage.createGraphics();
            }

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

            prevW = w;
            prevH = h;
            ret = scratchImage;
        } while (w != targetWidth || h != targetHeight);

        if (g2 != null) {
            g2.dispose();
        }

        if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
            scratchImage = new BufferedImage(targetWidth, targetHeight, type);
            g2 = scratchImage.createGraphics();
            g2.drawImage(ret, 0, 0, null);
            g2.dispose();
            ret = scratchImage;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(ret, "jpg", outputStream);
        byte[] retBytes = outputStream.toByteArray();
        outputStream.close();
        if (retBytes.length > imgMaxSize)
        {
            return scale(retBytes);
        }
        return retBytes;
    }
}
