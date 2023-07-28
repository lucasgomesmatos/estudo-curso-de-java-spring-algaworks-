package algafood.domain.service;

import algafood.api.dtos.input.CidadeInputDTO;
import algafood.domain.exception.CidadeNaoEncontradoException;
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
                .orElseThrow(() -> new CidadeNaoEncontradoException(id));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade adicionar(CidadeInputDTO cidadeInputDTO) {
        var estado = estadoService.buscar(cidadeInputDTO.getIdEstado());


        var cidade = Cidade.builder()
                .nome(cidadeInputDTO.getNome())
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
    public Cidade atualizar(CidadeInputDTO cidadeInputDTO, Long id) {
        var cidade = buscar(id);
        var estado = estadoService.buscar(cidadeInputDTO.getIdEstado());

        cidade.setNome(cidadeInputDTO.getNome());
        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);

    }
}
