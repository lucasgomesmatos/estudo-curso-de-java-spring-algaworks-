package algafood.api.controllers;

import algafood.api.dtos.input.ParametrosFormaPagamentoDTO;
import algafood.api.dtos.output.FormaPagamentoDTO;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/pagamentos")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoService.listar());
    }

    @GetMapping("{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable(value = "formaPagamentoId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> salvar(@RequestBody ParametrosFormaPagamentoDTO parametrosFormaPagamentoDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoService.adicionar(parametrosFormaPagamentoDTO));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> atualizar(@PathVariable(value = "formaPagamentoId") Long id, @RequestBody ParametrosFormaPagamentoDTO parametrosFormaPagamentoDTO) {



        var formaPagamento = formaPagamentoService.buscarFormaPagamento(id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoService.atualizar(parametrosFormaPagamentoDTO, formaPagamento));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("{formaPagamentoId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "formaPagamentoId") Long id) {
        formaPagamentoService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
