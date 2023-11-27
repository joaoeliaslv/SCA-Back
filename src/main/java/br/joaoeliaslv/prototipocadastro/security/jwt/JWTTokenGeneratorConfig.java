package br.joaoeliaslv.prototipocadastro.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTTokenGeneratorConfig
{
    @Bean
    public JWTTokenGenerator jwtTokenGenerator()
    {
        return new JWTTokenGeneratorImpl();
    }
}
