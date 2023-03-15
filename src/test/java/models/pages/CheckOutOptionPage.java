package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOptionPage extends BasePage {

    private static final By checkOutAsGuestBtnSel = By.cssSelector(".checkout-as-guest-button");

    public CheckOutOptionPage(WebDriver driver) {
        super(driver);
    }

    public void checkOutAsGuest(){
        component.findElement(checkOutAsGuestBtnSel).click();
    }
}
