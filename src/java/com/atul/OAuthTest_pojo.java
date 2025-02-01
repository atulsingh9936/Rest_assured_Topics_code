package com.atul;

import Pojo.GetCourse;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuthTest_pojo {
    public static void main(String[] args) {


     String response=   given().
                formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
                formParams("grant_type","client_credentials").
                formParams("scope","trust").
                when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath j = new JsonPath(response);
      String accesstoken=  j.getString("access_token");
        System.out.println(accesstoken);


   GetCourse gc=   given().queryParams("access-token",accesstoken).
              when().log().all()
              .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());




    }
}
