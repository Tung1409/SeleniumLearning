package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements  Urls{

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate
            driver.get(baseUrl.concat(iFrameSlug));

            // Locate the iframe
            By iFrameSel = By.cssSelector("[id$='ifr']");
            WebElement iFrameElem = driver.findElement(iFrameSel);

            // Switch to the iframe
            driver.switchTo().frame(iFrameElem);

            // Locate element inside iframe
            WebElement editorInputElem = driver.findElement(By.id("tinymce"));
            editorInputElem.click();
            editorInputElem.clear();
            editorInputElem.sendKeys("This is new text value ...");
            Thread.sleep(1000);
            // tuong tac iframe xong thi nen nhay ra
            // Switch back parent frame
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(1000);

//            driver.findElement(By.cssSelector("tinymce p")).sendKeys("New text");
            // khong tuong tac duoc do co html



        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
// cho text nam trong tag <p>,   $('tinymce p')
// co html base, html khong phai base, html document nam trong html parent
// thi phai switch sang iFrame thi moi tuong tac duoc
