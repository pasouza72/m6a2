package com.meli.m6a2.repository;

import com.meli.m6a2.model.Mesa;
import com.meli.m6a2.model.Pedido;
import com.meli.m6a2.model.Prato;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class PedidoRepository {

    private final Map<Long, List<Pedido>> pedidos = new HashMap<>();

    @PostConstruct
    public void init(){
        Mesa mesa = new Mesa(1L);
        List<Prato> pratos = Arrays.asList(
                new Prato(1L, 5, "Coxinha de Frango"),
                new Prato(2L, 8, "Pastel"));

        pedidos.put(1L, Collections.singletonList(new Pedido(1L, mesa, pratos)));
    }

    public List<Pedido> buscaPedidosPorIdMesa(Long id){
        List<Pedido> pedidos = this.pedidos.get(id);

        if(pedidos == null){
            throw new RuntimeException("Pedido não encontrada");
        }

        return pedidos;
    }

    public void criaPedido(Pedido pedido) {
        Long idMesa = pedido.getMesa().getId();
        List<Pedido> pedidos = this.pedidos.get(idMesa);

        if(pedidos != null){
            pedidos.add(pedido);
        }else{
            pedidos = new ArrayList<>();
            pedidos.add(pedido);
        }

        this.pedidos.put(idMesa, pedidos);
    }
}
