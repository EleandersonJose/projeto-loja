package com.personalizados.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPedidoRequestDTO {

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;
}
