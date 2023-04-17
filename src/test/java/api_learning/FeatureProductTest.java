package api_learning;

import driver.DriverFactory;
import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import test.BaseTest;
import url.Urls;

import java.util.List;

public class FeatureProductTest extends BaseTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            testFeatureProductHomePage(driver);

        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void testFeatureProductHomePage(WebDriver driver) {
        DriverFactory.getChromeDriver().get(Urls.demoPageUrl);
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> productItemComponents =
                homePage.productGridComponent().productItemComps();

        productItemComponents.forEach(productItemComp ->{
            System.out.println(productItemComp.productTitleElemm().getText());
        });
    }


}
