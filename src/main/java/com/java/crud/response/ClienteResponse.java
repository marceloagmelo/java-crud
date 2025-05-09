package com.java.crud.response;

import com.java.crud.entity.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {

    private String status;
    private String message;
    private Cliente cliente;

    public ClienteResponse(String status, String mensagem) {
        this.message = mensagem;
        this.status = status;
    }

    public ClienteResponse(String status, String mensagem, Cliente cliente) {
        this.message = mensagem;
        this.status = status;
        this.cliente = cliente;
    }

}
