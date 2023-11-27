package br.joaoeliaslv.prototipocadastro.entities.dto.proprietario;

import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoPostDTO;
import br.joaoeliaslv.prototipocadastro.validation.proprietario.ValidProprietarioPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidProprietarioPostDTO
public class ProprietarioPostDTO
{
    private int id;
    @Min(1)
    private int registro;
    @NotNull
    private int livro;
    @NotBlank
    private String folha;
    @NotBlank
    private String nome;
    private String nacionalidade;
    private String estadoCivil;
    private String profissao;
    @NotBlank
    private String cpf;
    private String rg;
    @NotNull
    private EnderecoPostDTO endereco;
    private String informacaoAdicional;

}
