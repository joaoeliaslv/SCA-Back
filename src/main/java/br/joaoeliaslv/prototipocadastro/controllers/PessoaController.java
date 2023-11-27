package br.joaoeliaslv.prototipocadastro.controllers;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.Pessoa;
import br.joaoeliaslv.prototipocadastro.entities.dto.pessoa.PessoaGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.pessoa.PessoaPostDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.pessoa.PessoaPutDTO;
import br.joaoeliaslv.prototipocadastro.repository.imagem.ArquivoRepository;
import br.joaoeliaslv.prototipocadastro.repository.imagem.AssinaturaArquivoRepositoryImpl;
import br.joaoeliaslv.prototipocadastro.service.PessoaService;
import br.joaoeliaslv.prototipocadastro.util.objectconverter.ObjectConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("${controllers.pessoa.mapping}")
@Slf4j
public class PessoaController
{
    private final PessoaService pessoaService;
    private final ObjectConverter objectConverter;

    public PessoaController(PessoaService pessoaService,
                            ObjectConverter objectConverter)
    {
        this.pessoaService = pessoaService;
        this.objectConverter = objectConverter;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid PessoaPostDTO dto)
    {
        Pessoa pessoa = objectConverter.convert(dto, Pessoa.class);

        Arquivo arquivoAss = new Arquivo(pessoa.getId(), dto.getAssinatura());
        try
        {
            pessoaService.criar(pessoa, arquivoAss);
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid PessoaPutDTO dto, @PathVariable int id)
    {
        Pessoa pessoa = objectConverter.convert(dto, Pessoa.class);
        try
        {
            Arquivo arquivoAss = new Arquivo(id, dto.getAssinatura());
            try
            {
                pessoaService.atualizar(pessoa, id, arquivoAss);
            }
            catch (IOException e)
            {
                log.error(e.getMessage());
            }

            return ResponseEntity.status(200).build();
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
    {
        try
        {
            pessoaService.deletar(id);
            return ResponseEntity.status(200).build();
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaGetDTO> ler(@PathVariable int id)
    {
        try
        {
            Pessoa pessoa = pessoaService.ler(id);
            PessoaGetDTO dto = objectConverter.convert(pessoa, PessoaGetDTO.class);

            Arquivo arquivoAss = null;
            try
            {
                arquivoAss = pessoaService.lerAssPessoa(pessoa.getId());
            }
            catch (IOException e)
            {
            }

            if (arquivoAss != null)
            {
                dto.setAssinatura(arquivoAss.getBytes());
            }

            return ResponseEntity.status(200).body(dto);
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Slice<PessoaGetDTO>> lerTodos(Pageable pageable,
                                                        @RequestParam(required = false) String ficha,
                                                        @RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) String cpf,
                                                        @RequestParam(required = false) String rg)
    {
        Integer fichaInt = null;
        try
        {
            fichaInt = Integer.parseInt(ficha);
        } catch (Exception ignored) { }

        Slice<Pessoa> pessoas = pessoaService.lerTodos(pageable, nome, cpf, rg, fichaInt);
        Slice<PessoaGetDTO> dto = pessoas.map(pessoa ->
        {
            Arquivo arquivoAss = null;
            try
            {
                arquivoAss = pessoaService.lerAssPessoa(pessoa.getId());
            }
            catch (IOException e)
            {
            }

            PessoaGetDTO pessoaGetDTO = objectConverter.convert(pessoa, PessoaGetDTO.class);

            if (arquivoAss != null)
            {
                pessoaGetDTO.setAssinatura(arquivoAss.getBytes());
            }

            return pessoaGetDTO;
        });
        return ResponseEntity.status(200).body(dto);
    }
}
