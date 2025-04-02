package com.java.crud.exception;

import com.java.crud.constants.ApplicationConstants;
import com.java.crud.util.PropertiesUtil;

public class ClienteNomeJaExistenteException extends Exception {

    public ClienteNomeJaExistenteException() {
        super(PropertiesUtil.getProperty(ApplicationConstants.PROP_MENSAGEM_CLIENTE_NOME_JA_EXISTE));
    }
}
