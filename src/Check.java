import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;
import org.junit.Test;

public class Check {
    @Test
    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://employsystem.com/kontakt/");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://employsystem.com/kontakt/" );
        driver.manage().window().maximize();

        String myEmail = "check@test.pl";
        String myTelephone = "777777777";
        String myMessage = "Something to write here";

        WebElement email = driver.findElement(By.id("field_contact_email"));
        /* Line below in js was used to find element on page when page is really small like mine when I was testing this so
        I used this method - I used this only once because other testing data was visible after go to first checking window*/
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
        WebElement telephone = driver.findElement(By.id("field_contact_telefon"));
        WebElement message = driver.findElement(By.id("field_kggkvh3"));
        WebElement submit = driver.findElement(By.className("gtm-check"));

        email.sendKeys(myEmail);
        telephone.sendKeys(myTelephone);
        message.sendKeys(myMessage);
        submit.click();

        try {
            String goodMessage = driver.findElement(By.className("frm_message")).getText(); //Jesli znajdziemy dobra wiadomosc jest ok
            System.out.println("Test Completed!");
            driver.quit();
        }catch(Exception e){
            String badMessage = driver.findElement(By.className("frm_error_style")).getText(); // Zla wiadomosc formularz sie nie wyslal
            System.out.println("Form was not send!");
            driver.quit();
        }
        //While you want to check if program was set correct values on field then you need to comment driver.quit() function.

    }

}
