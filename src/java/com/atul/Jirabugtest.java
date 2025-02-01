package com.atul;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Jirabugtest {
    public static void main(String[] args) {

        RestAssured.baseURI="https://atulsingh2189.atlassian.net";

     String Createissueresponse =   given().header("Content-Type","application/json").
                header("Authorization","Basic YXR1bC5zaW5naDIxODlAZ21haWwuY29tOkFUQVRUM3hGZkdGMGoyMlNiTDluMlJLZHNCZ21YR2hXbjNXZ29VR0tKMXZvOElTc3pCYUhlVnpXUkRjSXhDamxfdTVITXVYa0lpeEVzZjFYT3dOWXd2SVdxcU1WZktLcTVQSW0wcjJBMWNhVzdZaGN4QlJLY0xnUjBRVS1NT205TWliZDdqa3pBOFFQSlVnOUFDX1NEak5KQXN5US0tS1ZDZE9LdF9xNUswSU5NNmwwd3IxVzhPTT0yNUIyNTM5Qw==").
                body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"AJ\"\n" +
                        "       },\n" +
                        "       \"summary\": \"website are not working- automation -rest assured.\",\n" +
                        "      \n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Task\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}")
                .log().all()
                .post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(Createissueresponse);
      String issueid=  js.getString("id");
        System.out.println(issueid);

        // add attachement

        given().pathParam("key",issueid).
                header("X-Atlassian-Token","no-check").
                header("Authorization","Basic YXR1bC5zaW5naDIxODlAZ21haWwuY29tOkFUQVRUM3hGZkdGMGoyMlNiTDluMlJLZHNCZ21YR2hXbjNXZ29VR0tKMXZvOElTc3pCYUhlVnpXUkRjSXhDamxfdTVITXVYa0lpeEVzZjFYT3dOWXd2SVdxcU1WZktLcTVQSW0wcjJBMWNhVzdZaGN4QlJLY0xnUjBRVS1NT205TWliZDdqa3pBOFFQSlVnOUFDX1NEak5KQXN5US0tS1ZDZE9LdF9xNUswSU5NNmwwd3IxVzhPTT0yNUIyNTM5Qw==").
                multiPart("file",new File("C:\\Users\\Atul\\Downloads\\IMG_0586.PNG")).log().all().
                post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);


    }
}
