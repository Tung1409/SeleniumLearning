package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnable;
import url.Urls;

import java.time.Duration;

public class DynamicControl implements Urls {
    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            //navigate
            driver.get(baseUrl.concat(dynamicControlSlug));

            // Define parent locator | 2 form | checkbox form + input form
            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");

            // Checkbox form interaction
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));
            if (!checkboxElem.isSelected()) checkboxElem.click();
            Thread.sleep(1000);


            // Input form interaction
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputFieldElem = inputFormElem.findElement(By.tagName("input"));
            WebElement inputBtnElem = inputFormElem.findElement(By.tagName("button"));
            if (!inputFieldElem.isEnabled()) inputBtnElem.click();

            // click button xong cho` loading invi thi minh moi input
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            wait.until(new WaitForElementEnable(By.cssSelector("#input-example input")));

            inputFieldElem.sendKeys("Tui ten Teo, hello cac ban");

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
// dropdown nam trong tag <select>