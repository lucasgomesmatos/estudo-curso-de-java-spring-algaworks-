package algafood.api.controllers;

import algafood.api.dtos.input.ParametrosGrupoDTO;
import algafood.api.dtos.output.GrupoDTO;
import algafood.domain.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping("{grupoId}")
    public ResponseEntity<GrupoDTO> buscar(@PathVariable(name = "grupoId") Long id) {
        return ResponseEntity.ok(grupoService.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> listar() {
        return ResponseEntity.ok(grupoService.listar());
    }

    @PostMapping
    public ResponseEntity<GrupoDTO> adicionar(@RequestBody @Valid ParametrosGrupoDTO parametrosGrupoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoService.adicionar(parametrosGrupoDTO.getNome()));
    }

    @PutMapping("{grupoId}")
    public ResponseEntity<GrupoDTO> atualizar(@PathVariable(name = "grupoId") Long id, @RequestBody @Valid ParametrosGrupoDTO parametrosGrupoDTO) {
        return ResponseEntity.ok(grupoService.atualizar(id, parametrosGrupoDTO.getNome()));
    }

    @DeleteMapping("{grupoId}")
    public ResponseEntity<GrupoDTO> excluir(@PathVariable(name = "grupoId") Long id) {
        grupoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
