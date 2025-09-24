package com.personalizados.demo.controller;

import com.personalizados.demo.dto.ClienteRequestDTO;
import com.personalizados.demo.dto.ClienteResponseDTO;
import com.personalizados.demo.model.Cliente;
import com.personalizados.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO dto) {
        Cliente cliente = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente.toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        List<ClienteResponseDTO> clientes = service.listar();
        return clientes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(clientes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ClienteResponseDTO> clientes = service.buscarPorNome(nome);
        return clientes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> alterar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteRequestDTO dto) {
        Cliente cliente = service.alterar(id, dto);
        return ResponseEntity.ok(cliente.toResponseDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
