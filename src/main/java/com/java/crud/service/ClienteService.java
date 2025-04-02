package com.java.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.java.crud.entity.Cliente;
import com.java.crud.exception.ClienteInexistenteException;
import com.java.crud.exception.ClienteNomeJaExistenteException;
import com.java.crud.exception.ClienteNomeObrigatorioException;
import com.java.crud.record.ClienteRecord;
import com.java.crud.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getCliente(Integer id) throws ClienteInexistenteException {

        Cliente cliente = clienteRepository.findByIdCliente(id);

        if (cliente == null) {
            throw new ClienteInexistenteException();
        }

        return cliente;

    }

    public Cliente getCliente(String nomeCliente) throws ClienteInexistenteException {

        Cliente cliente = clienteRepository.findByNomeCliente(nomeCliente);

        if (cliente == null) {
            throw new ClienteInexistenteException();
        }

        return cliente;

    }

    public List<Cliente> getClientes() throws Exception {
        return clienteRepository.findAll();
    }

    public Cliente criar(ClienteRecord clienteRecord)
            throws ClienteNomeJaExistenteException, ClienteNomeObrigatorioException {
        Cliente cliente = clienteRepository.findByNomeCliente(clienteRecord.nome());

        if (cliente != null) {
            throw new ClienteNomeJaExistenteException();
        }

        if ("".equals(clienteRecord.nome())) {
            throw new ClienteNomeObrigatorioException();
        }

        Cliente clienteNovo = new Cliente();
        clienteNovo.setNome(clienteRecord.nome());
        return clienteRepository.saveAndFlush(clienteNovo);
    }

    public Cliente alterar(int id, ClienteRecord clienteRecord)
            throws ClienteNomeJaExistenteException, ClienteInexistenteException, ClienteNomeObrigatorioException {
        Cliente clienteValidado = clienteRepository.findByNomeCliente(clienteRecord.nome());
        if (clienteValidado != null) {
            throw new ClienteNomeJaExistenteException();
        }

        Cliente cliente = clienteRepository.findByIdCliente(id);

        if (cliente == null) {
            throw new ClienteInexistenteException();
        }

        if ("".equals(clienteRecord.nome())) {
            throw new ClienteNomeObrigatorioException();
        }

        cliente.setNome(clienteRecord.nome());
        return clienteRepository.saveAndFlush(cliente);
    }

    public void excluir(int id) throws ClienteInexistenteException {
        Cliente cliente = clienteRepository.findByIdCliente(id);

        if (cliente == null) {
            throw new ClienteInexistenteException();
        }
        clienteRepository.deleteById(id);
    }
}
