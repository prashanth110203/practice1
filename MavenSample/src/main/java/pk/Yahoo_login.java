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

public class Yahoo_login {

    public static void main(String[] args) throws InterruptedException {

        String username = "prashanthk110203@yahoo.com";
        String password = "Prashanth@11022003";

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

        driver.get("https://login.yahoo.com/");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")))
                .sendKeys(username);

        Thread.sleep(1500);

        driver.findElement(By.id("login-signin")).click();

      
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-passwd")))
                .sendKeys(password);

        Thread.sleep(1500);

        driver.findElement(By.id("login-signin")).click();

        Thread.sleep(5000);

        driver.get("https://mail.yahoo.com/");

        Thread.sleep(5000);

        System.out.println("Yahoo Mail opened successfully.");
        // Browser remains open
    }
}
