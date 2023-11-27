package br.joaoeliaslv.prototipocadastro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(initialValue = 1, name = "proprietariogen", sequenceName = "proprietarioidsequence", allocationSize = 1)
public class Proprietario
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proprietariogen")
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco")
    private Endereco endereco;
    private String informacaoAdicional;
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "proprietarios")
//    private List<Imovel> imoveis;
}
