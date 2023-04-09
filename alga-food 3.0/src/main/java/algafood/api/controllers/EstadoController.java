package algafood.api.controllers;

import algafood.domain.models.Estado;
import algafood.domain.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoRepository.findAll());
    }

    //04-12

}
