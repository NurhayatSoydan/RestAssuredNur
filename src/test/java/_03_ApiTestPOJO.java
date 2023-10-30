import Model.Location;
import Model.Place;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _03_ApiTestPOJO {
    // POJO: JSON nesnesi: Location nesnesine dönüstürmek
    @Test
    public void exractJsonAllPOJO() {
        //ogrenci ogr=new Ogrenci; --gibi mantıgı
        Location locationNesnesi =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().body().as(Location.class)//location kalıbına göre
                //dönen body bilgisi location class kalıbıyla
                ;
        System.out.println("locationNesnesi.getCountry() = " + locationNesnesi.getCountry());
        System.out.println("locationNesnesi.getPlaces() = " + locationNesnesi.getPlaces());
        
        for (Place p: locationNesnesi.getPlaces())
            System.out.println("p = " + p);

      //  JSonaDonustur(locationNesnesi) developher bu şekilde dönüştürmüştü
      //  JSon.Serialise(locationNesnesi) bende tersine deSerialize yaptım
      // yani NESNE yi elde ettim

    }
    

}
