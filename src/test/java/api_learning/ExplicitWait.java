package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import support.ui.WaitUntilSth;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements  Urls{

    public static void main(String[] args) {

        // Explicit wait chi ap dung cho doi tuong cu the
        // vd cho 1 cai no visible (display); cho URL co 1 gia tri nao do,
        // cho page title, cho so luong tab la bao nhieu
        // neu cho khong ra thi se bao loi TimeoutException

        // Get a Chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            // Navigate
            driver.get(baseUrl.concat(loginSlug));

            By taoLaoSel = By.cssSelector("#taolao");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(taoLaoSel));
            // neu cho khong ra thi se bao loi TimeoutException

//            wait.until(ExpectedConditions.visibilityOf(driver.findElement(taoLaoSel)));
            // neu cho khong ra thi se bao loi No such element
            // nguyen nhan do dung driver.findElement, no se dung implicitwait truoc khi dung explicitwait

            // windows/tabs ---> waitUntil tabs > 1
            // trong truong hop ExpectionConditions khong provide cho minh cai option do
            // thi minh co the customize wait.until ExpectedConditions
            wait.until(new WaitMoreThanOneTab());
            wait.until(new WaitUntilSth(By.cssSelector("#teo")));
        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
// dropdown nam trong tag <select>