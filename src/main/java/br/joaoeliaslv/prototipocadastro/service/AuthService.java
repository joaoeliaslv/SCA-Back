package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioDetailsDTO;
import br.joaoeliaslv.prototipocadastro.repository.UsuarioRepository;
import br.joaoeliaslv.prototipocadastro.util.objectconverter.ObjectConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService
{
    private UsuarioRepository usuarioRepository;
    private ObjectConverter objectConverter;

    public AuthService(UsuarioRepository usuarioRepository, ObjectConverter objectConverter)
    {
        this.usuarioRepository = usuarioRepository;
        this.objectConverter = objectConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Usuario usuario = usuarioRepository.findFirstByLogin(s);
        return objectConverter.convert(usuario, UsuarioDetailsDTO.class);
    }
}
