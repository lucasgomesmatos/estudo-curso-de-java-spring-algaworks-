package algafood;

import algafood.domain.models.Cozinha;
import algafood.domain.repositories.CozinhaRepository;
import algafood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static algafood.common.readFile.GetContentFromResource.getContentFromResource;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaNewIT {

    @LocalServerPort
    private int port;

    @Autowired
    DatabaseCleaner databaseCleaner;

    @Autowired
    CozinhaRepository cozinhaRepository;

    private Cozinha cozinhaIndiana;
    private int quantidadeCozinhasCadastradas;
    private static final int COZINHA_ID_INEXISTENTE = 100;


    @Before
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "api/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas() {

        given()
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }

    @Test
    public void deveRetornar2CozinhasQuandoConsultarCozinhas() {
        enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(quantidadeCozinhasCadastradas))
                .body("nome", Matchers.hasItems("Tailandesa", "Indiana"))
        ;
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinhas() {

        var nomeCozinha = getContentFromResource("/mockJson/cozinha-chinesa.json");

        given()
                .body(nomeCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretosQuandoConsultarCozinhaExistente() {

        given()
                .pathParam("cozinhaId", quantidadeCozinhasCadastradas)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaIndiana.getNome()));
    }

    @Test
    public void deveRetornarRespostaEStatus404CorretosQuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        cozinhaIndiana = new Cozinha();
        cozinhaIndiana.setNome("Indiana");
        cozinhaRepository.save(cozinhaIndiana);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }
}
