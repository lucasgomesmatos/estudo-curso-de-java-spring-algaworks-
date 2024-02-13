package algafood.api.controllers;

import algafood.api.dtos.input.ParametrosProdutoDTO;
import algafood.api.dtos.output.ProdutoOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    Mapper mapper;

    @GetMapping
    public ResponseEntity<List<ProdutoOutputDTO>> listar(@PathVariable Long restauranteId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.listar(restauranteId));
    }

    @GetMapping("{produtoId}")
    public ResponseEntity<ProdutoOutputDTO> buscarProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.buscar(restauranteId, produtoId));
    }

    @PostMapping
    public ResponseEntity<ProdutoOutputDTO> adicionar(@PathVariable Long restauranteId,
                                                      @Valid @RequestBody ParametrosProdutoDTO parametrosProduto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.adicionar(restauranteId, parametrosProduto));
    }

    @PutMapping("{produtoId}")
    public ResponseEntity<ProdutoOutputDTO> atualizar(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @Valid @RequestBody ParametrosProdutoDTO parametrosProduto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.atualizar(restauranteId, produtoId, parametrosProduto));
    }

}
