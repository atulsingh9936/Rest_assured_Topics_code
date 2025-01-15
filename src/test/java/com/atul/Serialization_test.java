package com.atul;

import Pojo.Add_place;
import Pojo.Location;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization_test {
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


        String response=    given().log().all().queryParam("key","qaclcick123").body(p)
                .when().post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }
}
