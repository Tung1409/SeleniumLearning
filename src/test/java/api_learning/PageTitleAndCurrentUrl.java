package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class PageTitleAndCurrentUrl {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            // GET A CHROME SESSION

            // NAVIGATE TO THE TARGET PAGE
            driver.get("https://the-internet.herokuapp.com/login");

            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());

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
