package com.personalizados.demo.model;

import com.personalizados.demo.dto.ClienteResponseDTO;
import com.personalizados.demo.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "A role é obrigatória")
    @Enumerated(EnumType.STRING)
    private Role role;

    // 🔥 Conversão para DTO
    public ClienteResponseDTO toResponseDTO() {
        return new ClienteResponseDTO(
                this.id,
                this.nome,
                this.email,
                this.role
        );
    }
}
