package com.atul;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {

        //  validate if add place api is working as expected

        // given- all input detail ,
        // when - submit the api(resource and http request)
        // then - Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
      String response=  given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.Addplace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();


        System.out.println(response);
        JsonPath js = new JsonPath(response);// for parsing json
       String PlaceId= js.getString("place_id");
        System.out.println(PlaceId);

             // update place
        String newAddress="70 winter walk, USA";


        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\"place_id\": \""+PlaceId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json")
                        .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

                 // get place

               String getplace=  given().log().all().queryParam("key","qaclick123").queryParam("place_id",PlaceId)
                         .when().get("maps/api/place/get/json")
                         .then().assertThat().log().all().statusCode(200).extract().response().asString();
               JsonPath j1 = new JsonPath(getplace);
             String actualaddress1=  j1.getString("address");
             System.out.println(actualaddress1);
        Assert.assertEquals(actualaddress1,newAddress);



        // Add place  -> update place with new address -> get place to validate if new address is present in  response



    }
}
