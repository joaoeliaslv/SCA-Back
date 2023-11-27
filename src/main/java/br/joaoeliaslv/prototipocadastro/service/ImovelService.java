package br.joaoeliaslv.prototipocadastro.service;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.ImovelProprietarios;
import br.joaoeliaslv.prototipocadastro.repository.ImovelRepository;
import br.joaoeliaslv.prototipocadastro.repository.imagem.ArquivoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ImovelService
{
    private ImovelRepository imovelRepository;
    private EnderecoService enderecoService;
    private ArquivoRepository arquivoRepository;

    public ImovelService(ImovelRepository imovelRepository,
                         EnderecoService enderecoService,
                         @Qualifier("anexoRepository") ArquivoRepository arquivoRepository)
    {
        this.imovelRepository = imovelRepository;
        this.enderecoService = enderecoService;
        this.arquivoRepository = arquivoRepository;
    }

    public void criar(ImovelProprietarios imovelProprietarios, Arquivo anexo) throws IOException
    {
        imovelRepository.save(imovelProprietarios);
        if (anexo != null)
        {
            anexo.setId(imovelProprietarios.getId());
            arquivoRepository.escrever(anexo);
        }
    }

    public void atualizar(ImovelProprietarios imovelProprietarios, int id, Arquivo anexo) throws IOException
    {
        ImovelProprietarios buscado = imovelRepository.findById(id).orElseThrow();
        imovelProprietarios.setId(id);
        imovelProprietarios.getEndereco().setId(buscado.getEndereco().getId());
        imovelRepository.save(imovelProprietarios);

        if (anexo != null)
        {
            anexo.setId(imovelProprietarios.getId());
            arquivoRepository.escrever(anexo);
        }
    }

    public void deletar(int id)
    {
        if (imovelRepository.existsById(id))
        {
            imovelRepository.deleteById(id);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }

    public ImovelProprietarios ler(int id)
    {
        ImovelProprietarios imovelProprietarios = imovelRepository.findById(id).orElseThrow();
        return imovelProprietarios;
    }

    public Arquivo lerAnexoPessoa(int id) throws IOException
    {
        return arquivoRepository.ler(id);
    }

    public Slice<ImovelProprietarios> lerTodos(Pageable pageable)
    {
        return imovelRepository.findAll(pageable);
    }

    public ImovelProprietarios buscarPorProtocolo(int protocolo)
    {
        return imovelRepository.findFirstByProtocolo(protocolo);
    }

    public ImovelProprietarios buscarPorMatricula(int matricula)
    {
        return imovelRepository.findFirstByNumeroMatricula(matricula);
    }

    public Slice<ImovelProprietarios> lerTodos(Pageable pageable,
                                               Integer matricula, Integer protocolo, String tipoImovel, String rua)
    {
        if (matricula != null && matricula != 0)
        {
            return imovelRepository.findByNumeroMatricula(matricula, pageable);
        }
        if (protocolo != null && protocolo != 0)
        {
            return imovelRepository.findByProtocolo(protocolo, pageable);
        }
        if (StringUtils.hasText(tipoImovel))
        {
            return imovelRepository.findByTipoImovelContainingIgnoreCase(tipoImovel, pageable);
        }
        if (StringUtils.hasText(rua))
        {
            return imovelRepository.findByEndereco_RuaContainingIgnoreCase(rua, pageable);
        }

        return imovelRepository.findAll(pageable);
    }
}
