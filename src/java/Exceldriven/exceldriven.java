//package src.java.Exceldriven;
//
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class exceldriven {
//    @Test
//    public void librarytest(){
//        RestAssured.baseURI="https://rahulshettyacademy.com";
//       Response resp = given().header("Content-Type","application/json").body("{\\r\\n\\\"name\\\":\\\"Learn Appium Automation with Java\\\",\\r\\n\\\"isbn\\\":\\\"bcd5454523\\\",\\r\\n\\\"aisle\\\":\\\"29289\\\",\\r\\n\\\"author\\\":\\\"atul\\\"\\r\\n}")
//                .when().post("/Library/Addbook.php").
//                then().assertThat().statusCode(200)
//                .extract().response();
//        System.out.println("Response Status Code: " + resp.getStatusCode());
//        System.out.println("Response Body: " + resp.asString());
//
//
//        JsonPath js = files.Reusable_methods.rawtoJson(String.valueOf(resp));
//        String id = js.get("ID");
//        System.out.println(id);
//
//
//    }
//
//}


package src.java.Exceldriven;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class exceldriven {
    @Test
    public void librarytest() throws IOException {
      Data_driven d = new Data_driven();
      ArrayList data= d.getdata("RestAddbook","Restassured");
        System.out.println("Excel Data: " + data);


        HashMap<String,Object> jsonAsMap= new HashMap<>();
        jsonAsMap.put("name",data.get(1));
        jsonAsMap.put("isbn",data.get(2));
        jsonAsMap.put("aisle",data.get(3));
        jsonAsMap.put("author",data.get(4));
//        HashMap<String,Object>Map2= new HashMap<>();
//        Map2.put("lat","12");
//        Map2.put("long","89");
//        jsonAsMap.put("location",Map2);


        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response resp = given()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .extract()
                .response();

        String responseString = resp.asString();

        JsonPath js = new JsonPath(responseString);
                String id = js.getString("ID");
                System.out.println("Generated Book ID: " + id);
        System.out.println(responseString);

        }
    }


