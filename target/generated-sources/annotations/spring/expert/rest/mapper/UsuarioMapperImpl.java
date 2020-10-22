package spring.expert.rest.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import spring.expert.domain.entity.Usuario;
import spring.expert.rest.dto.UsuarioDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-22T12:09:19-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO entityToDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setLogin( usuario.getLogin() );
        usuarioDTO.setAdmin( String.valueOf( usuario.isAdmin() ) );

        return usuarioDTO;
    }

    @Override
    public Usuario DTOToEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setLogin( usuarioDTO.getLogin() );
        if ( usuarioDTO.getAdmin() != null ) {
            usuario.setAdmin( Boolean.parseBoolean( usuarioDTO.getAdmin() ) );
        }

        return usuario;
    }
}
