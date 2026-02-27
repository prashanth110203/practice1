package pk;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceDemoAutomation {

    public static void main(String[] args) {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // Required for Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {

            // Open SauceDemo
            driver.get("https://www.saucedemo.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Wait until inventory page loads
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

            System.out.println("Login successful!");

            // Add first product to cart
            WebElement addToCartBtn = driver.findElement(By.cssSelector(".inventory_item button"));
            addToCartBtn.click();

            System.out.println("Product added to cart.");

            // Click cart icon
            driver.findElement(By.className("shopping_cart_link")).click();

            // Wait until cart page loads
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));

            System.out.println("Navigated to cart page successfully.");

            System.out.println("Test Completed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();  // Always close browser
        }
    }
}