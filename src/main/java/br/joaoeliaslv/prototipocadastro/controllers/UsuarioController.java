package br.joaoeliaslv.prototipocadastro.controllers;

import br.joaoeliaslv.prototipocadastro.entities.Usuario;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioGetDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioPostDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.usuario.UsuarioPutDTO;
import br.joaoeliaslv.prototipocadastro.service.UsuarioService;
import br.joaoeliaslv.prototipocadastro.util.objectconverter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("${controllers.usuario.mapping}")
public class UsuarioController
{
    private final UsuarioService usuarioService;
    private final ObjectConverter objectConverter;

    public UsuarioController(UsuarioService usuarioService, ObjectConverter objectConverter)
    {
        this.usuarioService = usuarioService;
        this.objectConverter = objectConverter;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioPostDTO dto)
    {
        Usuario usuario = objectConverter.convert(dto, Usuario.class);
        usuarioService.criar(usuario);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid UsuarioPutDTO dto, @PathVariable int id)
    {
        Usuario usuario = objectConverter.convert(dto, Usuario.class);
        try
        {
            usuarioService.atualizar(usuario, id);
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
            usuarioService.deletar(id);
            return ResponseEntity.status(200).build();
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioGetDTO> ler(@PathVariable int id)
    {
        try
        {
            Usuario usuario = usuarioService.ler(id);
            UsuarioGetDTO dto = objectConverter.convert(usuario, UsuarioGetDTO.class);
            return ResponseEntity.status(200).body(dto);
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioGetDTO>> lerTodos()
    {
        List<Usuario> usuarios = usuarioService.lerTodos();
        List<UsuarioGetDTO> dto = objectConverter.convert(usuarios, UsuarioGetDTO.class);
        return ResponseEntity.status(200).body(dto);
    }
}
