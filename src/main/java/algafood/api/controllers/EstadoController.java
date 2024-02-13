package algafood.api.controllers;

import algafood.api.dtos.input.ParametrosEstadoDTO;
import algafood.domain.models.Estado;
import algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estados")
public class EstadoController {


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
    public ResponseEntity<Estado> salvar(@RequestBody ParametrosEstadoDTO parametrosEstadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.adicionar(parametrosEstadoDTO));
    }

    @PutMapping("{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable(value = "estadoId") Long id, @RequestBody ParametrosEstadoDTO parametrosEstadoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.atualizar(id, parametrosEstadoDTO));
    }

    @DeleteMapping("{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "estadoId") Long id) {
        estadoService.remover(id);
        return ResponseEntity.noContent().build();

    }

}
