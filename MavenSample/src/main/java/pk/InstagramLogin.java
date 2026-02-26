package pk;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InstagramLogin {

    public static void main(String[] args) throws InterruptedException {

        String username = "your_username";
        String password = "your_password";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open Instagram
        driver.get("https://www.instagram.com/accounts/login/");

        Thread.sleep(3000);

        // Step 2: Enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys(username);

        // Step 3: Enter password
        driver.findElement(By.name("password"))
                .sendKeys(password);

        // Step 4: Click Login
        driver.findElement(By.xpath("//button[@type='submit']"))
                .click();

        System.out.println("Login submitted");

        Thread.sleep(5000);

        // Step 5: Click "Not Now" for Save Info (if appears)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Not Now')]"))).click();
            System.out.println("Save info popup handled");
        } catch (Exception e) {
            System.out.println("No Save info popup");
        }

        Thread.sleep(4000);

        // Step 6: Click "Not Now" for Notifications (if appears)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Not Now')]"))).click();
            System.out.println("Notification popup handled");
        } catch (Exception e) {
            System.out.println("No notification popup");
        }

        Thread.sleep(5000);

        System.out.println("Instagram login automation completed");

        // driver.quit();
    }
}
