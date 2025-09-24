package com.personalizados.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CampoErro {
    private String campo;
    private String mensagem;
}
