package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMod2 {

    private final WebDriver driver;
    private final static By usernameSel = By.id("username");
    private final static By passwordSel = By.cssSelector("#password");
    private final static By loginBtnSel = By.cssSelector("[type='submit']");

    public void inputUsername(String usernameTxt){
        driver.findElement(usernameSel).sendKeys(usernameTxt);
    }

    public void inpurPassword(String passwordTxt){
        driver.findElement(passwordSel).sendKeys(passwordTxt);
    }

    public void clickOnLoginBtn(){
        driver.findElement(loginBtnSel).click();
    }

    public LoginPageMod2(WebDriver driver) {
        this.driver = driver;
    }

    // neu nhu minh can dung nhieu hon 1 muc dich thi minh lam nhu the nay
//    public WebElement loginBtnElem(){
//        return driver.findElement(loginBtnSel);
//    }

}
