package com.java.crud.exception;

import com.java.crud.constants.ApplicationConstants;
import com.java.crud.util.PropertiesUtil;

public class ClienteInexistenteException extends Exception {

    public ClienteInexistenteException() {
        super(PropertiesUtil.getProperty(ApplicationConstants.PROP_MENSAGEM_CLIENTE_NAO_ENCONTRADO));
    }
}