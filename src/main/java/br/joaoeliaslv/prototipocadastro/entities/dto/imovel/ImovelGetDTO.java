package br.joaoeliaslv.prototipocadastro.entities.dto.imovel;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.Endereco;
import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import br.joaoeliaslv.prototipocadastro.entities.dto.endereco.EnderecoGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.proprietario.ProprietarioGetDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImovelGetDTO
{
    private int id;
    private int numeroMatricula;
    private int livro;
    private String folha;
    private int protocolo;
    private LocalDate abertura;
    private String tipoImovel;
    private String localizacao;
    private String cns;
    private String mat;
    private String tipoLogradouro;
    private String tituloLogradouro;
    private Endereco endereco;
    private String cin;
    private String latitude;
    private String longitude;
    private String areaTotal;
    private String areaConstruida;
    private String unidade;
    private String iptu;
    private String descricao;
    private List<ProprietarioGetDTO> proprietarios;
    private byte[] anexo;
}
