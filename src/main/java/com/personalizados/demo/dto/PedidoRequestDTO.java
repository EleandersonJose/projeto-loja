package com.personalizados.demo.dto;

import com.personalizados.demo.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Itens são obrigatórios")
    private List<ItemPedidoRequestDTO> itens;

    private StatusPedido status;
}
