package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(initialValue = 1, name = "pessoagen", sequenceName = "pessoaidsequence", allocationSize = 1)
@SequenceGenerator(initialValue = 1, name = "pessoagenficha", sequenceName = "pessoa_ficha_seq", allocationSize = 1)
@Entity
public class Pessoa
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoagen")
    private int id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoagenficha")
    @Column(insertable = false)
    private Integer ficha;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco")
    private Endereco endereco;
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataNasc;
    private LocalDate dataEmissao;
    private String estadocivil;
    private String profissao;
    private String pai;
    private String mae;
}
