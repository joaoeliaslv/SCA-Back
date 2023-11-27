package br.joaoeliaslv.prototipocadastro.entities.dto.endereco;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoGetDTO
{
    private int id;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
}
