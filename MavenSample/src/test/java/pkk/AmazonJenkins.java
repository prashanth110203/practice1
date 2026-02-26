package pkk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonJenkins {

    @Test
    public void openAmazon() {

        // Automatically downloads correct chromedriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");   // IMPORTANT for Jenkins
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.amazon.in");
        System.out.println("Amazon opened successfully in Jenkins");

        driver.quit();
    }
}