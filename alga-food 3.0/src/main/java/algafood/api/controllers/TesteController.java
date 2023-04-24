package algafood.api.controllers;

import algafood.domain.models.Cozinha;
import algafood.domain.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("/cozinha/por-nome")
    public ResponseEntity<List<Cozinha>> conhizhasPorNome(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaRepository.findByNomeContaining(nome));
    }
}
