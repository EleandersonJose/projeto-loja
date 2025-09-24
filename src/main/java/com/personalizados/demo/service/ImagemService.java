package com.personalizados.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagemService {

    private final String pastaUpload = "imagens/";

    // 🔥 Retorna apenas o nome do arquivo (ex: 1234567890_nome.png)
    public String salvarImagem(MultipartFile imagem) throws IOException {
        if (imagem.isEmpty()) {
            throw new IOException("Imagem vazia");
        }

        // Cria a pasta se não existir
        File pasta = new File(pastaUpload);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
        Path caminho = Paths.get(pastaUpload + nomeArquivo);
        Files.write(caminho, imagem.getBytes());

        return nomeArquivo; // ⬅️ só retorna o nome do arquivo
    }

    // 🔥 Agora recebe o nome do arquivo diretamente (sem URL)
    public void deletarImagem(String nomeArquivoImagem) {
        if (nomeArquivoImagem == null || nomeArquivoImagem.isBlank()) return;

        Path caminho = Paths.get(pastaUpload, nomeArquivoImagem); // Corrigido Pahts ➜ Paths

        try {
            Files.deleteIfExists(caminho);
        } catch (IOException e) {
            System.err.println("Erro ao apagar imagem: " + e.getMessage());
        }
    }
}
