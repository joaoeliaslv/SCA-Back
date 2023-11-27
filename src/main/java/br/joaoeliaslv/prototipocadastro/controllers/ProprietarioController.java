package br.joaoeliaslv.prototipocadastro.controllers;


import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.proprietario.ProprietarioGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.proprietario.ProprietarioPostDTO;
import br.joaoeliaslv.prototipocadastro.service.ProprietarioService;
import br.joaoeliaslv.prototipocadastro.util.objectconverter.ObjectConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("${controllers.proprietario.mapping}")
@RestController
@Slf4j
public class ProprietarioController
{
    private ProprietarioService proprietarioService;
    private ObjectConverter objectConverter;

    public ProprietarioController(ProprietarioService proprietarioService, ObjectConverter objectConverter)
    {
        this.proprietarioService = proprietarioService;
        this.objectConverter = objectConverter;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ProprietarioPostDTO dto)
    {
        Proprietario proprietario = objectConverter.convert(dto, Proprietario.class);
        try
        {
            proprietarioService.criar(proprietario);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid ProprietarioPostDTO dto, @PathVariable int id)
    {
        Proprietario proprietario = objectConverter.convert(dto, Proprietario.class);

        try
        {
            try
            {
                proprietarioService.atualizar(proprietario, id);
            } catch (Exception e)
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
            proprietarioService.deletar(id);
            return ResponseEntity.status(200).build();
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioGetDTO> ler(@PathVariable int id)
    {
        try
        {
            Proprietario proprietario = proprietarioService.ler(id);
            ProprietarioGetDTO dto = objectConverter.convert(proprietario, ProprietarioGetDTO.class);

            return ResponseEntity.status(200).body(dto);
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/imoveis")
    public ResponseEntity<Slice<ImovelGetDTO>> lerImoveis(@PathVariable int id)
    {
        try
        {
            List<ImovelGetDTO> dtos = objectConverter.convert(proprietarioService.lerImoveis(id), ImovelGetDTO.class);
            Slice<ImovelGetDTO> slice = new SliceImpl<ImovelGetDTO>(dtos);

            return ResponseEntity.status(200).body(slice);
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Slice<ProprietarioGetDTO>> lerTodos(@RequestParam(required = false) Integer page,
                                                              @RequestParam(required = false) Integer size,
                                                              Sort sort,
                                                              @RequestParam(required = false) Integer registro,
                                                              @RequestParam(required = false) String nome,
                                                              @RequestParam(required = false) String cpf,
                                                              @RequestParam(required = false) String rg)
    {
        Slice<Proprietario> proprietarios = proprietarioService.lerTodos(page, size, sort, nome, cpf, rg, registro);
        Slice<ProprietarioGetDTO> dto = proprietarios.map(proprietario ->
        {
            ProprietarioGetDTO proprietarioGetDTO = objectConverter.convert(proprietario, ProprietarioGetDTO.class);

            return proprietarioGetDTO;
        });

        return ResponseEntity.status(200).body(dto);
    }
}
