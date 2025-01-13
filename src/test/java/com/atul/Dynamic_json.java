package com.atul;

import files.Payload;
import files.Reusable_methods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Dynamic_json
{

    @Test(dataProvider = "Booksdata")

    public void addbook(String isbn,String aisle){
       RestAssured.baseURI="http://216.10.245.166";
        String resp=  given().log().all().header("Content-Type","application/json").body(Payload.addbook1(isbn,aisle)).
                when().post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

     JsonPath js= Reusable_methods.rawtoJson(resp);
    String id= js.get("ID");
        System.out.println(id);
        }

        // delete book

//    @Test
//    public void deletebook(String id){
//
//        given().log().all().header("Content-Type","application/json").body(Payload.deletebook(id)).
//                when().post("Library/DeleteBook.php").
//                then().log().all().assertThat().statusCode(200);
//
//    }

    @Test(dataProvider = "Booksdata", dependsOnMethods = "addbook")
    public void deleteBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        // Construct the ID to delete
        String id = isbn + aisle;

        // Delete the book
        String resp = given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"ID\" : \"" + id + "\"\n" +
                        "}")
                .when().post("Library/DeleteBook.php")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = Reusable_methods.rawtoJson(resp);
        String message = js.get("msg");
        System.out.println("Response Message: " + message);
    }



    @DataProvider(name="Booksdata")
    public Object[][] getdata(){
        // array = collections of elements
        //multidimensional array = collections of arrays
        return new Object[][]{{"nxcnj","89894"},{"iuiu","73886"},{"hjgsd","8754488"}};
    }
}
