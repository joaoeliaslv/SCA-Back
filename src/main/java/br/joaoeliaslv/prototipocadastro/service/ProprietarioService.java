package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Imovel;
import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import br.joaoeliaslv.prototipocadastro.repository.ProprietarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ProprietarioService
{
    private ProprietarioRepository proprietarioRepository;
    private EnderecoService enderecoService;

    public ProprietarioService(ProprietarioRepository proprietarioRepository,
                               EnderecoService enderecoService)
    {
        this.proprietarioRepository = proprietarioRepository;
        this.enderecoService = enderecoService;
        log.error(proprietarioRepository.findAllImoveis(7) + "");
    }

    public void criar(Proprietario proprietario)
    {
        proprietarioRepository.save(proprietario);
    }

    public void atualizar(Proprietario proprietario, int id)
    {
        Proprietario buscado = proprietarioRepository.findById(id).orElseThrow();
        proprietario.setId(id);
        proprietario.getEndereco().setId(buscado.getEndereco().getId());
        proprietarioRepository.save(proprietario);
    }

    public void deletar(int id)
    {
        if (proprietarioRepository.existsById(id))
        {
            proprietarioRepository.deleteById(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public Proprietario ler(int id)
    {
        return proprietarioRepository.findById(id).orElseThrow();
    }

    public Slice<Proprietario> lerTodos(Pageable pageable)
    {
        return proprietarioRepository.findAll(pageable);
    }

    public Proprietario buscarPorRegistro(int registro)
    {
        return proprietarioRepository.findFirstByRegistro(registro);
    }

    public Proprietario buscarPorCPF(String cpf)
    {
        return proprietarioRepository.findFirstByCpf(cpf);
    }

    public Proprietario buscarPorRG(String rg)
    {
        return proprietarioRepository.findFirstByRg(rg);
    }

    public Slice<Proprietario> lerTodos(Integer page, Integer size, Sort sort,
                                  String nome, String cpf, String rg, Integer registro)
    {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        if (page != null && page >= 0 && size != null && sort == null)
        {
            pageable = PageRequest.of(page, size);
        }
        else if (page != null && page >= 0 && size != null)
        {
            pageable = PageRequest.of(page, size, sort);
        }

        if (StringUtils.hasText(nome))
        {
            return proprietarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        if (StringUtils.hasText(cpf))
        {
            return proprietarioRepository.findByCpfContaining(cpf, pageable);
        }
        if (StringUtils.hasText(rg))
        {
            return proprietarioRepository.findByRgContainingIgnoreCase(rg, pageable);
        }
        if (registro != null && registro != 0)
        {
            return proprietarioRepository.findByRegistro(registro, pageable);
        }
        return proprietarioRepository.findAll(pageable);
    }

    public List<Imovel> lerImoveis(int id)
    {
        if (proprietarioRepository.existsById(id))
        {
            return proprietarioRepository.findAllImoveis(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
}
