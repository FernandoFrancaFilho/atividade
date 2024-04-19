package com.aula.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aula.demo.Pedido;
import com.aula.demo.dtos.PedidoDto;
import com.aula.demo.enums.StatusPedido;
import com.aula.demo.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Page<PedidoDto> findAll(Pageable pagination) {
        return pedidoRepository.findAll(pagination).map(pedido -> new PedidoDto(pedido));
    }
    
    public PedidoDto findById(long id) {
    	return PedidoRepository.getReferenceById(id);
    }
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido adicionarProdutosAoPedido(Long pedidoId, List<Long> produtosIds) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        List<Produto> produtos = produtoRepository.findAllById(produtosIds);
        if (produtos.size() != produtosIds.size()) {
            throw new IllegalArgumentException("Um ou mais produtos não foram encontrados");
        }

        for (Produto produto : produtos) {
            pedido.setProduto(produto);
            // Adicione lógica adicional se necessário
        }

        return pedidoRepository.save(pedido);
    }
}
    
    @Transactional
    public PedidoDto save(PedidoDto pedidoDto) {
    	Pedido pedido = Pedido.fromDto(pedidoDto);
    	Pedido pedidoSalvo = PedidoRepository.save(pedido);
    	pedidoSalvo.setStatus(StatusPedido.CRIADO);
    	return new PedidoDto(pedidoSalvo);
}
    
    @Transactional
    public PedidoDto update(lond id, PedidoDto pedidoDto) {
    	Pedido pedido = com.aula.demo.models.Pedido.fromDto(pedidoDto);
    	Pedido.setId(id);
    	Pedido pedidosaved = pedidoRepository.save(pedido);
    	return new PedidoDto(pedidosaved);
    }
    
    public void delete(long id) {
    	pedidoRepository.deleteById(id);
    }
    @Transactional
    public String atualizarStatus(long id, PedidoDto pedidoDto) {
    	com.aula.demo.models.Pedido pedido = com.aula.demo.models.Pedido.fromDto(pedidoDto);
    	pedido.setStatus(pedidoDto.status());
    	pedidoRepository.save(pedido);
    	return "Pedido Atualizado com Sucesso";
    }
}
