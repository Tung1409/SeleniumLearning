package pom_test;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMod3Advance;
import org.openqa.selenium.WebDriver;

public class LoginTestMod3 {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {

            driver.get("https://the-internet.herokuapp.com/login");
            LoginPageMod3Advance loginPage = new LoginPageMod3Advance(driver);
            LoginFormComponent loginFormComponent = loginPage.loginFormComp();
            loginFormComponent.inputUsername("duytung");
            loginFormComponent.inpurPassword("123456");
            loginFormComponent.clickOnLoginBtn();

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
