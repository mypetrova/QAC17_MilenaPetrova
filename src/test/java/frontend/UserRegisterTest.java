package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class UserRegisterTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() {
        WebElement myAccountButton = driver.findElement(By.cssSelector(".fa-user"));
        myAccountButton.click();
        //dropdown-menu dropdown-menu-right
        List<WebElement> register = driver.findElements(By.cssSelector(".dropdown-menu-right a:nth-of-type(1)"));
        for (int z = 0; z < register.size(); z++) {
            if (register.get(z).getText().equals("Register")) {
                register.get(z).click();
                break;
            }
        }


        driver.findElement(By.id("input-firstname")).sendKeys(random());
        driver.findElement(By.id("input-lastname")).sendKeys(random());
        driver.findElement(By.id("input-email")).sendKeys(random() + "@mail.bg");
        driver.findElement(By.id("input-telephone")).sendKeys("888 123456");
        driver.findElement(By.id("input-password")).sendKeys("parola123");
        driver.findElement(By.id("input-confirm")).sendKeys("parola123");
        driver.findElement(By.cssSelector(".pull-right input:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector(".pull-right input:nth-of-type(2)")).click();

        driver.findElement(By.cssSelector(".btn-primary")).click();


        //list-unstyled
        List<WebElement> li = driver.findElements(By.tagName("li"));

        for (int z = 0; z < li.size(); z++) {
            if(li.get(z).getText().equals("Modify your address book entries")) {
                li.get(z).findElement(By.tagName("a")).click();
                break;
            }
        }

        //pull-right
        List<WebElement> div = driver.findElements(By.className("pull-right"));
        for (WebElement d : div) {
            System.out.println(d.getText());
            if (d.getText().equals("New Address")) {
                d.findElement(By.tagName("a")).click();
            }
        }

        driver.findElement(By.id("input-firstname")).sendKeys(random());
        driver.findElement(By.id("input-lastname")).sendKeys(random());
        driver.findElement(By.id("input-address-1")).sendKeys(random());
        driver.findElement(By.id("input-city")).sendKeys("Sofia");
        driver.findElement(By.id("input-postcode")).sendKeys("1000");
        Select countrySelect = new Select(driver.findElement(By.id("input-country")));
        countrySelect.selectByVisibleText("Bulgaria");

        //input-zone
        Select inputZoneSelect = new Select(driver.findElement(By.id("input-zone")));
        inputZoneSelect.selectByVisibleText("Sofia");

//btn-primary

        List<WebElement> but = driver.findElements(By.className("btn-primary"));
        System.out.println(but.size());
        for (WebElement b : but) {
            System.out.println(b.getText());
            if (b.getTagName().equals("input")) {
                b.click();
            }
        }

        WebElement success = driver.findElement(By.className("alert-success"));
        System.out.println(success.getText());
        Assert.assertEquals(success.getText(), "Your address has been successfully added");
        System.out.println();
    }
    // web source : https://www.baeldung.com/java-random-string
    private String random() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);

        return generatedString;
    }



    @AfterMethod
    public void quit() {
        driver.quit();
    }


}
