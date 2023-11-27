package br.joaoeliaslv.prototipocadastro.security.jwt;

import org.springframework.security.core.Authentication;

public interface JWTTokenGenerator
{
    public String gerarToken(Authentication authentication);
    public boolean validarToken(String token);
    public String getUsuarioToken(String token);
}
