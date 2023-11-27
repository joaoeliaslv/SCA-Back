package br.joaoeliaslv.prototipocadastro.repository.imagem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoRepositoryConfig
{
    @Bean(name = "assRepository")
    public ArquivoRepository assRepository()
    {
        return new AssinaturaArquivoRepositoryImpl();
    }

    @Bean(name = "anexoRepository")
    public ArquivoRepository anexoRepository()
    {
        return new AnexoArquivoRepositoryImpl();
    }
}
