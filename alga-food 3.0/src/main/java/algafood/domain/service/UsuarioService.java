package algafood.domain.service;

import algafood.api.dtos.input.ParametrosUsuarioDTO;
import algafood.api.dtos.output.UsuarioDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.UsuarioException;
import algafood.domain.exception.UsuarioExistenteException;
import algafood.domain.models.Usuario;
import algafood.domain.repositories.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private Mapper mapper;

    private Usuario buscarPorId(Long id) {
        return usuarioRespository.findById(id)
                .orElseThrow(() -> new UsuarioException(id));
    }

    private Usuario buscarPorEmail(String email) {
        return usuarioRespository.findByEmail(email);
    }

    public UsuarioDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), UsuarioDTO.class);
    }

    public List<UsuarioDTO> listar() {
        return mapper.mapCollection(usuarioRespository.findAll(), UsuarioDTO.class);
    }

    public UsuarioDTO adicionar(ParametrosUsuarioDTO parametrosUsuario) {


        var usuarioExistente = buscarPorEmail(parametrosUsuario.getEmail());

        if (usuarioExistente != null) {
            throw new UsuarioExistenteException(String.format(MensagensDeException.MENSAGEM_USUARIO_EXISTENTE.getMensagem(), usuarioExistente.getEmail()));
        }

        var usuario = Usuario.builder()
                .nome(parametrosUsuario.getNome())
                .email(parametrosUsuario.getEmail())
                .senha(parametrosUsuario.getSenha())
                .build();

        return mapper.generalMapper(usuarioRespository.save(usuario), UsuarioDTO.class);

    }
}
