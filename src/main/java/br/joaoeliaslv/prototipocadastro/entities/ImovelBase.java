package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// # = Não pode ter dois imóveis com o mesmo valor
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@SequenceGenerator(initialValue = 1, name = "imovelgen", sequenceName = "imovelidsequence", allocationSize = 1)
public abstract class ImovelBase
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imovelgen")
    protected int id;
    // #
    protected int numeroMatricula;
    protected int livro;
    protected String folha;
    // #
    protected int protocolo;
    protected LocalDate abertura;
    protected String tipoImovel;
    protected String localizacao;
    protected String cns;
    protected String mat;
    protected String tipoLogradouro;
    protected String tituloLogradouro;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco")
    protected Endereco endereco;
    protected String cin;
    protected String latitude;
    protected String longitude;
    protected String areaTotal;
    protected String areaConstruida;
    protected String unidade;
    protected String iptu;
    protected String descricao;
}
