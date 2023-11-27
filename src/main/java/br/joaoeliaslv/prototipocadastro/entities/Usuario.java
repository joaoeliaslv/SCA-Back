package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(initialValue = 1, name = "usuariogen", sequenceName = "usuarioidsequence", allocationSize = 1)
@Entity
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuariogen")
    private int id;
    private String login;
    private String senha;
}
