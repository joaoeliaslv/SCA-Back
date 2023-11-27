package br.joaoeliaslv.prototipocadastro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Arquivo
{
    private int id;
    private byte[] bytes;
}
