package com.personalizados.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponseDTO {

    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemPedidoResponseDTO toResponseDTO() {
        return new ItemPedidoResponseDTO(
            this.id,
            this.produto != null ? this.produto.getId() : null,
            this.produto != null ? this.produto.getNome() : null,
            this.quantidade,
            this.precoUnitario,
            this.subtotal
        );
    }

}
