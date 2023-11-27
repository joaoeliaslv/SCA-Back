package br.joaoeliaslv.prototipocadastro.controllers;

import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioDetailsDTO;
import br.joaoeliaslv.prototipocadastro.security.jwt.JWTTokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class AuthController
{
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.prefix}")
    private String prefix;

    private AuthenticationManager manager;
    private JWTTokenGenerator tokenGenerator;

    public AuthController(AuthenticationManager manager, JWTTokenGenerator tokenGenerator)
    {
        this.manager = manager;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping(value = "${controllers.login.mapping}")
    public ResponseEntity<Void> login(@RequestBody UsuarioDetailsDTO dto)
    {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.gerarToken(authentication);
        return ResponseEntity.status(200).header(header,prefix + " " + token).build();
    }
}
