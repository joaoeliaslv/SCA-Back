package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// # = Não pode ter dois imóveis com o mesmo valor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "imovel")
@SequenceGenerator(initialValue = 1, name = "imovelgen", sequenceName = "imovelidsequence", allocationSize = 1)
public class ImovelProprietarios extends ImovelBase
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "imovel_proprietario",
            joinColumns = {@JoinColumn(name = "imovel_id")},
            inverseJoinColumns = {@JoinColumn(name = "proprietario_id")}
    )
    protected List<Proprietario> proprietarios;
}
