package br.joaoeliaslv.prototipocadastro.validation.usuario;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioPutDTO;
import br.joaoeliaslv.prototipocadastro.service.UsuarioService;
import br.joaoeliaslv.prototipocadastro.validation.usuario.ValidUsuarioPutDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioPutDTOValidator implements ConstraintValidator<ValidUsuarioPutDTO, UsuarioPutDTO>
{
    private UsuarioService usuarioService;

    public UsuarioPutDTOValidator(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean isValid(UsuarioPutDTO dto, ConstraintValidatorContext constraintValidatorContext)
    {
        Usuario usuario = usuarioService.buscarPorLogin(dto.getLogin());

        return usuario == null || usuario.getId() == dto.getId();
    }
}
