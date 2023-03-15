package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementDisplayingCach2 {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        driver.get("https://the-internet.herokuapp.com/login");

        List<WebElement> elementList = driver.findElements(By.tagName("taolao"));
        if (!elementList.isEmpty())
//            Assert.fail("reason.....");
        driver.quit();

        // Implicit wait, Explicit wait, Fluent wait (it dung)
        // Implicit wait apply cho ca whole driver session when we find element of the page
        //
    }
}
