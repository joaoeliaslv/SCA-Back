package br.joaoeliaslv.prototipocadastro.entities.dto.pessoa;

import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaGetDTO
{
    private int id;
    private int ficha;
    private EnderecoGetDTO endereco;
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataNasc;
    private LocalDate dataEmissao;
    private String estadocivil;
    private String profissao;
    private String pai;
    private String mae;
    private byte[] assinatura;
}
