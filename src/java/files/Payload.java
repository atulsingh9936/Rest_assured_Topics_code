package files;


public class Payload {

    public static String Addplace(){

     return "{\n" +
             "  \"location\": {\n" +
             "    \"lat\": -38.383494,\n" +
             "    \"lng\": 33.427362\n" +
             "  },\n" +
             "  \"accuracy\": 50,\n" +
             "  \"name\": \"Atul house\",\n" +
             "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
             "  \"address\": \"29, side layout, cohen 09\",\n" +
             "  \"types\": [\n" +
             "    \"shoe park\",\n" +
             "    \"shop\"\n" +
             "  ],\n" +
             "  \"website\": \"http://google.com\",\n" +
             "  \"language\": \"French-IN\"\n" +
             "}\n";
    }

    public static   String coursePrice(){
            return "{\n" +
                    "\n" +
                    "\"dashboard\": {\n" +
                    "\n" +
                    "\"purchaseAmount\": 940,\n" +
                    "\n" +
                    "\"website\": \"rahulshettyacademy.com\"\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "\"courses\": [\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"Selenium Python\",\n" +
                    "\n" +
                    "\"price\": 50,\n" +
                    "\n" +
                    "\"copies\": 6\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"Cypress\",\n" +
                    "\n" +
                    "\"price\": 40,\n" +
                    "\n" +
                    "\"copies\": 4\n" +
                    "\n" +
                    "},\n" +
                    "\n" +
                    "{\n" +
                    "\n" +
                    "\"title\": \"RPA\",\n" +
                    "\n" +
                    "\"price\": 45,\n" +
                    "\n" +
                    "\"copies\": 10\n" +
                    "\n" +
                    "},\n" +
                    "  {\n" +
                    "\n" +
                    "\"title\": \"Appium\",\n" +
                    "\n" +
                    "\"price\": 3,\n" +
                    "\n" +
                    "\"copies\": 10\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "]\n" +
                    "\n" +
                    "}";
    }

    public static String addbook1(String isbn, String aisle){
      String payload=  "{\n" +
              "  \"name\": \"Learn Appium Automation with Java\",\n" +
              "  \"isbn\": \""+isbn+"\",\n" +
              "  \"aisle\": \""+aisle+"\",\n" +
              "  \"author\": \"John foe\"\n" +
              "}";
              return payload;

    }

    public static String deletebook(String id){
        String poyload ="{\"ID\": \""+id+"\"}";
        return poyload;
    }
}
