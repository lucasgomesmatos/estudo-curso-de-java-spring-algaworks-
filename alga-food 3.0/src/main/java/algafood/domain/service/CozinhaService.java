package algafood.domain.service;

import algafood.api.dtos.input.CozinhaInputDTO;
import algafood.domain.exception.CozinhaNaoEncontradoException;
import algafood.domain.models.Cozinha;
import algafood.domain.repositories.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    private Cozinha buscarPorId(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(id));
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional
    public Cozinha adicionar(CozinhaInputDTO cozinhaInputDto) {
        var cozinha = new Cozinha(cozinhaInputDto.getNome());

        return cozinhaRepository.save(cozinha);
    }

    public Cozinha buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {
        buscarPorId(id);
        cozinhaRepository.deleteById(id);

    }

    @Transactional
    public Cozinha atualizar(Long id, CozinhaInputDTO cozinhaInputDTO) {
        var cozinha = buscarPorId(id);
        BeanUtils.copyProperties(cozinhaInputDTO, cozinha, "id");
        return cozinhaRepository.save(cozinha);
    }
}
