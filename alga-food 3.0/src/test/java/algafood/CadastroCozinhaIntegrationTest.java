package algafood;

import algafood.api.dtos.CozinhaDTO;
import algafood.domain.models.Cozinha;
import algafood.domain.repositories.CozinhaRepository;
import algafood.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTest {


	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Test
	public void deveTestarCadastroCozinhaComSucesso() {
		// Cenário
		CozinhaDTO novaCozinha = new CozinhaDTO("Brasileira");

		// Ação
		Cozinha novaCozinhaCadastrada = cozinhaService.adicionar(novaCozinha);

		// Validação
		assertThat(novaCozinhaCadastrada).isNotNull();
		assertThat(novaCozinhaCadastrada.getId()).isNotNull();

		// Verificar se a cozinha foi persistida no banco de dados
		Cozinha cozinhaPersistida = cozinhaRepository.findById(novaCozinhaCadastrada.getId()).orElse(null);
		assertThat(cozinhaPersistida).isNotNull();
		assertThat(cozinhaPersistida.getNome()).isEqualTo(novaCozinha.getNome());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void deveFalharAoCadastrarCozinhaQuandoSemNome() {
		// Cenário
		CozinhaDTO novaCozinha = new CozinhaDTO(null);

		// Ação
		Cozinha novaCozinhaCadastrada = cozinhaService.adicionar(novaCozinha);

		// Validação

	}
}
