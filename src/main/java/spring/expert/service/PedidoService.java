package spring.expert.service;

import spring.expert.domain.entity.Pedido;
import spring.expert.domain.enums.StatusPedido;
import spring.expert.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
