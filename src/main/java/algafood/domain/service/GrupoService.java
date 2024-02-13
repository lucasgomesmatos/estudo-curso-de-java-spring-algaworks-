package algafood.domain.service;

import algafood.api.dtos.output.GrupoDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.GrupoException;
import algafood.domain.models.Grupo;
import algafood.domain.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private Mapper mapper;


    private Grupo buscarPorId(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoException(id));
    }

    public GrupoDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), GrupoDTO.class);
    }

    public Grupo buscarGrupo(Long id) {
        return buscarPorId(id);
    }


    public List<GrupoDTO> listar() {
        return mapper.mapCollection(grupoRepository.findAll(), GrupoDTO.class);
    }

    @Transactional
    public GrupoDTO adicionar(String nome) {

        var grupo = Grupo.builder().nome(nome).build();
        return mapper.generalMapper(grupoRepository.save(grupo), GrupoDTO.class);
    }

    @Transactional
    public GrupoDTO atualizar(Long id, String nome) {
        var grupo = buscarPorId(id);
        grupo.setNome(nome);

        return mapper.generalMapper(grupoRepository.save(grupo), GrupoDTO.class);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            buscarPorId(id);
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MensagensDeException.MENSAGEM_FORMA_GRUPO.getMensagem(), id));
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        var grupo = buscarPorId(grupoId);
        var permissao = permissaoService.buscarPermissao(permissaoId);

        grupo.associar(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        var grupo = buscarPorId(grupoId);
        var permissao = permissaoService.buscarPermissao(permissaoId);

        grupo.desassociar(permissao);
    }
}
