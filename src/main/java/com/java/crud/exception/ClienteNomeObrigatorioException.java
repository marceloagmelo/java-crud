package com.java.crud.exception;

import com.java.crud.constants.ApplicationConstants;
import com.java.crud.util.PropertiesUtil;

public class ClienteNomeObrigatorioException extends Exception {

    public ClienteNomeObrigatorioException() {
        super(PropertiesUtil.getProperty(ApplicationConstants.PROP_MENSAGEM_CLIENTE_NOME_OBRIGATORIO));
    }
}