package com.personalizados.demo.service;

import com.personalizados.demo.dto.*;
import com.personalizados.demo.enums.StatusPedido;
import com.personalizados.demo.model.*;
import com.personalizados.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        //Busca cliente e lanca excecao caso de erro
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        //monta a lista de produtos
        List<ItemPedido> itens = dto.getItens().stream().map(itemDto -> {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.getQuantidade()));

            return ItemPedido.builder()
                    .produto(produto)
                    .quantidade(itemDto.getQuantidade())
                    .precoUnitario(produto.getPreco())
                    .subtotal(subtotal)
                    .build();
        }).toList();

        BigDecimal total = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .dataPedido(LocalDateTime.now())
                .itens(itens)
                .status(dto.getStatus() != null ? dto.getStatus() : StatusPedido.AGUARDANDO)
                .total(total)
                .build();
        
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoSalvo.toResponseDTO();
    }

    public List<PedidoResponseDTO> listar() {
        return pedidoRepository.findAll().stream()
                .map(Pedido::toResponseDTO)
                .toList();
    }

    public void apagar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
