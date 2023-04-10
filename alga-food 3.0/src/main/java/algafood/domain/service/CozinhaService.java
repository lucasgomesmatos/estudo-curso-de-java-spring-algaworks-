package algafood.domain.service;

import algafood.api.dtos.CozinhaDTO;
import algafood.domain.exception.ApiRequestException;
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
                .orElseThrow(() -> new ApiRequestException("Cozinha nao encontado para o id: " + id));
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional
    public Cozinha adicionar(CozinhaDTO cozinhaDto) {
        var cozinha = new Cozinha(cozinhaDto.getNome());

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

    public Cozinha atualizar(Long id, CozinhaDTO cozinhaDTO) {
        var cozinha = buscarPorId(id);
        BeanUtils.copyProperties(cozinhaDTO, cozinha, "id");
        return cozinhaRepository.save(cozinha);
    }
}
