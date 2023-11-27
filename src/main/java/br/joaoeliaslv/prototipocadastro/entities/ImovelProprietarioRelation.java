package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "imovel_proprietario")
public class ImovelProprietarioRelation
{
    @Id
    @Column(name = "imovel_id")
    private int imovelId;
    @Column(name = "proprietario_id")
    private int proprietarioId;
}
