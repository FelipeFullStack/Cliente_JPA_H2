package br.com.example.cliente.service;

import br.com.example.cliente.entity.cliente;
import br.com.example.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public cliente Save(cliente cliente) {
        return clienteRepository.save(cliente);

    }

    public List<cliente> listClient() {
        return clienteRepository.findAll();
    }

    public Optional<cliente> searchClient(Long id) {
        return clienteRepository.findById(id);
    }

    public void removeClintId(Long id) {
        clienteRepository.deleteById(id);
    }

}
