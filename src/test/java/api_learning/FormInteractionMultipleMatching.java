package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteractionMultipleMatching {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // GET A CHROME SESSION

            // NAVIGATE TO THE TARGET PAGE
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By loginInputFieldsSel = By.tagName("input");

            // do selector match 2 thang ma find 1 element thi no se tuong tac thang dau tien

            // Interaction
//            driver.findElement(loginInputFieldsSel).sendKeys("teo@sth.com");
            List<WebElement> loginFormFieldsElems = driver.findElements(loginInputFieldsSel);
            // neu findElement khong co s, neu co loi thi no se ra No Such Element
            // neu findElemets co s, thi khong ra no suck element ma no se tra ve 1 list rong~

            // CAU HOI PHONG VAN: Lam sao de cho 1 doi tuong ma tui biet no khong hien thi tren day
            // minh se dung trycatch, qua file class ElementDisplaying
            // cach 2 minh dung findElements

            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            if (!loginFormFieldsElems.isEmpty()) {
                loginFormFieldsElems.get(USERNAME_INDEX).sendKeys("teo@sth.com");
                loginFormFieldsElems.get(PASSWORD_INDEX).sendKeys("123456");
            } else {
                // Assert.fail("...");
            }

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
