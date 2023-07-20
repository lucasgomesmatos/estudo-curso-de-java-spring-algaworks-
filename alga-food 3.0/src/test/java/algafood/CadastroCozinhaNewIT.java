package algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaNewIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "api/cozinhas";

        flyway.migrate();
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
    public void deveRetornar4CozinhasQuandoConsultarCozinhas() {
        enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(4))
                .body("nome", Matchers.hasItems("Indiana", "Brasil"))
        ;
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinhas() {
        given()
                .body("{\"nome\": \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then().statusCode(HttpStatus.CREATED.value());
    }
}
