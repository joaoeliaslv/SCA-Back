package br.joaoeliaslv.prototipocadastro.controllers;

import br.joaoeliaslv.prototipocadastro.entities.Arquivo;
import br.joaoeliaslv.prototipocadastro.entities.ImovelProprietarios;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelPostDTO;
import br.joaoeliaslv.prototipocadastro.service.ImovelService;
import br.joaoeliaslv.prototipocadastro.util.objectconverter.ObjectConverter;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("${controllers.imovel.mapping}")
@Slf4j
public class ImovelController
{
    private ImovelService imovelService;
    private ObjectConverter objectConverter;

    public ImovelController(ImovelService imovelService,
                            ObjectConverter objectConverter)
    {
        this.imovelService = imovelService;
        this.objectConverter = objectConverter;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ImovelPostDTO dto)
    {
        ImovelProprietarios imovelProprietarios = objectConverter.convert(dto, ImovelProprietarios.class);

        Arquivo arquivoAnexo = new Arquivo(imovelProprietarios.getId(), dto.getAnexo());

        try
        {
            imovelService.criar(imovelProprietarios, arquivoAnexo);
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid ImovelPostDTO dto, @PathVariable int id)
    {
        ImovelProprietarios imovelProprietarios = objectConverter.convert(dto, ImovelProprietarios.class);

        try
        {
            Arquivo arquivoAnexo = new Arquivo(imovelProprietarios.getId(), dto.getAnexo());

            try
            {
                imovelService.atualizar(imovelProprietarios, id, arquivoAnexo);
            } catch (IOException e)
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
            imovelService.deletar(id);
            return ResponseEntity.status(200).build();
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelGetDTO> ler(@PathVariable int id)
    {
        try
        {
            ImovelProprietarios imovelProprietarios = imovelService.ler(id);
            ImovelGetDTO dto = objectConverter.convert(imovelProprietarios, ImovelGetDTO.class);

            Arquivo arquivoAnexo = null;
            try
            {
                arquivoAnexo = imovelService.lerAnexoPessoa(id);
            }
            catch (IOException e)
            {
            }

            if (arquivoAnexo != null)
            {
                dto.setAnexo(arquivoAnexo.getBytes());
            }

            return ResponseEntity.status(200).body(dto);
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Slice<ImovelGetDTO>> lerTodos(Pageable pageable,
                                                        @RequestParam(required = false) Integer numeroMatricula,
                                                        @RequestParam(required = false) Integer protocolo,
                                                        @RequestParam(required = false) String tipoImovel,
                                                        @RequestParam(required = false) String rua)
    {
        Slice<ImovelProprietarios> imoveis = imovelService.lerTodos(pageable, numeroMatricula, protocolo, tipoImovel, rua);
        Slice<ImovelGetDTO> dto = imoveis.map(imovel ->
        {
            Arquivo arquivoAnexo = null;

            try
            {
                arquivoAnexo = imovelService.lerAnexoPessoa(imovel.getId());
            }
            catch (IOException e)
            {
            }

            ImovelGetDTO imovelGetDTO = objectConverter.convert(imovel, ImovelGetDTO.class);

            if (arquivoAnexo != null)
            {
                imovelGetDTO.setAnexo(arquivoAnexo.getBytes());
            }

            return imovelGetDTO;
        });
        return ResponseEntity.status(200).body(dto);
    }
}

