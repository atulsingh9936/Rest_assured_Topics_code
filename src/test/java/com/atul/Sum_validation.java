package com.atul;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sum_validation {
    @Test
    public void sumofcourses(){
        int sum=0;
        JsonPath js = new JsonPath(Payload.coursePrice());
        int count = js.getInt("courses.size()");
        for(int i=0;i<count;i++){

        int price= js.getInt("courses["+i+"].price");
        int copies= js.getInt("courses["+i+"].copies");
        int amount = price*copies;
            System.out.println(amount);
            sum=sum+amount;


        }
        System.out.println(sum);

      int purchaseamount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseamount);
    }
}
