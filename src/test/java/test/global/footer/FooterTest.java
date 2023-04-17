package test.global.footer;

import org.testng.annotations.Test;
import test.BaseTest;
import test_flow.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    // khoi tao Webdriver driver nam o BaseTest roi
    @Test()
    public void testFooterCategoryPage() {
//        WebDriver driver = getDriver();
        driver.get(Urls.demoPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComp();
        // finally la fail hay pass no cung thuc hien cai o trong finally

    }

    @Test()
    public void testFooterRegisterPage() {

    }

    @Test()
    public void testFooterLoginPage() {


    }

    @Test()
    public void testFooterHomePage() {

    }

}
