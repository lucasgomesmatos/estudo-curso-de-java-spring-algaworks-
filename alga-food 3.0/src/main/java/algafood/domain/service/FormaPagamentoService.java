package algafood.domain.service;

import algafood.api.dtos.input.FormaPagamentoInputDTO;
import algafood.api.dtos.output.FormaPagamentoOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.common.MensagensDeException;
import algafood.domain.exception.EntidadeEmUsoException;
import algafood.domain.exception.FormaPagamentoNaoEncontradoException;
import algafood.domain.models.FormaPagamento;
import algafood.domain.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;


    @Autowired
    private Mapper mapper;

    private FormaPagamento buscarPorId(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradoException(id));
    }

    public List<FormaPagamentoOutputDTO> listar() {
        return mapper.mapCollection(formaPagamentoRepository.findAll(), FormaPagamentoOutputDTO.class);
    }

    @Transactional
    public FormaPagamentoOutputDTO adicionar(FormaPagamentoInputDTO formaPagamentoInputDTO) {

        var formaPagamento = FormaPagamento.builder()
                .descricao(formaPagamentoInputDTO.getDescricao())
                .build();

        return mapper.generalMapper(formaPagamentoRepository.save(formaPagamento), FormaPagamentoOutputDTO.class);

    }

    public FormaPagamentoOutputDTO buscar(Long id) {
        return mapper.generalMapper(buscarPorId(id), FormaPagamentoOutputDTO.class);
    }

    public FormaPagamento buscarFormaPagamento(Long id) {
        return buscarPorId(id);
    }

    @Transactional
    public void remover(Long id) {


        try {
            buscarPorId(id);
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MensagensDeException.MENSAGEM_FORMA_PAGAMENTO_EM_USO.getMensagem(), id));
        }
    }

    @Transactional
    public FormaPagamentoOutputDTO atualizar(FormaPagamentoInputDTO formaPagamentoInputDTO, FormaPagamento formaPagamento) {
        formaPagamento.setDescricao(formaPagamentoInputDTO.getDescricao());
        return mapper.generalMapper(formaPagamentoRepository.save(formaPagamento), FormaPagamentoOutputDTO.class);

    }
}
