package algafood.domain.service;

import algafood.api.dtos.RestauranteDTO;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.models.Restaurante;
import algafood.domain.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    private Restaurante buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante não econtrada para o id: " + id));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional
    public Restaurante adicionar(RestauranteDTO restauranteDTO) {
        var cozinha = cozinhaService.buscar(restauranteDTO.getIdCozinha());

        var restaurante = Restaurante.builder()
                .nome(restauranteDTO.getNome())
                .taxaFrete(restauranteDTO.getTaxaFrete())
                .cozinha(cozinha)
                .build();

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {
        try {
            buscarPorId(id);
            restauranteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeNaoEncontradaException("Restaurante não econtrada para o id: " + id);
        }
    }

    @Transactional
    public Restaurante atualizar(Long id, RestauranteDTO restauranteDTO) {
        var restaurante = buscarPorId(id);
        var cozinha = cozinhaService.buscar(restauranteDTO.getIdCozinha());

        restaurante.setNome(restauranteDTO.getNome());
        restaurante.setTaxaFrete(restauranteDTO.getTaxaFrete());
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
}
