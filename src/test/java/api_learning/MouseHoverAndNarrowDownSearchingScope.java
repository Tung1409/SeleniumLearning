package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {

    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");


    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate
            driver.get(baseUrl.concat(hoverSlug));

            // Normal finding elements
//            List<WebElement> figcaptionNamesElem = driver.findElements(By.cssSelector(". figcaption h5"));
//            List<WebElement> figcaptionHrefsElem = driver.findElements(By.cssSelector(". figcaption a"));

            // tager parent elenements
            List<WebElement> figuresElem = driver.findElements(figureSel);
            if (figuresElem.isEmpty())
                throw new RuntimeException("There is no target to test");

            // Define Action object
            Actions actions = new Actions(driver);

            for (WebElement figureElem : figuresElem) {
                WebElement profileNameElem = figureElem.findElement(profileNameSel);
                WebElement profileLinkElem = figureElem.findElement(profileLinkSel);

                // BEFORE mouse hover
                System.out.println(profileNameElem.getText() + ":" + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + ":" + profileLinkElem.isDisplayed());

                // Mouse hover
                actions.moveToElement(figureElem).perform();

                // AFTER mouse hover
                System.out.println(profileNameElem.getText() + ":" + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + ":" + profileLinkElem.isDisplayed());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
// cho text nam trong tag <p>,   $('tinymce p')
// co html base, html khong phai base, html document nam trong html parent
// thi phai switch sang iFrame thi moi tuong tac duoc
