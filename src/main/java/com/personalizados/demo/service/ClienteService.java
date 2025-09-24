package com.personalizados.demo.service;

import com.personalizados.demo.dto.ClienteRequestDTO;
import com.personalizados.demo.dto.ClienteResponseDTO;
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

    public Cliente salvar(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha()) // Aqui depois podemos aplicar criptografia
                .role(dto.getRole())
                .build();

        return repository.save(cliente);
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(Cliente::toResponseDTO)
                .toList();
    }

    public Cliente alterar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setRole(dto.getRole());

        return repository.save(cliente);
    }

    @Transactional
    public void apagar(Long id) {
        repository.deleteById(id);
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(Cliente::toResponseDTO)
                .toList();
    }
}
