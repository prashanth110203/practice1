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

public class Amazon_auto {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");

        options.setExperimentalOption("excludeSwitches",
                java.util.Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            // Open Amazon
            driver.get("https://www.amazon.in/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            addProduct(driver, wait, "UPSC book");
            addProduct(driver, wait, "Gaming Mouse");

            // Go to Cart
            System.out.println("Navigating to cart...");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();

            // Remove first product
            System.out.println("Removing one product...");
            try {
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@value='Delete'])[1]"))).click();
                System.out.println("One product removed successfully.");
            } catch (Exception e) {
                System.out.println("Delete button not found.");
            }

            // Proceed to checkout
            System.out.println("Proceeding to checkout...");
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.name("proceedToRetailCheckout"))).click();

            System.out.println("Reached Checkout/Login page. Automation Complete.");

        } finally {
            driver.quit();
        }
    }

    public static void addProduct(WebDriver driver, WebDriverWait wait, String product) {

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("twotabsearchtextbox"))).clear();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
        driver.findElement(By.id("nav-search-submit-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@data-component-type='s-search-result']//h2)[1]")))
                .click();

        String main = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(main)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-button"))).click();

            if (driver.findElements(By.id("attach-close_sideSheet-link")).size() > 0) {
                driver.findElement(By.id("attach-close_sideSheet-link")).click();
            }

        } catch (Exception e) {
            System.out.println("Add to cart failed for: " + product);
        }

        System.out.println(product + " added");

        driver.close();
        driver.switchTo().window(main);
    }
}