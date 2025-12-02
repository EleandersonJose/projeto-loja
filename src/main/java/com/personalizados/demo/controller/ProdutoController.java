package com.personalizados.demo.controller;

import com.personalizados.demo.dto.ProdutoRequestDTO;
import com.personalizados.demo.dto.ProdutoResponseDTO;
import com.personalizados.demo.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(
            @Valid @ModelAttribute ProdutoRequestDTO dto) throws IOException {
<<<<<<< HEAD
        //Produto produto = service.salvar(dto);
        ProdutoResponseDTO produtoSalvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(produtoSalvo);
=======

        ProdutoResponseDTO produtoSalvo = service.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
>>>>>>> 1a27b37 (Removendo pasta duplicada projeto-loja)
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        List<ProdutoResponseDTO> produtos = service.listarProdutos();
        return ResponseEntity.ok()
                             .body(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @ModelAttribute ProdutoRequestDTO dto) throws IOException {
        ProdutoResponseDTO produtoAtualizado = service.alterar(id, dto);
<<<<<<< HEAD
=======

>>>>>>> 1a27b37 (Removendo pasta duplicada projeto-loja)
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
