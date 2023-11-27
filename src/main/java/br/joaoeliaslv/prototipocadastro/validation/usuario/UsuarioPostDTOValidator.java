package br.joaoeliaslv.prototipocadastro.validation.usuario;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioPostDTO;
import br.joaoeliaslv.prototipocadastro.service.UsuarioService;
import br.joaoeliaslv.prototipocadastro.validation.usuario.ValidUsuarioPostDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioPostDTOValidator implements ConstraintValidator<ValidUsuarioPostDTO, UsuarioPostDTO>
{
    private UsuarioService usuarioService;

    public UsuarioPostDTOValidator(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean isValid(UsuarioPostDTO dto, ConstraintValidatorContext constraintValidatorContext)
    {
        Usuario usuario = usuarioService.buscarPorLogin(dto.getLogin());

        return usuario == null;
    }
}
