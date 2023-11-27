package br.joaoeliaslv.prototipocadastro.repository;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    public Usuario findFirstByLogin(String login);
}
