package algafood.api.controllers;

import algafood.api.dtos.EstadoDTO;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.models.Estado;
import algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estados")
public class EstadoController {

    private static final String MENSAGEM_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.listar());
    }

    @GetMapping("{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable(value = "estadoId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.adicionar(estadoDTO));
    }

    @PutMapping("{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable(value = "estadoId") Long id, @RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.atualizar(id, estadoDTO));
    }

    @DeleteMapping("{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "estadoId") Long id) {

        try {
            estadoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MENSAGEM_ESTADO_EM_USO, id));
        }

    }

}
