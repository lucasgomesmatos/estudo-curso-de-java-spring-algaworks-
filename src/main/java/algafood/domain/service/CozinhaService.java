package algafood.domain.service;

import algafood.api.dtos.input.CozinhaInputDTO;
import algafood.api.dtos.output.CozinhaOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.CozinhaNaoEncontradoException;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.models.Cozinha;
import algafood.domain.repositories.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Autowired
    Mapper mapper;

    private Cozinha buscarPorId(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(id));
    }

    public List<CozinhaOutputDTO> listar() {
        return mapper.mapCollection(cozinhaRepository.findAll(), CozinhaOutputDTO.class);
    }

    @Transactional
    public CozinhaOutputDTO adicionar(CozinhaInputDTO cozinhaInputDto) {
        var cozinha = new Cozinha(cozinhaInputDto.getNome());
        return mapper.generalMapper(cozinhaRepository.save(cozinha), CozinhaOutputDTO.class);
    }

    public CozinhaOutputDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), CozinhaOutputDTO.class);
    }

    @Transactional
    public void remover(Long id) {
        try {
            buscarPorId(id);
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MensagensDeException.MENSAGEM_COZINHA_EM_USO.getMensagem(), id));
        }
    }

    @Transactional
    public CozinhaOutputDTO atualizar(Long id, CozinhaInputDTO cozinhaInputDTO) {
        var cozinha = buscarPorId(id);
        BeanUtils.copyProperties(cozinhaInputDTO, cozinha, "id");
        return mapper.generalMapper(cozinhaRepository.save(cozinha), CozinhaOutputDTO.class);
    }
}
