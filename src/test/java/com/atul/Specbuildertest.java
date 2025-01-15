package com.atul;

import Pojo.Add_place;
import Pojo.Location;
import io.restassured.RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Specbuildertest {
    public static void main(String[] args) {
       RestAssured.baseURI="https://rahulshettyacademy.com";

        Add_place p = new Add_place();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName("Frontline house");
        List<String> my_list = new ArrayList<String>();
        my_list.add("shoe park");
        my_list.add("shop");

        p.setTypes(my_list);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);



     RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
     ResponseSpecification respec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

     RequestSpecification res=  given().spec(req).body(p);

     Response response  =  res.when().post("/maps/api/place/add/json").
             then().spec(respec).extract().response();

         String Responsestring =response.asString();
        System.out.println(Responsestring);
    }
}
