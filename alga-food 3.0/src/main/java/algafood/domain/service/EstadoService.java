package algafood.domain.service;

import algafood.api.dtos.input.EstadoInputDTO;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.EstadoNaoEncontradoException;
import algafood.domain.models.Estado;
import algafood.domain.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    private Estado buscarPorId(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Transactional
    public Estado adicionar(EstadoInputDTO estadoInputDTO) {
        var estado = new Estado(estadoInputDTO.getNome());
        return estadoRepository.save(estado);
    }

    public Estado buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {
        try {
            buscarPorId(id);
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MensagensDeException.MENSAGEM_ESTADO_EM_USO.getMensagem(), id));
        }
    }

    @Transactional
    public Estado atualizar(Long id, EstadoInputDTO estadoInputDTO) {
        var estado = buscarPorId(id);
        estado.setNome(estadoInputDTO.getNome());

        return estadoRepository.save(estado);
    }
}
