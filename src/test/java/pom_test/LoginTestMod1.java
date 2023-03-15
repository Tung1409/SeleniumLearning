package pom_test;

import driver.DriverFactory;
import models.pages.LoginPageMod1;
import org.openqa.selenium.WebDriver;

public class LoginTestMod1 {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {

            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod1 loginPage = new LoginPageMod1(driver);
            loginPage.username().sendKeys("my name is Tung");
            loginPage.password().sendKeys("123456");
            loginPage.loginBtn().click();

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
