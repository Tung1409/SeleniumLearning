package pom_test;

import driver.DriverFactory;
import models.pages.LoginPageMod2;
import org.openqa.selenium.WebDriver;

public class LoginTestMod2 {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        try {

            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod2 loginPage = new LoginPageMod2(driver);
            loginPage.inputUsername("tung@1029");
            loginPage.inpurPassword("123456");
            loginPage.clickOnLoginBtn();


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
