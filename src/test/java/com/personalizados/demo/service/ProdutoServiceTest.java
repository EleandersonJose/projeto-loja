package com.personalizados.demo.service;

import com.personalizados.demo.dto.ProdutoRequestDTO;
import com.personalizados.demo.dto.ProdutoResponseDTO;
import com.personalizados.demo.model.Produto;
import com.personalizados.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ImagemService imagemService;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste 1: salvar produto
    @Test
    void deveSalvarProduto() throws IOException {
        // TODO: preencher com Arrange, Act e Assert
        ProdutoRequestDTO request = new ProdutoRequestDTO();
        request.setNome("Caneca");
        request.setDescricao("Naruto");
        request.setPreco(new BigDecimal("39.99"));
        request.setImagem(null);

        Produto produto = Produto.builder()
                .id(1L)
                .nome("Caneca")
                .descricao("Naruto")
                .preco(new BigDecimal("39.99"))
                .nomeArquivoImagem("Naruto.png")
                .build();

        when(imagemService.salvarImagem(null)).thenReturn("Naruto.png");
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        // Act
        ProdutoResponseDTO response = produtoService.salvar(request);

        // Assert
        assertEquals("Caneca", response.getNome());
        assertEquals(new BigDecimal("39.99"), response.getPreco());
        assertEquals("Naruto.png", response.getNomeArquivoImagem());
        assertNotNull(response.getId());
    }

    // Teste 2: listar produtos
    @Test
    void deveListarProdutos() {
        // TODO: preencher com Arrange, Act e Assert

        // Arrange
        Produto produto1 = Produto.builder()
                .id(1L)
                .nome("Caneca")
                .descricao("Branca")
                .preco(new BigDecimal("29.99"))
                .nomeArquivoImagem("CanecaBranca.png")
                .build();

        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("CanecaP")
                .descricao("Preta")
                .preco(new BigDecimal("35.99"))
                .nomeArquivoImagem("CanecaPreta.png")
                .build();

        when(produtoRepository.findAll()).thenReturn(List.of(produto1, produto2));

        // Act
        List<ProdutoResponseDTO> response = produtoService.listarProdutos();

        // Assert
        assertEquals(2, response.size());
        assertEquals("Caneca", response.get(0).getNome());
        assertEquals("CanecaP", response.get(1).getNome());
    }

    // Teste 3: atualizar produto
    @Test
    void deveAtualizarProduto() throws IOException {
        // TODO: preencher com Arrange, Act e Assert

        // Arrange
        Long id = 1L;
        ProdutoRequestDTO requestDTO = new ProdutoRequestDTO();
        requestDTO.setNome("Caneca Nova");
        requestDTO.setDescricao("Desc Alterada");
        requestDTO.setPreco(new BigDecimal("45.0"));
        requestDTO.setImagem(null);

        Produto produtoExistente = Produto.builder()
                .id(id)
                .nome("Caneca")
                .descricao("Desc")
                .preco(new BigDecimal("40.0"))
                .nomeArquivoImagem("antiga.png")
                .build();

        Produto produtoAlterado = Produto.builder()
                .id(id)
                .nome("Caneca Nova")
                .descricao("Desc Alterada")
                .preco(new BigDecimal("45.0"))
                .nomeArquivoImagem("antiga.png")
                .build();

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAlterado);

        // act
        ProdutoResponseDTO responseDTO = produtoService.alterar(id, requestDTO);

        // Assert
        assertEquals("Caneca Nova", responseDTO.getNome());
        assertEquals("Desc Alterada", responseDTO.getDescricao());
        assertEquals(new BigDecimal("45.0"), responseDTO.getPreco());
        assertEquals("antiga.png", responseDTO.getNomeArquivoImagem());
    }

    // Teste 4: deletar produto
    @Test
    void deveDeletarProduto() {
        // TODO: preencher com Arrange, Act e Assert

        // Arrange
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto teste");
        produto.setNomeArquivoImagem("imagem.png");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // Act
        produtoService.apagar(1L);

        // Assert
        verify(imagemService, times(1)).deletarImagem("imagem.png");
        verify(produtoRepository, times(1)).deleteById(1L);
        ;
    }

    // ===============================
    // Teste 5: erro ao atualizar produto inexistente
    // ===============================
    @Test
    void deveLancarExcecaoAoAtualizarProdutoInexistente() {
        // TODO: preencher com Arrange, Act e Assert
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        dto.setNome("Novo Nome");

        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> produtoService.alterar(99L, dto));

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(produtoRepository, never()).save(any());
    }
}