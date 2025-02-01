package com.atul;

import Pojo.Login_request;
import Pojo.Login_response;
import Pojo.Orders;
import Pojo.orderdetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class E_Commerce_Api_test {
    public static void main(String[] args) {
        // login

     RequestSpecification req=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        Login_request loginRequest = new Login_request();
        loginRequest.setUserEmail("atul.singh2189@gmail.com");
        loginRequest.setUserPassword("Atul9936@");

      RequestSpecification reqlogin=  given().log().all().spec(req).body(loginRequest);
     Login_response loginResponse = reqlogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(Login_response.class);
        System.out.println(loginResponse.getToken());
      String token=  loginResponse.getToken();
        System.out.println(loginResponse.getUserId());
       String userid = loginResponse.getUserId();


        // Add Product
      RequestSpecification addproductBasereq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
              addHeader("Authorization",token).build();
        String filename = "C:/Users/Atul/OneDrive/Desktop/Screenshot_69.png";

     RequestSpecification reqAddProduct= given().log().all().spec(addproductBasereq).param("productName","Laptop")

              .param("productAddedBy",userid)
              .param("productCategory","fashion")
              .param("productSubCategory","shirts")
              .param("productPrice","11500")
              .param("productDescription","Addias Originals")
              .param("productFor","women")

              .multiPart("productImage",new File(filename));
     String addproductResponse=reqAddProduct.when().post("/api/ecom/product/add-product")
             .then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(addproductResponse);
        String productId=  js.getString("productId");
        System.out.println(productId);


        // Create order
        RequestSpecification createorderBasereq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).
                addHeader("Authorization",token).build();
        orderdetails orderdetail = new orderdetails();
        orderdetail.setCountry("India");
        orderdetail.setProductOrderedId(productId);

        List<orderdetails> orderdetailsList = new ArrayList<orderdetails>();
        orderdetailsList.add(orderdetail);

        Orders orders = new Orders();
        orders.setOrders(orderdetailsList);


      RequestSpecification createorderreq=  given().log().all().spec(createorderBasereq).body(orders);
     String responseAddorder= createorderreq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddorder);

        // delete product

        RequestSpecification DeleteProductBasereq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).
                addHeader("Authorization",token).build();

      RequestSpecification DeleteProductreq=  given().log().all().spec(DeleteProductBasereq).pathParam("productId",productId);
      String Deleteproductresponse=DeleteProductreq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
              extract().response().asString();

      JsonPath js1 = new JsonPath(Deleteproductresponse);
        Assert.assertEquals("Product Deleted Successfully", js1.get("message"));







    }
}
