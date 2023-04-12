package algafood.domain.service;

import algafood.domain.exception.ApiHandlerException;
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

    private Cidade buscarPorId(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new ApiHandlerException("Cozinha não econtrada para o id: " + id));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade adicionar(Cidade cidade) {
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
}
