package com.personalizados.demo.model;

import com.personalizados.demo.enums.StatusPedido;
import com.personalizados.demo.dto.PedidoResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private LocalDateTime dataPedido;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;

    // 🔥 Conversão para DTO
    public PedidoResponseDTO toResponseDTO() {
        return new PedidoResponseDTO(
            this.id,
            this.cliente != null ? this.cliente.toResponseDTO() : null,
            this.dataPedido,
            this.itens != null ? this.itens.stream()
                                    .map(ItemPedido::toResponseDTO)
                                    .toList()
                                    : List.of(),
            this.status,
            this.total
        );
    }
}
