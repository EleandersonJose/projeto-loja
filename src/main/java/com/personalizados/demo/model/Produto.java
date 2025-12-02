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

<<<<<<< HEAD


    public ProdutoResponseDTO toResponseDTO() {
        String urlBase = "http://localhost:60999/api/produtos/imagens/";

        //Evita erro caso a imagem seja nula
        String urlCompleta = (this.nomeArquivoImagem != null && !this.nomeArquivoImagem.isBlank())
                                                    ? urlBase + this.nomeArquivoImagem 
                                                    : null;
=======
    public ProdutoResponseDTO toResponseDTO() {
        String urlBase = "http://localhost:64999/api/produtos/imagens/";

        String urlCompleta = (this.nomeArquivoImagem != null && !this.nomeArquivoImagem.isBlank())
                ? urlBase + this.nomeArquivoImagem
                : null;
>>>>>>> 1a27b37 (Removendo pasta duplicada projeto-loja)

        return new ProdutoResponseDTO(
                this.id,
                this.nome,
                this.descricao,
                this.preco,
                this.nomeArquivoImagem,
<<<<<<< HEAD
                urlCompleta
        );
=======
                urlCompleta);
>>>>>>> 1a27b37 (Removendo pasta duplicada projeto-loja)
    }
}