package algafood.domain.service;

import algafood.api.dtos.input.ParametrosProdutoDTO;
import algafood.api.dtos.output.ProdutoOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.exception.ProdutoNaoEncontradoException;
import algafood.domain.models.Produto;
import algafood.domain.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private Mapper mapper;

    private Produto buscarPorId(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    public ProdutoOutputDTO buscar(Long restauranteId, Long produtoId) {
        return mapper.generalMapper(buscarPorId(restauranteId, produtoId), ProdutoOutputDTO.class);
    }

    public List<ProdutoOutputDTO> listar(Long restauranteId) {

        var restaurante = restauranteService.buscarRestaurante(restauranteId);
        var produtos = produtoRepository.findByRestaurante(restaurante);

        return mapper.mapCollection(produtos, ProdutoOutputDTO.class);
    }

    private Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public ProdutoOutputDTO adicionar(Long restauranteId, ParametrosProdutoDTO parametrosProduto) {

        var restaurante = restauranteService.buscarRestaurante(restauranteId);

        var produto = Produto.builder()
                .nome(parametrosProduto.getNome())
                .descricao(parametrosProduto.getDescricao())
                .preco(parametrosProduto.getPreco())
                .restaurante(restaurante)
                .ativo(parametrosProduto.getAtivo())
                .build();

        return mapper.generalMapper(salvar(produto), ProdutoOutputDTO.class);
    }

    public ProdutoOutputDTO atualizar(Long restauranteId, Long produtoId, ParametrosProdutoDTO parametrosProduto) {

        var produtoAtual = buscarPorId(restauranteId, produtoId);

        BeanUtils.copyProperties(parametrosProduto, produtoAtual, "id");

        return mapper.generalMapper(salvar(produtoAtual), ProdutoOutputDTO.class);
    }
}


