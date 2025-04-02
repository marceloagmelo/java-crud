package com.java.crud.controller;

import org.springframework.web.bind.annotation.RestController;

import com.java.crud.constants.ApplicationConstants;
import com.java.crud.exception.ClienteInexistenteException;
import com.java.crud.exception.ClienteNomeJaExistenteException;
import com.java.crud.exception.ClienteNomeObrigatorioException;
import com.java.crud.record.ClienteRecord;
import com.java.crud.response.ClienteListaResponse;
import com.java.crud.response.ClienteResponse;
import com.java.crud.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
@Tag(name = "open-api")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listagem dos clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("")
    public ResponseEntity<?> listar() throws IOException {
        try {
            ClienteListaResponse response = new ClienteListaResponse(ApplicationConstants.STATUS_OK,
                    ApplicationConstants.MENSAGEM_CLIENTE_LISTA_SUCESSO,
                    clienteService.getClientes());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException ioe) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ioe.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Recuperar cliente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente ou serviço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> recuperar(@PathVariable("id") Integer id)
            throws IOException {
        try {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_OK,
                    ApplicationConstants.MENSAGEM_CLIENTE_ALTERADO_SUCESSO,
                    clienteService.getCliente(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ClienteInexistenteException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Cadastrar cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "409", description = "Cliente já cadastrado"),
    })
    @PostMapping("")
    public ResponseEntity<?> criar(@RequestBody ClienteRecord clienteRecord) throws IOException {
        try {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_OK,
                    ApplicationConstants.MENSAGEM_CLIENTE_CRIADO_SUCESSO,
                    clienteService.criar(clienteRecord));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ClienteNomeObrigatorioException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (ClienteNomeJaExistenteException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Alterar cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente alterado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente ou serviço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Integer id, @RequestBody ClienteRecord clienteRecord)
            throws IOException {
        try {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_OK,
                    ApplicationConstants.MENSAGEM_CLIENTE_ALTERADO_SUCESSO,
                    clienteService.alterar(id, clienteRecord));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ClienteNomeObrigatorioException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (ClienteInexistenteException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Excluir cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente ou serviço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Integer id) throws IOException {
        try {
            clienteService.excluir(id);
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_OK,
                    ApplicationConstants.MENSAGEM_CLIENTE_DELETADO_SUCESSO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ClienteInexistenteException ec) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, ec.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ClienteResponse response = new ClienteResponse(ApplicationConstants.STATUS_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
