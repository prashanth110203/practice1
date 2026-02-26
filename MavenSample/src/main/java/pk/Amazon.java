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

public class Amazon {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open Amazon
        driver.get("https://www.amazon.in/");
        Thread.sleep(2000);

        // Add Camera
        addProduct(driver, wait, "Camera");

        // Add UPSC Book
        addProduct(driver, wait, "UPSC book");

        // Go to Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("nav-cart"))).click();

        Thread.sleep(2000);

        // Proceed to checkout (Login page will appear)
        wait.until(ExpectedConditions.elementToBeClickable(
                By.name("proceedToRetailCheckout"))).click();

        Thread.sleep(3000);

        System.out.println("Products added. Login required for checkout.");

        driver.quit();
    }

    public static void addProduct(WebDriver driver, WebDriverWait wait, String product)
            throws InterruptedException {

        // Search product
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("twotabsearchtextbox"))).clear();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);

        Thread.sleep(1500);

        driver.findElement(By.id("nav-search-submit-button")).click();

        Thread.sleep(2000);

        // Click first product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@data-component-type='s-search-result']//h2)[1]")))
                .click();

        Thread.sleep(2000);

        // Switch tab
        String main = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(main)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        Thread.sleep(2000);

        // Add to cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button"))).click();

        Thread.sleep(2000);

        System.out.println(product + " added");

        driver.close();
        driver.switchTo().window(main);

        Thread.sleep(2000);
    }
}

