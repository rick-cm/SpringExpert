package spring.expert.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.expert.domain.entity.Pedido;
import spring.expert.domain.entity.Usuario;
import spring.expert.rest.dto.InformacoesPedidoDTO;
import spring.expert.rest.dto.UsuarioDTO;

@Mapper(componentModel = "spring", uses = {Usuario.class, UsuarioDTO.class})
public interface UsuarioMapper {

    UsuarioMapper MAPPER = Mappers.getMapper( UsuarioMapper.class );

    UsuarioDTO entityToDTO(Usuario usuario);

    Usuario DTOToEntity(UsuarioDTO usuarioDTO);
}
