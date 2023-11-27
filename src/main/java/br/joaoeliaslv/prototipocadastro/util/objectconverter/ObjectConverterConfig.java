package br.joaoeliaslv.prototipocadastro.util.objectconverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectConverterConfig
{
    @Bean
    public ObjectConverter objectConverter()
    {
        return new ObjectConverterImpl();
    }
}
