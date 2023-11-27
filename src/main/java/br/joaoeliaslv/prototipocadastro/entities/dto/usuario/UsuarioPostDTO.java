package br.joaoeliaslv.prototipocadastro.entities.dto.usuario;

import br.joaoeliaslv.prototipocadastro.validation.usuario.ValidUsuarioPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ValidUsuarioPostDTO
public class UsuarioPostDTO
{
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}
