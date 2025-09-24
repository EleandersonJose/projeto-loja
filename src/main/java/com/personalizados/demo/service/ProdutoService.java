package com.personalizados.demo.service;

import com.personalizados.demo.dto.ProdutoRequestDTO;
import com.personalizados.demo.dto.ProdutoResponseDTO;
import com.personalizados.demo.model.Produto;
import com.personalizados.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ImagemService imagemService;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) throws IOException {
        String nomeArquivoImagem = imagemService.salvarImagem(dto.getImagem());

        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .nomeArquivoImagem(nomeArquivoImagem) // ✅ novo campo
                .build();

        Produto salvo = repository.save(produto);

        // ✅ Converte para DTO antes de retornar
        return salvo.toResponseDTO();
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return repository.findAll()
                .stream()
                .map(Produto::toResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO alterar(Long id, ProdutoRequestDTO dto) throws IOException {
        // Busca o produto existente
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Atualiza os campos do produt
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        // Atualiza a imagem apenas se uma nova foi enviada
        if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
            // remove imagem anterior
            imagemService.deletarImagem(produto.getNomeArquivoImagem());

            // Salva a nova imagem
            String novoNomeArquivo = imagemService.salvarImagem(dto.getImagem());
            produto.setNomeArquivoImagem(novoNomeArquivo); // ✅ novo campo
        }

        // Salva no repositorio
        Produto produtoSalvo = repository.save(produto);

        // ✅ Converte para ResponseDTO antes de retornar
        return produtoSalvo.toResponseDTO();
    }

    public void apagar(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Deletar imagem física
        imagemService.deletarImagem(produto.getNomeArquivoImagem()); // ✅ novo campo

        // Deletar produto do banco
        repository.deleteById(id);
    }
}
