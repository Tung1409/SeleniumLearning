package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test_flow.global.FooterTestFlow;
import url.Urls;

public class FooterTest{

    @Test(priority = 1, dependsOnMethods = {"testFooterRegisterPage"})
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(Urls.demoPageUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    @Test(priority = 2)
    public void testFooterRegisterPage() {
        String actualResult = "Teo";
        String expectedResult = "Ti";
//        Verifier.verifyEquals(actualResult, expectedResult);

        // Hard assertion: khi ma fail thi nhung cai con lai se khong lam gi ca, giong Exception ma khong trycatch
        Assert.assertEquals(actualResult, expectedResult, "Welcome message is incorrect");
        // bi loi no moi in ra
        Assert.assertTrue(actualResult.equals(expectedResult), "..");
        Assert.assertFalse(actualResult.equals(expectedResult), "..");
        Assert.fail();
    }

    @Test(priority = 3)
    public void testFooterLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 2);
        softAssert.assertEquals(true, true);
        softAssert.assertEquals(2, 3);

        softAssert.assertAll(); // co dong nay thi dong o duoi khong duoc thuc thi, giong Hard Assertion

        System.out.println("hello");

    }

//    @Test(priority = 4)
    public void testFooterHomePage() {
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoPageUrl);
        try {
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComp =
                    homePage.footerComp().informationColumnComp();
            // tu homePage di xuong footer roi toi Column
            CustomerServiceColumnComponent customerServiceColumnComponent =
                    homePage.footerComp().customerServiceColumnComp();

            testFooterColumn(informationColumnComp);
            testFooterColumn(customerServiceColumnComponent);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }

    /* Input username -> input password -> click on login button
    *
    *
    * */

}
