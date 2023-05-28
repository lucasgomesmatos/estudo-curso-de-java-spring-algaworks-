package algafood.domain.service;

import algafood.api.dtos.EstadoDTO;
import algafood.domain.exception.EstadoNaoEncontradoException;
import algafood.domain.models.Estado;
import algafood.domain.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Estado adicionar(EstadoDTO estadoDTO) {
        var estado = new Estado(estadoDTO.getNome());
        return estadoRepository.save(estado);
    }

    public Estado buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {
        buscarPorId(id);
        estadoRepository.deleteById(id);

    }

    @Transactional
    public Estado atualizar(Long id, EstadoDTO estadoDTO) {
        var estado = buscarPorId(id);
        estado.setNome(estadoDTO.getNome());

        return estadoRepository.save(estado);
    }
}
