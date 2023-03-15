package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements  Urls{

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate
            driver.get(baseUrl.concat(dropdownSlug));

            // Dropdown Locator and elements
            By dropdownSel = By.id("dropdown");
            WebElement dropdownElem = driver.findElement(dropdownSel);

            //Select dropdown
            SelectEx select = new SelectEx(dropdownElem);

            // Select by visible text | select option 1
            select.selectOption1();
            Thread.sleep(1000);

            // Select by index | option 2
            select.selectByIndex(2);
            Thread.sleep(1000);

            // Select by value | option 1
            select.selectByValue("1");
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
// dropdown nam trong tag <select>