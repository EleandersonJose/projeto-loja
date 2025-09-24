package com.personalizados.demo.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String mensagem;
    private List<CampoErro> erros; // Pode ser null se não for erro de campo
}
