package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class JSExcutor implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            //navigate
            driver.get(baseUrl.concat(floatingMenu));

            // Scroll to button
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Debug purpose only
            Thread.sleep(2000);

            // Scroll to top
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

