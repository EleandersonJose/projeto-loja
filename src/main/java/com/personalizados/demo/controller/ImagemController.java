package com.personalizados.demo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagemController {

    @GetMapping("/imagens/{nomeArquivo}")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable String nomeArquivo) throws IOException {
        Path caminho = Paths.get("imagens").resolve(nomeArquivo);

        if (!Files.exists(caminho)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imagem = Files.readAllBytes(caminho);
        String contentType = Files.probeContentType(caminho);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imagem);
    }
}
