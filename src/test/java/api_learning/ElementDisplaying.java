package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class ElementDisplaying {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();

        Exception e = null;
        try {
            // GET A CHROME SESSION

            // NAVIGATE TO THE TARGET PAGE
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By usernameSel = By.id("usernamemasas");

            // find elements
            WebElement usernameElem = driver.findElement(usernameSel);


        } catch (NoSuchElementException noSuchElementException) {
            e = noSuchElementException;
        }

        if (e == null)
//            Assert.fail("the element abc still display on the page");
        // DEBUG PURPOSE ONLY

        // Khi exception dien ra thi nhung~ cai o duoi exception se khong duoc thuc thi

        // QUIT THE BROWSER SESSION
        driver.quit();

        // Implicit wait, Explicit wait, Fluent wait (it dung)
        // Implicit wait apply cho ca whole driver session when we find element of the page
        //
    }
}
