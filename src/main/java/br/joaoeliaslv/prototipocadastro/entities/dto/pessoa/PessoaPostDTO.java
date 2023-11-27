package br.joaoeliaslv.prototipocadastro.entities.dto.pessoa;

import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoPostDTO;
import br.joaoeliaslv.prototipocadastro.validation.pessoa.ValidPessoaPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ValidPessoaPostDTO
public class PessoaPostDTO
{
    private int ficha;
    @NotNull
    private EnderecoPostDTO endereco;
    @NotBlank
    private String nome;
    @CPF
    private String cpf;
    @NotBlank
    private String rg;
    @NotNull
    private LocalDate dataNasc;
    @NotNull
    private LocalDate dataEmissao;
    @NotBlank
    private String estadocivil;
    @NotBlank
    private String profissao;
    @NotBlank
    private String pai;
    @NotBlank
    private String mae;
    private byte[] assinatura;
}
