package pk;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoAutomation {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            // Open SauceDemo
            driver.get("https://www.saucedemo.com/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Wait for products page
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_list")));

            System.out.println("Login successful!");

            // Add first product to cart
            driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
            System.out.println("Product added to cart.");

            // Go to cart
            driver.findElement(By.className("shopping_cart_link")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_list")));

            System.out.println("Navigated to cart.");

            // Checkout
            driver.findElement(By.id("checkout")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));

            // Fill details
            driver.findElement(By.id("first-name")).sendKeys("Prashanth");
            driver.findElement(By.id("last-name")).sendKeys("Kathi");
            driver.findElement(By.id("postal-code")).sendKeys("600001");

            driver.findElement(By.id("continue")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));

            driver.findElement(By.id("finish")).click();

            System.out.println("Order completed successfully!");

        } finally {
            driver.quit();
        }
    }
}