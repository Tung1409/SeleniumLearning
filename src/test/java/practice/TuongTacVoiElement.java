package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnable;
import url.Urls;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class TuongTacVoiElement implements Urls {

//    private final static By figureSel = By.cssSelector(".figure");
//    private final static By profileNameSel = By.cssSelector(".figcaption h5");
//    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.openChromeDriver();

        // navigate
        driver.get(baseUrl.concat(dynamicControlSlug));

        try {
            // Define Selector
            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");
            Actions actions = new Actions(driver);

            // Find elements
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement inputFormElem = driver.findElement(inputFormSel);

            // Interact checkbox
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));
            if (!checkboxElem.isSelected()){
                checkboxElem.click();
            }

            // Interact input form
            WebElement inputFormBtnElem = inputFormElem.findElement(By.tagName("button"));
            WebElement inputElem = inputFormElem.findElement(By.tagName("input"));
            if (!inputElem.isEnabled()){
                inputFormBtnElem.click();
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(new WaitForElementEnable(By.cssSelector("#input-example input")));
            inputElem.sendKeys("Tung was here");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
