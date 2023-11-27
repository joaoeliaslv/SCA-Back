package br.joaoeliaslv.prototipocadastro.entities.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoPostDTO
{
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;
    private String complemento;
}
