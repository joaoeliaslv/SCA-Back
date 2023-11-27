package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Endereco;
import br.joaoeliaslv.prototipocadastro.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnderecoService
{
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository)
    {
        this.enderecoRepository = enderecoRepository;
    }

    public void criar(Endereco endereco)
    {
        enderecoRepository.save(endereco);
    }

    public void atualizar(Endereco endereco, int id)
    {
        if(enderecoRepository.existsById(id))
        {
            endereco.setId(id);
            enderecoRepository.save(endereco);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public void deletar(int id)
    {
        if(enderecoRepository.existsById(id))
        {
            enderecoRepository.deleteById(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public Endereco ler(int id)
    {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow();
        return endereco;
    }

    public List<Endereco> lerTodos()
    {
        return enderecoRepository.findAll();
    }
}
