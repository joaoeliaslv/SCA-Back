package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import br.joaoeliaslv.prototipocadastro.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService
{
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder)
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void criar(Usuario usuario)
    {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    /**
     * Atualiza o usuário no banco de dados, se a senha for diferente da atual,
     * a senha é transformada em hash no processo, seta o ID do usuário passado
     * com o ID passado como argumento.
     * @param usuario
     * @param id
     */
    public void atualizar(Usuario usuario, int id)
    {
        Usuario buscado = usuarioRepository.findById(id).orElseThrow();

        if (!buscado.getSenha().equals(usuario.getSenha()))
        {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }

    public void deletar(int id)
    {
        if(usuarioRepository.existsById(id))
        {
            usuarioRepository.deleteById(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    /**
     * Busca um usuário pelo ID.
     * @param id
     * @return um usuário com o ID caso ele exista, se não, retorna null.
     */
    public Usuario ler(int id)
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return usuario;
    }

    /**
     * Busca um usuário pelo login.
     * @param login
     * @return um usuário com o login caso ele exista, se não, retorna null.
     */
    public Usuario buscarPorLogin(String login)
    {
        Usuario usuario = usuarioRepository.findFirstByLogin(login);
        return usuario;
    }

    public List<Usuario> lerTodos()
    {
        return usuarioRepository.findAll();
    }
}
