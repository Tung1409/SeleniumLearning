package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getChromeDriver(){
        // chi ra duong dan cua chromedriver
        // TODO: innit FireFox
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";
        if (OS.isFamilyMac()){
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/chromedriver.exe";
        }

        if (OS.isFamilyWindows()){
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver.exe";
        }

        if (chromeDriverLocation.isEmpty()){
            throw new IllegalArgumentException("Can't detect OS type");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(chromeOptions);

        // Interval time | 500 mili seconds = 0.5 sec
        // Interval time: khoang thoi gian driver quyet dinh di tim lai element
        // vay thi se gui 5/0.5 = 10 request
        // thuong se de 15s trong implicitlywait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // cai nay se chi ap dung cho find element cho moi lan goi find.element 5s

        return driver;
    }
}
