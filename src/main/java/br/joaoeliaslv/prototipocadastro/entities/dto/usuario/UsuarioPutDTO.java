package br.joaoeliaslv.prototipocadastro.entities.dto.usuario;

import br.joaoeliaslv.prototipocadastro.validation.usuario.ValidUsuarioPutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ValidUsuarioPutDTO
public class UsuarioPutDTO
{
    @Min(1)
    private int id;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}
