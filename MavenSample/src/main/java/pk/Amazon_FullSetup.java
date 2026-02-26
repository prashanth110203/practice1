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

public class Amazon_FullSetup {

    public static void main(String[] args) throws InterruptedException {

        // Chrome options
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // STEP 1: Open Amazon
        driver.get("https://www.amazon.in/");
        Thread.sleep(2000);

        // STEP 2: Add Products
        addProduct(driver, wait, "UPSC book");
        addProduct(driver, wait, "Gaming mouse");

        // STEP 3: Go to Cart
        System.out.println("Opening Cart...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();

        Thread.sleep(3000);

        // STEP 4: Remove one product
        try {

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//input[@value='Delete'])[1]"))).click();

            System.out.println("One product removed");

        } catch (Exception e) {

            System.out.println("Delete button not found");

        }

        Thread.sleep(3000);

        // STEP 5: Proceed to checkout
        wait.until(ExpectedConditions.elementToBeClickable(
                By.name("proceedToRetailCheckout"))).click();

        System.out.println("Proceeding to login page");

        Thread.sleep(4000);

        // STEP 6: Enter Phone Number using FULL XPath
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div/div/span/form/div[1]/input")))
                .sendKeys("7358276924");

        System.out.println("Phone number entered");

        Thread.sleep(2000);

        // Click Continue
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();

        System.out.println("Continue button clicked");

        Thread.sleep(5000);

        System.out.println("Automation Completed Successfully");

        driver.quit();
    }

    // METHOD TO ADD PRODUCT
    public static void addProduct(WebDriver driver, WebDriverWait wait, String product)
            throws InterruptedException {

        System.out.println("Searching for: " + product);

        // Search box
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("twotabsearchtextbox"))).clear();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);

        Thread.sleep(2000);

        driver.findElement(By.id("nav-search-submit-button")).click();

        Thread.sleep(3000);

        // Click first product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@data-component-type='s-search-result']//h2)[1]")))
                .click();

        Thread.sleep(3000);

        // Switch to new tab
        String mainWindow = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {

            if (!tab.equals(mainWindow)) {

                driver.switchTo().window(tab);

                break;
            }
        }

        Thread.sleep(3000);

        // Click Add to Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button"))).click();

        System.out.println(product + " added to cart");

        Thread.sleep(4000);

        // Handle protection popup if exists
        try {

            if (driver.findElements(By.id("attach-close_sideSheet-link")).size() > 0) {

                driver.findElement(By.id("attach-close_sideSheet-link")).click();

                Thread.sleep(2000);

            }

        } catch (Exception e) {

        }

        // Close product tab	
        driver.close();

        driver.switchTo().window(mainWindow);

        Thread.sleep(2000);
    }
}
