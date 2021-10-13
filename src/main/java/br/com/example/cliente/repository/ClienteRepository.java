package br.com.example.cliente.repository;

import br.com.example.cliente.entity.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<cliente, Long> {

}
