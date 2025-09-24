package com.personalizados.demo.dto;

import com.personalizados.demo.enums.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Role role;
}
