import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {
    @Test
    public void test1() {
        given()
                //hazırlık işlemleri kodları
                .when()
                //endpoint(url), metodu verip gönderiliyor
                .then()
        //assertion, test, data işlemleri
        ;
    }

    @Test
    public void statusCodeTest() {
        given()
                //hazırlık kısmı boş
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()//dönen body json data,log().All():gidip gelen  herşey
                .statusCode(200)//test kısmı olduğundan assertıon status code 200 mü
        ;
    }

    @Test
    public void contentTypeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()//dönen body json data,log().All():gidip gelen  herşey
                .statusCode(200)//test kısmı olduğundan assertıon status code 200 mü
                .contentType(ContentType.JSON) // dönen datanın tipi JSON mı
        ;
    }

    @Test
    public void test3() {
        given()
                //hazırlık kısmı boş
                .when() //send butonu gibi
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().all()//dönen body json data,log().All():gidip gelen  herşey
        ;
    }

    @Test
    public void checkCountryInResponseBody() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200) //assertion
                .body("country", equalTo("United States")) // assertion
        // body nin country değişkeni "United States" eşit mi
        // Request sonrası dönen Response verinin içinde United States varmı diye verileri dışarı çıkarmadan Assertion yapıyoruz
        //Bunun için org.hamcrest kütüphanesini POM.XML eklememiz gerekli.

        ;

    }

    // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
    // place dizisinin ilk elemanının state değerinin  "California"
    // olduğunu doğrulayınız
    @Test
    public void Soru() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200) //assertion
                .body("places[0].state", equalTo("California")) // assertion
        ;
    }
    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız
        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
               // .log().body()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))
                .statusCode(200) //assertion
        ;
    }
}