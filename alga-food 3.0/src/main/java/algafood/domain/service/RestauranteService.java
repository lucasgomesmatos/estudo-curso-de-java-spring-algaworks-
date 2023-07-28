package algafood.domain.service;

import algafood.api.dtos.output.RestauranteOutputDTO;
import algafood.domain.exception.RestauranteNaoEncontradoException;
import algafood.domain.models.Restaurante;
import algafood.domain.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional
    public Restaurante adicionar(RestauranteOutputDTO restauranteOutputDTO) {
        var cozinha = cozinhaService.buscar(restauranteOutputDTO.getIdCozinha());

        var restaurante = Restaurante.builder()
                .nome(restauranteOutputDTO.getNome())
                .taxaFrete(restauranteOutputDTO.getTaxaFrete())
                .cozinha(cozinha)
                .build();

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscar(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {

        buscarPorId(id);
        restauranteRepository.deleteById(id);

    }

    @Transactional
    public Restaurante atualizar(RestauranteOutputDTO restauranteOutputDTO, Restaurante restaurante) {

        var cozinha = cozinhaService.buscar(restauranteOutputDTO.getIdCozinha());

        restaurante.setNome(restauranteOutputDTO.getNome());
        restaurante.setTaxaFrete(restauranteOutputDTO.getTaxaFrete());
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
}
