package algafood.api.controllers;

import algafood.domain.exception.ApiHandlerException;
import algafood.domain.models.Cozinha;
import algafood.domain.models.Restaurante;
import algafood.domain.repositories.CozinhaRepository;
import algafood.domain.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public ResponseEntity<List<Cozinha>> conhizhasPorNome(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaRepository.findByNomeContaining(nome));
    }

    @GetMapping("/cozinhas/por-nome/unica")
    public ResponseEntity<Cozinha> conhizhasPorNomeUnico(@RequestParam String nome) {

        var cozinha = cozinhaRepository.findByNome(nome).orElseThrow(() -> new ApiHandlerException("Cozinha não encontrada"));
        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
    }

    @GetMapping("/restaurantes/taxaFrete")
    public ResponseEntity<List<Restaurante>> restaurantePorTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {

        return ResponseEntity.status(HttpStatus.OK).body(restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal));
    }

    @GetMapping("/restaurantes/por-nome")
    public ResponseEntity<List<Restaurante>> restaurantePoNome(@RequestParam String nome, @RequestParam Long cozinhaId) {

        return ResponseEntity.status(HttpStatus.OK).body(restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId));
    }
}
