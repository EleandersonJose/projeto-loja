package com.personalizados.demo.service;

import com.personalizados.demo.dto.ClienteRequestDTO;
import com.personalizados.demo.dto.ClienteResponseDTO;
import com.personalizados.demo.enums.Role;
import com.personalizados.demo.model.Cliente;
import com.personalizados.demo.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTestUPDATE {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private ClienteRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = Cliente.builder()
                .id(1L)
                .nome("Escanor Sama")
                .email("escanor@sol.com")
                .senha("forcaDoSol")
                .role(Role.CLIENTE)
                .build();

        requestDTO = new ClienteRequestDTO(
                "Escanor Sama",
                "escanor@sol.com",
                "forcaDoSol",
                Role.CLIENTE);
    }

    @Test
    void deveSalvarClienteComSucesso() {
        when(repository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente salvo = service.salvar(requestDTO);

        assertNotNull(salvo);
        assertEquals(cliente.getNome(), salvo.getNome());
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarClienteComEmailDuplicado() {
        when(repository.existsByEmail(requestDTO.getEmail())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.salvar(requestDTO));

        assertEquals("Email já cadastrado.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveListarClientes() {
        when(repository.findAll()).thenReturn(Arrays.asList(cliente));

        List<ClienteResponseDTO> lista = service.listar();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        assertEquals("Escanor Sama", lista.get(0).getNome());
    }

    @Test
    void deveAlterarClienteComSucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente alterado = service.alterar(1L, requestDTO);

        assertNotNull(alterado);
        assertEquals("Escanor Sama", alterado.getNome());
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoAoAlterarClienteInexistente() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.alterar(1L, requestDTO));

        assertEquals("Cliente não encontrado", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveApagarClientePorId() {
        doNothing().when(repository).deleteById(1L);

        service.apagar(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveBuscarClientePorNome() {
        when(repository.findByNomeContainingIgnoreCase("Escanor"))
                .thenReturn(Arrays.asList(cliente));

        List<ClienteResponseDTO> encontrados = service.buscarPorNome("Escanor");

        assertEquals(1, encontrados.size());
        assertEquals("Escanor Sama", encontrados.get(0).getNome());
    }
}
