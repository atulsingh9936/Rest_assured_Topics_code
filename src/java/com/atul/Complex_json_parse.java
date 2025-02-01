package com.atul;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Complex_json_parse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.coursePrice());
        // Print number of courses returned by Api
      int count =  js.getInt("courses.size()");
        System.out.println(count);
        // print purchase amount
       int totalamount= js.getInt("dashboard.purchaseAmount");
        System.out.println(totalamount);

        // print title of first course
      String Firstcoursetitle=  js.get("courses[0].title");
        System.out.println(Firstcoursetitle);

        // print all courses title with their respective prices
        for(int i=0;i<count;i++){
            String coursetitles  = js.get("courses["+i+"].title");
            System.out.println(  js.get("courses["+i+"].price").toString());
            System.out.println(coursetitles);
        }

        // Print no. of copies sold by RPA Course

        System.out.println("Print no. of copies sold by RPA Course");

        for(int i=0;i<count;i++){
            String coursetitles  = js.get("courses["+i+"].title");
            if(coursetitles.equalsIgnoreCase("RPA")){

             int copies=   js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;

            }

        }
    }
}
