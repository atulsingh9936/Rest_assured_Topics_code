package src.java.file;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Graphql {
    public static void main(String[] args) {

        //Query
        int characterId = 12102;
    String response=    given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"query($characterId: Int!,$episodeId: Int!){\\n  character(characterId:$characterId){\\n    name\\n    gender\\n    status\\n    id\\n    type\\n  }\\n  location(locationId:17708)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId:$episodeId){\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Rahul\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n    }\\n    \\n  }\\n  episodes(filters:{episode:\\\"hulu\\\"}){\\n    result{\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n  \\n}\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":12623}}").when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
      String characterName=  js.getString("data.character.name");
        Assert.assertEquals(characterName,"baskins Robins1");


        // mutation
        String characterName1= "Baskins Robins";
        String Mutationresponse=   given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodename:String!){\\n  createLocation(location:{name:$locationName,type:\\\"southzone\\\",dimension:\\\"234\\\"}){\\n    id\\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"macho1\\\",status:\\\"demo\\\",species:\\\"llk\\\",gender:\\\"male\\\",image:\\\"sddff\\\",originId:17596,locationId:17596}){\\n    id\\n  }\\n  createEpisode(episode:{name:$episodename,air_date:\\\"1990 june\\\",episode:\\\"prime\\\"}){\\n    id\\n  }\\n  deleteLocations(locationIds:[17600]){\\n    locationsDeleted\\n    \\n  }\\n}\",\"variables\":{\"locationName\":\"Newzealand1\",\"characterName\":\""+characterName1+"\",\"episodename\":\"Manifest2\"}}").when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(Mutationresponse);
        JsonPath js1 = new JsonPath(Mutationresponse);


    }
}
