package pk;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLogin {

    public static void main(String[] args) throws InterruptedException {

        String username = "therockstar.prashanth2@gmail.com";
        String password = "Prashanth@11";

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open Gmail login page
        driver.get("https://accounts.google.com/");

        Thread.sleep(2000);

        // Step 2: Enter Email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")))
                .sendKeys(username);

        Thread.sleep(1500);

        driver.findElement(By.id("identifierNext")).click();

        // Step 3: Enter Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")))
                .sendKeys(password);

        Thread.sleep(1500);

        driver.findElement(By.id("passwordNext")).click();

        // Step 4: Open Gmail inbox
        wait.until(ExpectedConditions.urlContains("myaccount"));

        driver.get("https://mail.google.com/");

        Thread.sleep(5000);

        System.out.println("Gmail opened successfully.");

        // keep browser open
        // driver.quit();
    }
}
