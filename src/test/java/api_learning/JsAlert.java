package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;

public class JsAlert implements Urls {

    private final static By jsAlertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By resultSel = By.id("result");


    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate
            driver.get(baseUrl.concat(jsAlertSlug));

            WebElement triggerJsAlertBtnElem ;
            Alert alert;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            // JS_ALERT | ok
            triggerJsAlertBtnElem =driver.findElement(jsAlertSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            handleAlert(alert, true);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            // JS_CONFIRM | cancel
            triggerJsAlertBtnElem =driver.findElement(jsAlertConfirmSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            handleAlert(alert, false);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            // JS_PROMPT
            triggerJsAlertBtnElem =driver.findElement(jsAlertPromptSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            handleAlert(alert, "My name is Tung");
            System.out.println("Result: " + driver.findElement(resultSel).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public static void handleAlert(Alert alert, boolean isAccepting){
        System.out.println("Alert Content: " + alert.getText());
        if (isAccepting) alert.accept();
        else alert.dismiss();
    }

    public static void handleAlert(Alert alert, String contenttoEnter){
        System.out.println("Alert Content: " + alert.getText());
        alert.sendKeys(contenttoEnter);
        alert.accept();
        alert.dismiss();
    }
}
// cho text nam trong tag <p>,   $('tinymce p')
// co html base, html khong phai base, html document nam trong html parent
// thi phai switch sang iFrame thi moi tuong tac duoc
