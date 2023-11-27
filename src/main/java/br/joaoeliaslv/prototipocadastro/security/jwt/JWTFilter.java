package br.joaoeliaslv.prototipocadastro.security.jwt;

import br.joaoeliaslv.prototipocadastro.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter
{
    @Value("${controllers.login.mapping}")
    private String loginPath;
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.prefix}")
    private String prefix;
    @Autowired
    private JWTTokenGenerator generator;
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException
    {
        String path = httpServletRequest.getRequestURI();
        if(path.endsWith(loginPath))
        {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = getJWTToken(httpServletRequest);
        if(StringUtils.hasText(token) && generator.validarToken(token))
        {
            try
            {
                String username = generator.getUsuarioToken(token);
                UserDetails userDetails = authService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            catch (Exception e)
            {
                log.error("Não foi possível efetuar o login.", e);
                httpServletResponse.sendError(403, "Não foi possível efetuar o login: " + e.getMessage());
            }
        }
        else
        {
            httpServletResponse.sendError(403, "Token JWT inválido.");
        }
    }

    private String getJWTToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if(StringUtils.hasText(token) && token.startsWith(prefix + " "))
        {
            return token.replace(prefix + " ", "");
        }

        return null;
    }

}
