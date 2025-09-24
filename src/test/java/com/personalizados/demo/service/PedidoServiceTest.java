/*
 * package com.personalizados.demo.service;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import static org.mockito.ArgumentMatchers.any;
 * import static org.mockito.ArgumentMatchers.anyList;
 * import static org.mockito.Mockito.doNothing;
 * import static org.mockito.Mockito.times;
 * import static org.mockito.Mockito.when;
 * 
 * import java.util.List;
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith;
 * import org.mockito.InjectMocks;
 * import org.mockito.Mock;
 * import org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import com.personalizados.demo.enums.StatusPedido;
 * import com.personalizados.demo.model.Cliente;
 * import com.personalizados.demo.model.ItemPedido;
 * import com.personalizados.demo.model.Pedido;
 * import com.personalizados.demo.repository.ItemPedidoRepository;
 * import com.personalizados.demo.repository.PedidoRepository;
 * import com.personalizados.demo.service.PedidoService;
 * 
 * @ExtendWith(MockitoExtension.class)
 * class PedidoServiceTest {
 * 
 * @InjectMocks
 * private PedidoService pedidoService;
 * 
 * @Mock
 * private PedidoRepository pedidoRepository;
 * 
 * @Mock
 * private ItemPedidoRepository itemPedidoRepository;
 * 
 * @Test
 * void deveCriarPedidoComItensECalcularValorTotal() {
 * Cliente cliente = new Cliente("Jose", "teste@teste.com", "1234",
 * Role.CLIENTE);
 * ItemPedido item1 = new ItemPedido(null, null, 2, 30.0); // qtd * preço = 60.0
 * ItemPedido item2 = new ItemPedido(null, null, 1, 40.0); // qtd * preço = 40.0
 * List<ItemPedido> itens = List.of(item1, item2);
 * 
 * // Simula o save do pedido
 * when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation ->
 * invocation.getArgument(0));
 * when(itemPedidoRepository.saveAll(anyList())).thenReturn(itens);
 * 
 * Pedido pedido = pedidoService.criarPedido(cliente, itens);
 * 
 * assertEquals(100.0, pedido.getValorTotal());
 * assertEquals(2, pedido.getItens().size());
 * assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
 * }
 * 
 * @Test
 * void deveAtualizarStatusDoPedido() {
 * Pedido pedido = new Pedido();
 * pedido.setStatus(StatusPedido.PENDENTE);
 * 
 * when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
 * when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
 * 
 * Pedido atualizado = pedidoService.atualizarStatus(1L,
 * StatusPedido.FINALIZADO);
 * 
 * assertEquals(StatusPedido.FINALIZADO, atualizado.getStatus());
 * }
 * 
 * @Test
 * void deveListarTodosOsPedidos() {
 * List<Pedido> pedidos = List.of(new Pedido(), new Pedido());
 * 
 * when(pedidoRepository.findAll()).thenReturn(pedidos);
 * 
 * List<Pedido> resultado = pedidoService.listarPedidos();
 * 
 * assertEquals(2, resultado.size());
 * }
 * 
 * @Test
 * void deveDeletarPedidoPorId() {
 * doNothing().when(pedidoRepository).deleteById(1L);
 * 
 * pedidoService.apagar(1L);
 * 
 * verify(pedidoRepository, times(1)).deleteById(1L);
 * }
 * }
 */