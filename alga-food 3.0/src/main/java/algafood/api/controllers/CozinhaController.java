package algafood.api.controllers;

import algafood.api.dtos.CozinhaDTO;
import algafood.domain.models.Cozinha;
import algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cozinha>> listar1() {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Cozinha>> listar2() {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping("{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable(value = "cozinhaId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody CozinhaDTO cozinhaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.adicionar(cozinhaDTO));
    }

}
