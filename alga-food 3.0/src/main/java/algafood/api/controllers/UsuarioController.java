package algafood.api.controllers;

import algafood.api.dtos.input.ParametrosUsuarioDTO;
import algafood.api.dtos.output.UsuarioDTO;
import algafood.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable(name = "usuarioId") Long id) {
        return ResponseEntity.ok(usuarioService.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid ParametrosUsuarioDTO parametrosUsuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.adicionar(parametrosUsuarioDTO));
    }
}
