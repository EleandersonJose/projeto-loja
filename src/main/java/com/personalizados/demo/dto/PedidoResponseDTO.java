package com.personalizados.demo.dto;

import com.personalizados.demo.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private ClienteResponseDTO cliente;
    private LocalDateTime dataPedido;
    private List<ItemPedidoResponseDTO> itens;
    private StatusPedido status;
    private BigDecimal total;
}
