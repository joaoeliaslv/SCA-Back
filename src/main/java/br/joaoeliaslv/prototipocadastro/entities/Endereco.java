package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(initialValue = 1, name = "enderecogen", sequenceName = "enderecoidsequence", allocationSize = 1)
@Entity
public class Endereco
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecogen")
    private int id;
    private String estado;
    private String cidade;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
}
