package algafood.domain.service;

import algafood.api.dtos.CidadeDTO;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.models.Cidade;
import algafood.domain.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    private Cidade buscarPorId(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cidade não econtrada para o id: " + id));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade adicionar(CidadeDTO cidadeDTO) {
        var estado = estadoService.buscar(cidadeDTO.getIdEstado());

        var cidade = Cidade.builder()
                .nome(cidadeDTO.getNome())
                .estado(estado)
                .build();

        return cidadeRepository.save(cidade);
    }

    public Cidade buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {
        buscarPorId(id);
        cidadeRepository.deleteById(id);
    }

    @Transactional
    public Cidade atualizar(Long id, CidadeDTO cidadeDTO) {
        var cidade = buscarPorId(id);
        var estado = estadoService.buscar(cidadeDTO.getIdEstado());

        cidade.setNome(cidadeDTO.getNome());
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }
}
