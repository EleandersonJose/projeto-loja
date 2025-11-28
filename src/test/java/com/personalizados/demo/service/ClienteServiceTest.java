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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private ClienteRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Cliente cliente = new Cliente();
        cliente.setNome("Escanor Sama");
        cliente.setEmail("escanor@sunshine.com");
        cliente.setSenha("forcaDoSol");
        cliente.setRole(Role.ADMIN);

        requestDTO = new ClienteRequestDTO(
                    "Escanor Sama",
                    "escanor@sunshine.com",
                    "forcaDoSol",
                    Role.ADMIN
        );
    }

    @Test
    void deveSalvarClienteComSucesso() {
        when(repository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDTO clienteSalvo = service.salvar(requestDTO);

        assertNotNull(clienteSalvo);
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
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

        ClienteResponseDTO clienteAlterado = service.alterar(1L, requestDTO);

        assertNotNull(clienteAlterado);
        assertEquals(requestDTO.getNome(), clienteAlterado.getNome());
        assertEquals(requestDTO.getEmail(), clienteAlterado.getEmail());
        assertEquals(requestDTO.getRole(), clienteAlterado.getRole());
        verify(repository, times(1)).save(any(Cliente.class));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoAlterarClienteInexistente() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, 
                () -> service.alterar(1L, requestDTO));
        
        assertEquals("Cliente nao encontrado", ex.getMessage());
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

        List<ClienteResponseDTO> clienteEncontrado = service.buscarPorNome("Escanor");

        assertEquals(1, clienteEncontrado.size());
        assertEquals("Escanor Sama", clienteEncontrado.get(0).getNome());
    }
}
