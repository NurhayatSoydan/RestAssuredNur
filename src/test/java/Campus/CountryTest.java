package Campus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

public class CountryTest {
    RequestSpecification reqSpec;
    Faker randomUreteci = new Faker();
    String commentID = "";
    String rndCountryName = "";
    String rndCountryCode = "";

    @BeforeClass
    public void Setup() {
        baseURI = "https://test.mersys.io/";

        Map<String, String> userCredential = new HashMap<>();
        userCredential.put("username", "turkeyts");
        userCredential.put("password", "TechnoStudy123");
        userCredential.put("rememberMe", "true");

        Cookies cookies =
                given()
                        .body(userCredential)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/auth/login")

                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response().getDetailedCookies();

        reqSpec = new RequestSpecBuilder()
                .addCookies(cookies)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void createCountry() {

        String rndCountryName = randomUreteci.address().country() + randomUreteci.address().countryCode();
        String rndCountryCode = randomUreteci.address().countryCode();

        Map<String, String> newCountry = new HashMap<>();
        newCountry.put("name", rndCountryName);
        newCountry.put("code", rndCountryCode);
        //  newCountry.put("translateName",[])


        given()
                .spec(reqSpec)
                .body(newCountry)
                //  .log().all()
                .when()
                .post("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id")
        ;
    }
    // Aynı countryName ve code gönderildiğinde kayıt yapılmadığını yani
    // createCountryNegative testini yapınız

    @Test(dependsOnMethods ="createCountry")
    public void createCountryNegative() {

          Map<String,String> newCountry=new HashMap<>();
          newCountry.put("name",rndCountryName);
          newCountry.put("code",rndCountryCode);

        given()
                .spec(reqSpec)
                  .body(newCountry)
                //  .log().all()
                .when()
                .post("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(400)
                .body("message",containsString("already"))
        ;
    }


}