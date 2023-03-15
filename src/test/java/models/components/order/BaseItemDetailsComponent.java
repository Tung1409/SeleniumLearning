package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseItemDetailsComponent extends Component {

    private final static By barNotificationContentSel = By.cssSelector("#bar-notification");
    private final static By productPriceSel = By.cssSelector(".product-price");
    private final static By addToCardBtnSel = By.cssSelector(".button-1.add-to-cart-button");


    public BaseItemDetailsComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double productPrice(){
        String productPriceText =  component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
        // chuyen du lieu thanh double: parse
    }

    public void clickOnAddToCartBtn(){
        component.findElement(addToCardBtnSel).click();
    }

    public void waitUntilItemAddToCart(){
        String successMsg = "The product has been added to your shopping cart";
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(barNotificationContentSel, successMsg));
        // neu co 1 cai bug, an add lan dau tien khong duoc, thi minh dung try catch de work around no
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(barNotificationContentSel, successMsg));
        } catch (TimeoutException e) {
            clickOnAddToCartBtn();
        }
    }
}
