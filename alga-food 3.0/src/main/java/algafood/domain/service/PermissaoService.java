package algafood.domain.service;

import algafood.domain.exception.PermissaoNaoEncontradoException;
import algafood.domain.models.Permissao;
import algafood.domain.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    private Permissao buscarPorId(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNaoEncontradoException(id));
    }

    public Permissao buscarPermissao(Long id) {
        return buscarPorId(id);
    }
}
