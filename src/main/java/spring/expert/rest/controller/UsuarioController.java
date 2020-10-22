package spring.expert.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.expert.domain.entity.Usuario;
import spring.expert.exception.SenhaInvalidaException;
import spring.expert.rest.dto.CredenciaisDTO;
import spring.expert.rest.dto.TokenDTO;
import spring.expert.rest.dto.UsuarioDTO;
import spring.expert.rest.mapper.UsuarioMapper;
import spring.expert.security.jwt.JwtService;
import spring.expert.service.impl.UsuarioServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl service;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioMapper.entityToDTO(service.salvar(usuario));
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try {
               Usuario usuario = Usuario.builder()
                        .login(credenciaisDTO.getLogin())
                        .senha(credenciaisDTO.getSenha()).build();
            UserDetails usuarioAutenticado = service.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
