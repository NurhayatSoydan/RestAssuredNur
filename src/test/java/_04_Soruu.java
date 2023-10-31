import Model.Location;
import Model.Place;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class _04_Soruu {
    // http://api.zippopotam.us/tr/01000  endpointinden dönen verilerden "Dörtağaç Köyü" ait
    // bilgileri yazdırınız
    @Test
    public void exractJsonAllPOJO() {

        Location locationNesnesi =
                given()
                        .when()
                        .get("http://api.zippopotam.us/tr/01000")
                        .then()
                        .extract().body().as(Location.class);

        for (Place p : locationNesnesi.getPlaces()) {
            if (p.getPlacename().equalsIgnoreCase("Dörtağaç köyü"))

                System.out.println("p = " + p);
        }
    }
}