package api_learning;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.management.OperatingSystemMXBean;

public class LaunchBrower {

    public static void main(String[] args) {
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
//        chromeOptions.addArguments("--start-maximize");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get("https://learn.sdetpro.com");
        // driver.get la truy cap vao trang web
        // Debug purpose only
        try{
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }



        driver.quit();
        // driver.close() chi tat window, quit() la dong window va tat section
    }
}
