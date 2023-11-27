package br.joaoeliaslv.prototipocadastro.repository.imagem;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface ArquivoRepository
{
    public Arquivo ler(int id) throws IOException;

    public void escrever(Arquivo arquivo) throws IOException;
}
