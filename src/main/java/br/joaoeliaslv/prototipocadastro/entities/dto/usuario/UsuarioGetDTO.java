package br.joaoeliaslv.prototipocadastro.entities.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioGetDTO
{
    private int id;
    private String login;
}
