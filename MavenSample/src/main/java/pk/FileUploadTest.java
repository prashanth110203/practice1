package pk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/upload");

        Thread.sleep(3000);

        WebElement upload = driver.findElement(By.id("file-upload"));

        upload.sendKeys("C:\\Users\\PRASHANTH\\Downloads\\1770017323436-SDET- Daily Assignment- Day 19 Q1.docx");

        Thread.sleep(3000);

        driver.findElement(By.id("file-submit")).click();

        Thread.sleep(4000);

        String message = driver.findElement(By.tagName("h3")).getText();

        if(message.equals("File Uploaded!")) {
            System.out.println("Upload Successful");
        } else {
            System.out.println("Upload Failed");
        }

        Thread.sleep(3000);

        driver.quit();
    }
}
