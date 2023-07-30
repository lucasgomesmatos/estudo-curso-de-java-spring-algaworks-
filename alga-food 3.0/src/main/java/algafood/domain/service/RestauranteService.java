package algafood.domain.service;

import algafood.api.dtos.input.RestauranteInputDTO;
import algafood.api.dtos.output.RestauranteOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.exception.RestauranteNaoEncontradoException;
import algafood.domain.models.Cozinha;
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

    @Autowired
    Mapper mapper;

    private Restaurante buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    public List<RestauranteOutputDTO> listar() {
        return mapper.mapCollection(restauranteRepository.findAll(), RestauranteOutputDTO.class);
    }

    @Transactional
    public RestauranteOutputDTO adicionar(RestauranteInputDTO restauranteInputDTO) {
        var cozinhaOutput = cozinhaService.buscar(restauranteInputDTO.getIdCozinha());

        var cozinha = mapper.generalMapper(cozinhaOutput, Cozinha.class);

        var restaurante = Restaurante.builder()
                .nome(restauranteInputDTO.getNome())
                .taxaFrete(restauranteInputDTO.getPrecoFrete())
                .cozinha(cozinha)
                .build();

        return mapper.generalMapper(restauranteRepository.save(restaurante), RestauranteOutputDTO.class);
    }

    public RestauranteOutputDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), RestauranteOutputDTO.class);
    }

    public Restaurante buscarRestaurante(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {

        buscarPorId(id);
        restauranteRepository.deleteById(id);

    }

    @Transactional
    public RestauranteOutputDTO atualizar(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante) {

        var cozinhaOutput = cozinhaService.buscar(restauranteInputDTO.getIdCozinha());
        var cozinha = mapper.generalMapper(cozinhaOutput, Cozinha.class);

        restaurante.setNome(restauranteInputDTO.getNome());
        restaurante.setTaxaFrete(restauranteInputDTO.getPrecoFrete());
        restaurante.setCozinha(cozinha);
        return mapper.generalMapper(restauranteRepository.save(restaurante), RestauranteOutputDTO.class);
    }
}
