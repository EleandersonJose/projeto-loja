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

        cliente = Cliente.builder();

        requestDTO = new ClienteRequestDTO();
    }

    @Test
    void deveSalvarClienteComSucesso() {

    }

    @Test
    void deveLancarExcecaoAoSalvarClienteComEmailDuplicado() {

    }

    @Test
    void deveListarClientes() {

    }

    @Test
    void deveAlterarClienteComSucesso() {

    }

    @Test
    void deveLancarExcecaoAoAlterarClienteInexistente() {

    }

    @Test
    void deveApagarClientePorId() {

    }

    @Test
    void deveBuscarClientePorNome() {

    }
}
