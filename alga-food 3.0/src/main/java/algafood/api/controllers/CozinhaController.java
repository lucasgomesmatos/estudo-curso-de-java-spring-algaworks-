package algafood.api.controllers;

import algafood.api.dtos.input.CozinhaInputDTO;
import algafood.api.dtos.output.CozinhaOutputDTO;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<List<CozinhaOutputDTO>> listar1() {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<CozinhaOutputDTO>> listar2() {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping("{cozinhaId}")
    public ResponseEntity<CozinhaOutputDTO> buscar(@PathVariable(value = "cozinhaId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CozinhaOutputDTO> salvar(@RequestBody @Valid CozinhaInputDTO cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.adicionar(cozinha));
    }

    @PutMapping("{cozinhaId}")
    public ResponseEntity<CozinhaOutputDTO> atualizar(@PathVariable(value = "cozinhaId") Long id, @RequestBody CozinhaInputDTO cozinhaInputDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.atualizar(id, cozinhaInputDTO));
    }

    @DeleteMapping("{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "cozinhaId") Long id) {


        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MensagensDeException.MENSAGEM_COZINHA_EM_USO.getMensagem(), id));
        }
    }
}
