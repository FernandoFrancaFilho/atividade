package com.aula.demo.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.aula.demo.dtos.PedidoDto;
import com.aula.demo.services.PedidoService;


@RestController
@RequestMapping(value = "/pedidos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @PostMapping("/{pedidoId}/adicionar-produtos")
    public ResponseEntity<Pedido> adicionarProdutosAoPedido(@PathVariable Long pedidoId, @RequestBody List<Long> produtosIds) {
        Pedido pedido = pedidoService.adicionarProdutosAoPedido(pedidoId, produtosIds);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public Page<PedidoDto> findAll(@PageableDefault(size = 5) Pageable pagination) {
        return pedidoService.findAll(pagination);
    }
    
    @GetMapping("/{id}")
    public PedidoDto findById(@PathVariable long id) {
    	return ResponseEntity.ok(pedidoService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<PedidoDto> save(@RequestBody PedidoDto pedidoDto, UriComponentsBuilder uriBuilder){
    	PedidoDto pedidoSaved = pedidoService.save(pedidoDto);
    	URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoSaved.id()).toUri();
    	return ResponseEntity.created(uri) .body(pedidoSaved);
    }
    
    @PutMapping("{/id}")
    public ResponseEntity<PedidoDto> update(@PathVariable long id, @RequestBody PedidoDto pedidoDto){
    	return ResponseEntity.ok(PedidoService.update(id, pedidoDto));
    }
    
    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable long id){
    	pedidoService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/atualizar-status")
    public ResponseEntity<String>atualizarStatus(@PathVariable long id, @RequestBody PedidoDto pedidoDto){
    	return ResponseEntity.ok(pedidoService.atualizarStatus(id, pedidoDto));
    }

    }

