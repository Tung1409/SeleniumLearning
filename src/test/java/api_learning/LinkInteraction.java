package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkInteraction {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // GET A CHROME SESSION

            // NAVIGATE TO THE TARGET PAGE
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
//            By poweredByLinkTextSel= By.linkText("Elemental Selenium");
            By poweredByLinkTextSel= By.partialLinkText("Elemental ");
            //partialLinkText la lay 1 phan thoi


            // FIND ELEMENTS
            WebElement powerByLinkTextElem = driver.findElement(poweredByLinkTextSel);


            // INTERACTION
            powerByLinkTextElem.click();

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // DEBUG PURPOSE ONLY

        // Khi exception dien ra thi nhung~ cai o duoi exception se khong duoc thuc thi

        // QUIT THE BROWSER SESSION
        driver.quit();

        // Implicit wait, Explicit wait, Fluent wait (it dung)
        // Implicit wait apply cho ca whole driver session when we find element of the page
        //
    }
}
