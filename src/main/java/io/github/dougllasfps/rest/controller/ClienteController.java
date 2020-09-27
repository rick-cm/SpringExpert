package io.github.dougllasfps.rest.controller;

import io.github.dougllasfps.domain.entity.Cliente;
import io.github.dougllasfps.domain.repository.Clientes;
import io.github.dougllasfps.exception.ResourceNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientesRepo;

    public ClienteController( Clientes clientes) {
        this.clientesRepo = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id ){
        return clientesRepo
                .findById(id)
                .orElseThrow(() ->
                                new ResourceNotFoundException(
                                "Cliente não encontrado") );

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody @Valid Cliente cliente ){
        return clientesRepo.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        clientesRepo.findById(id)
                .map( cliente -> {
                    clientesRepo.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente não encontrado") );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody @Valid Cliente cliente ){
        clientesRepo
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepo.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente não encontrado") );
    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return clientesRepo.findAll(example);
    }

}
