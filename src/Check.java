import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;
import org.junit.Test;

public class Check {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://employsystem.com/kontakt/");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://employsystem.com/kontakt/" );
        driver.manage().window().maximize();



        WebElement email = driver.findElement(By.id("field_contact_email"));
        /* Line below in js was used to find element on page when page is really small like mine when I was testing this so
        I used this method - I used this only once because other testing data was visible after go to first checking window*/
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
        WebElement telephone = driver.findElement(By.id("field_contact_telefon"));
        WebElement message = driver.findElement(By.id("field_kggkvh3"));
        WebElement submit = driver.findElement(By.className("gtm-check"));

        email.sendKeys("check@test.pl");
        telephone.sendKeys("7777777");
        message.sendKeys("something to test");
        submit.click();
        String expectedMessage = "Wiadomość wysłana!";
        try {
            String goodMessage = driver.findElement(By.className("frm_message")).getText();
            System.out.println("Test Completed!");
            driver.quit();
        }catch(Exception e){
            String badMessage = driver.findElement(By.className("frm_error_style")).getText();
            System.out.println("Form was not send!");
            driver.quit();
        }
        //While you want to check if program was set correct values on field then you need to comment driver.quit() function.

    }

}
