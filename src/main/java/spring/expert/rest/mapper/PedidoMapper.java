package spring.expert.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import spring.expert.domain.entity.ItemPedido;
import spring.expert.domain.entity.Pedido;
import spring.expert.rest.dto.InformacaoItemPedidoDTO;
import spring.expert.rest.dto.InformacoesPedidoDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {Pedido.class,InformacoesPedidoDTO.class})
public interface PedidoMapper {

    PedidoMapper MAPPER = Mappers.getMapper( PedidoMapper.class );

    @Mapping(source = "cliente.nome", target = "nomeCliente")
    @Mapping(source = "cliente.cpf", target = "cpf")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "id", target = "codigo")
    @Mapping(source = "itens", target = "items",qualifiedByName ="itensPedidoConverter" )
    InformacoesPedidoDTO entityToDTO(Pedido pedido);

    @Mapping(source = "produto.descricao", target = "descricaoProduto")
    @Mapping(source = "produto.preco", target = "precoUnitario")
    InformacaoItemPedidoDTO entityToDTO(ItemPedido item);

    @Named("itensPedidoConverter")
    default List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){

        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> entityToDTO(item)
        ).collect(Collectors.toList());
    }

}
