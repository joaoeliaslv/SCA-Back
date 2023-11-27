package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

// # = Não pode ter dois imóveis com o mesmo valor
@Entity
@SequenceGenerator(initialValue = 1, name = "imovelgen", sequenceName = "imovelidsequence", allocationSize = 1)
public class Imovel extends ImovelBase
{
}
