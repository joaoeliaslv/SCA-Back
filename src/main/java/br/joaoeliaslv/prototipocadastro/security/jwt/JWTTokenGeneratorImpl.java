package br.joaoeliaslv.prototipocadastro.security.jwt;

import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioDetailsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Slf4j
public class JWTTokenGeneratorImpl implements JWTTokenGenerator
{
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    @Override
    public String gerarToken(Authentication authentication)
    {
        UsuarioDetailsDTO dto = (UsuarioDetailsDTO) authentication.getPrincipal();

        Date dataExp = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setSubject(dto.getUsername())
                .claim("id", dto.getId())
                .setIssuedAt(new Date())
                .setExpiration(dataExp)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public boolean validarToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public String getUsuarioToken(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
