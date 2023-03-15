package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCardType;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private final static By purchaseNumSel = By.cssSelector("#PurchaseOrderNumber");
    private final static By continueBtnSel = By.cssSelector(".button-1.payment-info-next-step-button");
    private final static By creditCardDropdownSel = By.cssSelector("#CreditCardType");
    private final static By cardHolderNameSel = By.cssSelector("#CardholderName");
    private final static By cardNumberSel = By.cssSelector("#CardNumber");
    private final static By cardExpiredMonthDropdownSel = By.cssSelector("#ExpireMonth");
    private final static By cardExpiredYearDropdownSel = By.cssSelector("#ExpireYear");
    private final static By cardCodeSel = By.cssSelector("#CardCode");


    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }


    public void inputPurchaseNum(String number){
        component.findElement(purchaseNumSel).sendKeys(number);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = component.findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }

    public void inputCardName(String name){
        component.findElement(cardHolderNameSel).sendKeys(name);
    }

    public void inputCardNum(String number){
        component.findElement(cardNumberSel).sendKeys(number);
    }

    public void selectExpiredMonth(String month){
        Select select = new Select(component.findElement(cardExpiredMonthDropdownSel));
        select.selectByValue(month);
    }

    public void selectExpiredYear(String year){
        Select select = new Select(component.findElement(cardExpiredYearDropdownSel));
        select.selectByValue(year);
    }

    public void inputCardCode(String code){
        component.findElement(cardCodeSel).sendKeys(code);
    }

    public void selectCardType(CreditCardType creditCardType){
        if (creditCardType == null){
            throw new IllegalArgumentException("[ERR] Credit card type can't be null");
        }
        Select select = new Select(component.findElement(creditCardDropdownSel));
        switch (creditCardType){
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
        }
    }
}
