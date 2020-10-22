package spring.expert.rest.mapper;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import spring.expert.domain.entity.Cliente;
import spring.expert.domain.entity.ItemPedido;
import spring.expert.domain.entity.Pedido;
import spring.expert.domain.entity.Produto;
import spring.expert.rest.dto.InformacaoItemPedidoDTO;
import spring.expert.rest.dto.InformacoesPedidoDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-22T12:09:19-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public InformacoesPedidoDTO entityToDTO(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        InformacoesPedidoDTO informacoesPedidoDTO = new InformacoesPedidoDTO();

        String cpf = pedidoClienteCpf( pedido );
        if ( cpf != null ) {
            informacoesPedidoDTO.setCpf( cpf );
        }
        informacoesPedidoDTO.setCodigo( pedido.getId() );
        String nome = pedidoClienteNome( pedido );
        if ( nome != null ) {
            informacoesPedidoDTO.setNomeCliente( nome );
        }
        informacoesPedidoDTO.setItems( converter( pedido.getItens() ) );
        if ( pedido.getStatus() != null ) {
            informacoesPedidoDTO.setStatus( pedido.getStatus().name() );
        }
        informacoesPedidoDTO.setTotal( pedido.getTotal() );
        if ( pedido.getDataPedido() != null ) {
            informacoesPedidoDTO.setDataPedido( DateTimeFormatter.ISO_LOCAL_DATE.format( pedido.getDataPedido() ) );
        }

        return informacoesPedidoDTO;
    }

    @Override
    public InformacaoItemPedidoDTO entityToDTO(ItemPedido item) {
        if ( item == null ) {
            return null;
        }

        InformacaoItemPedidoDTO informacaoItemPedidoDTO = new InformacaoItemPedidoDTO();

        BigDecimal preco = itemProdutoPreco( item );
        if ( preco != null ) {
            informacaoItemPedidoDTO.setPrecoUnitario( preco );
        }
        String descricao = itemProdutoDescricao( item );
        if ( descricao != null ) {
            informacaoItemPedidoDTO.setDescricaoProduto( descricao );
        }
        informacaoItemPedidoDTO.setQuantidade( item.getQuantidade() );

        return informacaoItemPedidoDTO;
    }

    private String pedidoClienteCpf(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }
        Cliente cliente = pedido.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String cpf = cliente.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }

    private String pedidoClienteNome(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }
        Cliente cliente = pedido.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String nome = cliente.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private BigDecimal itemProdutoPreco(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Produto produto = itemPedido.getProduto();
        if ( produto == null ) {
            return null;
        }
        BigDecimal preco = produto.getPreco();
        if ( preco == null ) {
            return null;
        }
        return preco;
    }

    private String itemProdutoDescricao(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Produto produto = itemPedido.getProduto();
        if ( produto == null ) {
            return null;
        }
        String descricao = produto.getDescricao();
        if ( descricao == null ) {
            return null;
        }
        return descricao;
    }
}
