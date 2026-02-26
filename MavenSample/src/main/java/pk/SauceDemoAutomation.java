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

public class SauceDemoAutomation {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-password-generation");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1500);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                .sendKeys("standard_user");
        Thread.sleep(1500);

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");
        Thread.sleep(1500);

        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();
        Thread.sleep(1500);

        // Add second product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-bike-light"))).click();
        Thread.sleep(1500);

        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("checkout"))).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")))
                .sendKeys("Prashanth");
        Thread.sleep(1500);

        driver.findElement(By.id("last-name")).sendKeys("Kathi");
        Thread.sleep(1500);

        driver.findElement(By.id("postal-code")).sendKeys("600001");
        Thread.sleep(1500);

        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);

        System.out.println("Order placed successfully!");

        driver.quit();
    }
}

