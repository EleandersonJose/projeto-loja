/*package com.personalizados.demo.service;

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
public class PedidoServiceCopy {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        //Busca cliente e lanca excecao caso de erro
        
        //monta a lista de produtos
        
        //calcula o total
        
        // retorna um itempeido co os dados do produto, como quantidde preco e totale no fim convete em uma lista
        
        //total de iens
        
        // instancia um pedido;
       
        //salva o pedido instanciado

        // retotno do metodo
        return null;
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
*/