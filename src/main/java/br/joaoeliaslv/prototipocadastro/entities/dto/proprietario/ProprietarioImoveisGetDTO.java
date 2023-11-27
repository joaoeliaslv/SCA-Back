package br.joaoeliaslv.prototipocadastro.entities.dto.proprietario;

import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProprietarioImoveisGetDTO
{
    private int id;
    private int registro;
    private int livro;
    private String folha;
    private String nome;
    private String nacionalidade;
    private String estadoCivil;
    private String profissao;
    private String cpf;
    private String rg;
    private EnderecoGetDTO endereco;
    private String informacaoAdicional;
    private List<ImovelGetDTO> imoveis;
}
