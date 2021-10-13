package br.com.example.cliente.http.controller;


import br.com.example.cliente.entity.cliente;
import br.com.example.cliente.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public cliente salve(@RequestBody cliente cliente) {
        return clienteService.Save(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<cliente> listClient() {
        return clienteService.listClient();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public cliente searchClientId(@PathVariable("id") Long id) {
        return clienteService.searchClient(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void removeClient(@PathVariable("id") Long id){
            clienteService.searchClient(id)
                    .map(cliente -> {
                        clienteService.removeClintId(cliente.getId());
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

        }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void  updateClient(@PathVariable("id")Long id, @RequestBody cliente cliente) {
        clienteService.searchClient(id)
        .map(clienteBase -> {
            modelMapper.map(cliente, clienteBase);
            clienteService.Save(clienteBase);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

    }
}
