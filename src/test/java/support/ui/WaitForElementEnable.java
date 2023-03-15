package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForElementEnable implements ExpectedCondition<Boolean> {

    private By selector;

    public WaitForElementEnable(By selector) {
        this.selector = selector;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return driver.findElement(selector).isEnabled();
    }

    // nang cap hon, loi~ gi` thi` in ra cho tui

    @Override
    public String toString() {
        return "Tab number are more than 2";
    }
}
