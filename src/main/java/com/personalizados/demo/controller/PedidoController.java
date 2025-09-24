package com.personalizados.demo.controller;

import com.personalizados.demo.dto.PedidoRequestDTO;
import com.personalizados.demo.dto.PedidoResponseDTO;
import com.personalizados.demo.model.Pedido;
import com.personalizados.demo.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO dto) {
        Pedido pedido = service.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        List<PedidoResponseDTO> pedidos = service.listar();
        return pedidos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(pedidos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
