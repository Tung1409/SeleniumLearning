package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // GET A CHROME SESSION

            // NAVIGATE TO THE TARGET PAGE
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By usernameSel= By.id("username");
            By passwordSel= By.cssSelector("#password");
            By loginBtnSel= By.cssSelector("[type='submit']");

            // FIND ELEMENTS
            WebElement usernameElem = driver.findElement(usernameSel);
            /*o tren trinh duyet chuot phai chon kiem tra
            de kiem tra element id minh chon co dung khong thi qua tab console
            #('elementid'), xong chuot trai de kiem tra
            co the search "css selector" de doc them
            */
            WebElement passwordElem = driver.findElement(passwordSel);
            // tim nhu username cung duoc
            // o button Login thi attribute class = radius, attribute type = submit
            // doi voi attribute cssSelector thi phai $('[]')
            // neu o trong la dau nhay don ' thi o ngoai la dau nhay kep''
            WebElement loginBtnElem = driver.findElement(loginBtnSel);
            // co 8 loai by can phai nho sau khi xong khoa hoc
            // Id LinkText partialLinkText name tagName xpath className cssSelector

            // GET ATTRIBUTE VALUE
            System.out.println("Login button type: " + loginBtnElem.getAttribute("type"));
            System.out.println("Login button color: " + loginBtnElem.getCssValue("background-color"));

            // INTERACTION
            usernameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();

            // Goback to previous page
            driver.navigate().back();

            // Refresh page
            driver.navigate().refresh();

            // Re-interact with the element again
            usernameElem = driver.findElement(usernameSel);
            passwordElem = driver.findElement(passwordSel);
            loginBtnElem = driver.findElement(loginBtnSel);
            usernameElem.sendKeys("taolaousername");
            passwordElem.sendKeys("12345678");
            loginBtnElem.click(); // ra loi StaleElementReferenceException




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
        // Empplicit wait chi ap dung cho doi tuong cu the
        // vd cho 1 cai no visible (display); cho URL co 1 gia tri nao do,
        // cho page title, cho so luong tab la bao nhieu
    }
}
