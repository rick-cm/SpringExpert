package spring.expert.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.expert.domain.entity.Pedido;
import spring.expert.domain.enums.StatusPedido;
import spring.expert.rest.dto.AtualizacaoStatusPedidoDTO;
import spring.expert.rest.dto.InformacoesPedidoDTO;
import spring.expert.rest.dto.PedidoDTO;
import spring.expert.rest.mapper.PedidoMapper;
import spring.expert.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    @Autowired
    private PedidoMapper pedidoMapper;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody @Valid PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id ){
        return service
                .obterPedidoCompleto(id)
                .map( p -> pedidoMapper.entityToDTO(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

//    private InformacoesPedidoDTO converter(Pedido pedido){
//        return InformacoesPedidoDTO
//                .builder()
//                .codigo(pedido.getId())
//                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//                .cpf(pedido.getCliente().getCpf())
//                .nomeCliente(pedido.getCliente().getNome())
//                .total(pedido.getTotal())
//                .status(pedido.getStatus().name())
//                .items(converter(pedido.getItens()))
//                .build();
//    }

//    @Named("itensPedidoConverter")
//    public List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
//
//        if(CollectionUtils.isEmpty(itens)){
//            return Collections.emptyList();
//        }
//        return itens.stream().map(
//                item -> informacoesItemPedidoMapper.entityToDTO(item)
//        ).collect(Collectors.toList());
//    }

//    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
//        if(CollectionUtils.isEmpty(itens)){
//            return Collections.emptyList();
//        }
//        return itens.stream().map(
//                item -> InformacaoItemPedidoDTO
//                            .builder().descricaoProduto(item.getProduto().getDescricao())
//                            .precoUnitario(item.getProduto().getPreco())
//                            .quantidade(item.getQuantidade())
//                            .build()
//        ).collect(Collectors.toList());
//    }
}
