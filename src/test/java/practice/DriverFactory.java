package practice;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    protected static WebDriver openChromeDriver(){
        String currentProjectlocation = System.getProperty("user.dir");
        String chromeDriverlocation = "";

        if (OS.isFamilyMac()){
            chromeDriverlocation = currentProjectlocation + "/src/test/resources/drivers/chromedriver2.exe";
        }

        if (OS.isFamilyWindows()){
            chromeDriverlocation = currentProjectlocation + "\\src\\test\\resources\\drivers\\chromedriver2.exe";
        }

        if (chromeDriverlocation.isEmpty()){
            throw new  IllegalArgumentException("Can't detect OS type");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverlocation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
