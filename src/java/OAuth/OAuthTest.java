package src.java.OAuth;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class OAuthTest {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chrome-win64");
//        WebDriver driver = new ChromeDriver();
//        String password ="Atul9936@";
//        driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fv2%25252Fauth&dsh=S-384827062%3A1738391612053180&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&ddm=1&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAOm0THsbIqrJ1T0owWcjDxlquhMMYwHS5WUjdBkwIfKjXb3rH4n-0f0St3RVTqI7HAuZYDC6h_cD6S33ysNAX4D9RUatznC_7ta9CHKLmFX4zzovfmgdt5JfrwPIeU1er_EP9Ba33tflsHKgIBohrJfmeBXBTnn9TO0VvpQdj_YQfVg3PMWQK93MVg6R7VVz_W_BEH-GsJ6ECxgixMLTIJFARKhADxK7uydyxX5iZuRBT-BuwbRTNtmTb5vhNwj2PcbBkk7Ry2fa5oVK9KkiO-7hUvIFdjBZjiVGdRrT4t4UFSzvmpRnzNkCVJuMn40BZWppqL4lS82PWExp8A4kr6lGlg7QGVPF1YZ8Ibin2M1P5dcojJJuZwKD9Vtgx7_jxtuz4N57vUafrn9s5eeNn96BJmV7cHZ7b_kYlNVaCT8EMaIGFHZvt_nUIWqlC-SX34gut0yy3s2k8UEL3qPWdfsRDOHwA%26flowName%3DGeneralOAuthFlow%26as%3DS-384827062%253A1738391612053180%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxcewqD7B7mjH7yYvjjeYOeq5P5MersC26rDRkC1EelIwtMweEpK9lvx7ZOHOF_zf2i5_dkZUOTj4PeWn2UjnjDUuVdOooOINCO8EXxLZiY21Iz93_Tc");
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("atul.singh2189@gmail.com");
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
      //  String url= driver.getCurrentUrl();
        String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0ASVgi3Kfgc_nmxnN9fYLZpCfvf0KnTjBsh9MOXYRr0VbgCH7iptc_UjhVuz8I-fkTppWMg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=consent";

      String partialcode=  url.split("code=")[1];
     String code= partialcode.split("&scope")[0];
        System.out.println(code);





     String accessTokenResponse=   given().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
      String accessToken=  js.getString("access_token");
        System.out.println(accessTokenResponse);




      String response=  given().queryParam("access_token",accessToken)
                .when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();
      System.out.println(response);
    }
}
