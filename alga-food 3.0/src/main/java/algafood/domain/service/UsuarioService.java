package algafood.domain.service;

import algafood.api.dtos.input.ParametrosAtualizarSenhaUsuarioDTO;
import algafood.api.dtos.input.ParametrosAtualizarUsuarioDTO;
import algafood.api.dtos.input.ParametrosUsuarioDTO;
import algafood.api.dtos.output.UsuarioDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.SenhaUsuarioNaoCoincidenteException;
import algafood.domain.exception.UsuarioException;
import algafood.domain.exception.UsuarioExistenteException;
import algafood.domain.models.Usuario;
import algafood.domain.repositories.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private Mapper mapper;


    private Usuario buscarPorId(Long id) {
        return usuarioRespository.findById(id)
                .orElseThrow(() -> new UsuarioException(id));
    }

    public Usuario buscarUsuario(Long id) {
        return buscarPorId(id);
    }

    private Usuario salvar(Usuario usuario) {
        usuarioRespository.detach(usuario);
        var usuarioExistente = usuarioRespository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new UsuarioExistenteException(
                    String.format(MensagensDeException.MENSAGEM_USUARIO_EXISTENTE.getMensagem(),
                            usuarioExistente.get().getEmail()));
        }

        return usuarioRespository.save(usuario);

    }

    public UsuarioDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), UsuarioDTO.class);
    }

    public List<UsuarioDTO> listar() {
        return mapper.mapCollection(usuarioRespository.findAll(), UsuarioDTO.class);
    }

    @Transactional
    public UsuarioDTO adicionar(ParametrosUsuarioDTO parametrosUsuario) {

        var usuario = mapper.generalMapper(parametrosUsuario, Usuario.class);
        var usuarioSalvo = salvar(usuario);

        return mapper.generalMapper(usuarioSalvo, UsuarioDTO.class);

    }

    @Transactional
    public UsuarioDTO atualizar(Long id, ParametrosAtualizarUsuarioDTO parametrosUsuario) {

        var usuarioAutal = buscarPorId(id);

        usuarioAutal.setNome(parametrosUsuario.getNome());
        usuarioAutal.setEmail(parametrosUsuario.getEmail());
        var usuarioSalvo = salvar(usuarioAutal);

        return mapper.generalMapper(usuarioSalvo, UsuarioDTO.class);

    }

    @Transactional
    public void alterarSenha(Long id, ParametrosAtualizarSenhaUsuarioDTO parametrosAtualizarSenhaUsuario) {
        var usuarioAutal = buscarPorId(id);

        if (!Objects.equals(usuarioAutal.getSenha(), parametrosAtualizarSenhaUsuario.getSenhaAtual())) {
            throw new SenhaUsuarioNaoCoincidenteException(String.format(MensagensDeException.MENSAGEM_SENHA_USUARIO_NAO_COINCIDENTE.getMensagem()));
        }

        usuarioAutal.setSenha(parametrosAtualizarSenhaUsuario.getNovaSenha());
        usuarioRespository.save(usuarioAutal);
    }

    @Transactional
    public void associarPermissao(Long usuarioId, Long grupoId) {
        var usuario = buscarPorId(usuarioId);
        var grupo = grupoService.buscarGrupo(grupoId);

        usuario.associar(grupo);
    }

    @Transactional
    public void desassociarPermissao(Long usuarioId, Long grupoId) {
        var usuario = buscarPorId(usuarioId);
        var grupo = grupoService.buscarGrupo(grupoId);

        usuario.desassociar(grupo);
    }
}
