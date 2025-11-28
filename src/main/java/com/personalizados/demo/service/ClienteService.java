package com.personalizados.demo.service;

import com.personalizados.demo.dto.ClienteRequestDTO;
import com.personalizados.demo.dto.ClienteResponseDTO;
import com.personalizados.demo.exception.ResourceNotFoundException;
import com.personalizados.demo.model.Cliente;
import com.personalizados.demo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        //Cliente cliente = Cliente.builder()
        //       .nome(dto.getNome())
        //        .email(dto.getEmail())
        //        .senha(dto.getSenha()) // Aqui depois podemos aplicar criptografia
        //        .role(dto.getRole())
        //        .build();
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setRole(dto.getRole());

        Cliente clienteSalvo = repository.save(cliente);

        return clienteSalvo.toResponseDTO();

    }

    public List<ClienteResponseDTO> listar() {
    return repository.findAll()          // lista de Cliente (pode estar vazia)
            .stream()                    // cria um stream
            .map(Cliente::toResponseDTO) // converte cada Cliente em DTO
            .toList();                   // retorna uma lista de DTOs
    }

    public ClienteResponseDTO alterar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
        
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setRole(dto.getRole());

        Cliente clienteAlterado = repository.save(cliente);

        return clienteAlterado.toResponseDTO();
    }

    @Transactional
    public void apagar(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado com id" + id));

        repository.delete(cliente);
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        return  repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(Cliente::toResponseDTO)
                .toList();
        }
}
