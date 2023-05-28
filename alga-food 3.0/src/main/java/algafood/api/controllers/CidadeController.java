package algafood.api.controllers;

import algafood.api.dtos.CidadeDTO;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.EstadoNaoEncontradoException;
import algafood.domain.exception.NegocioException;
import algafood.domain.models.Cidade;
import algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());
    }

    @GetMapping("{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable(value = "cidadeId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody CidadeDTO cidadeDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.adicionar(cidadeDTO));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable(value = "cidadeId") Long id, @RequestBody CidadeDTO cidadeDTO) {

        try {
            var cidade = cidadeService.buscar(id);
            return ResponseEntity.status(HttpStatus.OK).body(cidadeService.atualizar(cidadeDTO, cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("{cidadeId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "cidadeId") Long id) {
        cidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
