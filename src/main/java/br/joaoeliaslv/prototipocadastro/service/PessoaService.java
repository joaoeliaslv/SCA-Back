package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.Pessoa;
import br.joaoeliaslv.prototipocadastro.repository.PessoaRepository;
import br.joaoeliaslv.prototipocadastro.repository.imagem.ArquivoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class PessoaService
{
    private PessoaRepository pessoaRepository;
    private EnderecoService enderecoService;
    private ArquivoRepository arquivoRepository;

    public PessoaService(PessoaRepository pessoaRepository,
                         EnderecoService enderecoService,
                         @Qualifier("assRepository") ArquivoRepository arquivoRepository)
    {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
        this.arquivoRepository = arquivoRepository;
    }

    public void criar(Pessoa pessoa, Arquivo arquivoAss) throws IOException
    {
        pessoaRepository.save(pessoa);
        if (arquivoAss != null)
        {
		    arquivoAss.setId(pessoa.getId());
            arquivoRepository.escrever(arquivoAss);
        }
    }

    public void atualizar(Pessoa pessoa, int id, Arquivo arquivoAss) throws IOException
    {
        Pessoa buscado = pessoaRepository.findById(id).orElseThrow();
        pessoa.setId(id);
        pessoa.getEndereco().setId(buscado.getEndereco().getId());
        pessoaRepository.save(pessoa);
        if (!buscado.getFicha().equals(pessoa.getFicha()))
        {
            int proximaFicha = pessoaRepository.getNextFicha();
            if (pessoa.getFicha() >= proximaFicha)
            {
                pessoaRepository.atualizarNextFicha(pessoa.getFicha() + 1);
            }
        }
        if (arquivoAss != null)
        {
            arquivoRepository.escrever(arquivoAss);
        }
    }

    public void deletar(int id)
    {
        if(pessoaRepository.existsById(id))
        {
            pessoaRepository.deleteById(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public Pessoa ler(int id)
    {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        return pessoa;
    }

    public Pessoa buscarPorCPF(String cpf)
    {
        Pessoa pessoa = pessoaRepository.findFirstByCpf(cpf);
        return pessoa;
    }

    public Pessoa buscarPorRG(String rg)
    {
        Pessoa pessoa = pessoaRepository.findFirstByRg(rg);
        return pessoa;
    }

    public Pessoa buscarPorFicha(Integer ficha)
    {
        Pessoa pessoa = pessoaRepository.findByFicha(ficha);
        return pessoa;
    }

    public Arquivo lerAssPessoa(int id) throws IOException
    {
        return arquivoRepository.ler(id);
    }

    public Slice<Pessoa> lerTodos(Pageable pageable,
                                  String nome, String cpf, String rg, Integer ficha)
    {
        if (StringUtils.hasText(nome))
        {
            return pessoaRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        if (StringUtils.hasText(cpf))
        {
            return pessoaRepository.findByCpfContaining(cpf, pageable);
        }
        if (StringUtils.hasText(rg))
        {
            return pessoaRepository.findByRgContainingIgnoreCase(rg, pageable);
        }
        if (ficha != null && ficha != 0)
        {
            return pessoaRepository.findByFicha(ficha, pageable);
        }
        return pessoaRepository.findAll(pageable);
    }
}
