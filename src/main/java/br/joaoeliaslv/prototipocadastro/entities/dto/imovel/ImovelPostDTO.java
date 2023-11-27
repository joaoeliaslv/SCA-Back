package br.joaoeliaslv.prototipocadastro.entities.dto.imovel;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.Endereco;
import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoPostDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.proprietario.ProprietarioPostDTO;
import br.joaoeliaslv.prototipocadastro.validation.imovel.ValidImovelPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidImovelPostDTO
public class ImovelPostDTO
{
    private int id;
    @Min(1)
    private int numeroMatricula;
    @Min(1)
    private int livro;
    @NotBlank
    private String folha;
    private int protocolo;
    private LocalDate abertura;
    private String tipoImovel;
    private String localizacao;
    private String cns;
    private String mat;
    private String tipoLogradouro;
    private String tituloLogradouro;
    @NotNull
    private EnderecoPostDTO endereco;
    private String cin;
    private String latitude;
    private String longitude;
    private String areaTotal;
    private String areaConstruida;
    private String unidade;
    private String iptu;
    private String descricao;
    @NotNull
    private List<ProprietarioPostDTO> proprietarios;
    private byte[] anexo;
}
