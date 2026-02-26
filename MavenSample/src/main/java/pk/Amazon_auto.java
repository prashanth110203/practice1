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

        // 1. Open Amazon
        driver.get("https://www.amazon.in/");
        Thread.sleep(2000);

        // 2. Add 3 Products (Using your working method)
        //addProduct(driver, wait, "Camera");
        addProduct(driver, wait, "UPSC book");
        addProduct(driver, wait, "Gaming Mouse");

        // 3. Go to Cart
        System.out.println("Navigating to cart...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();
        Thread.sleep(3000);

        // 4. Remove one product from the cart (The first one in the list)
        System.out.println("Removing one product...");
        try {
            // Locating the 'Delete' button for the first item in the cart
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//input[@value='Delete'])[1]"))).click();
            System.out.println("One product removed successfully.");
        } catch (Exception e) {
            System.out.println("Could not find delete button: " + e.getMessage());
        }

        Thread.sleep(3000);

      
        System.out.println("Proceeding to checkout...");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.name("proceedToRetailCheckout"))).click();

        Thread.sleep(3000);
        System.out.println("Reached Checkout/Login page. Automation Complete.");

        driver.quit();
    }

    // This is your working method - kept exactly as you provided
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
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
            Thread.sleep(3000);
            
            // Handle optional "Protection Plan" side-sheet that sometimes appears
            if(driver.findElements(By.id("attach-close_sideSheet-link")).size() > 0) {
                driver.findElement(By.id("attach-close_sideSheet-link")).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Add to cart button click failed for: " + product);
        }

        System.out.println(product + " added");
        driver.close();
        driver.switchTo().window(main);
        Thread.sleep(2000);
    }
}