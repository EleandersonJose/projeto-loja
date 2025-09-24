package com.personalizados.demo.model;

import com.personalizados.demo.dto.ProdutoResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;

    // Agora o campo armazena apenas o nome da imagem, tipo "caneca123.png"
    private String nomeArquivoImagem;

    public ProdutoResponseDTO toResponseDTO() {
        String url = "http://localhost:60999/api/produtos/imagens/" + this.nomeArquivoImagem;

        return ProdutoResponseDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .descricao(this.descricao)
                .preco(this.preco)
                .nomeArquivoImagem(this.nomeArquivoImagem)
                .imagemUrl(url)
                .build();
    }
}