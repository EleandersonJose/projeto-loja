package com.personalizados.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @Nullable
    private MultipartFile imagem;
}
