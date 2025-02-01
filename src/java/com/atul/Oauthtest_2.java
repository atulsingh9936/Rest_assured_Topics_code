package com.atul;

import static io.restassured.RestAssured.given;


import Pojo.GetCourse;
import Pojo.WebAutomation;
import Pojo.api;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Oauthtest_2 {



    public static void main(String[] args) throws InterruptedException {

        String[] CourseTitles = {"Selenium Webdriver Java","Cypress","Protractor",};

// TODO Auto-generated method stub

        String response =

                given()



                        .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                        .formParams("grant_type", "client_credentials")

                        .formParams("scope", "trust")





                        .when().log().all()

                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println(accessToken);

        GetCourse gc=    given()

                .queryParams("access_token", accessToken)

                .when()

                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

                        .as(GetCourse.class);

        System.out.println(  gc.getLinkedIn());
        System.out.println(gc.getInstructor());

        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
        System.out.println( gc.getCourses().getWebAutomation().get(0).getCourseTitle());


     List<api> apiCourses = gc.getCourses().getApi();
     for(int i=0;i<apiCourses.size();i++){
         if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
             System.out.println(apiCourses.get(i).getPrice());

         }

     }
        ArrayList<String> a = new ArrayList<String>();


     List<WebAutomation> webAutomation  =  gc.getCourses().getWebAutomation();
     for( int i=0;i<webAutomation.size();i++){
       a.add(  webAutomation.get(i).getCourseTitle());
     }

      List<String> expectedlist = Arrays.asList(CourseTitles);
        Assert.assertTrue(a.equals(expectedlist));









    }




}
