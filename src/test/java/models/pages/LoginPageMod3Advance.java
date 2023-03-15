package models.pages;

import models.components.LoginFormComponent;
import org.openqa.selenium.WebDriver;

public class LoginPageMod3Advance {

    private final WebDriver driver;

    public LoginPageMod3Advance(WebDriver driver) {
        this.driver = driver;
    }

    public LoginFormComponent loginFormComp(){
        return new LoginFormComponent(driver);
    }
}
