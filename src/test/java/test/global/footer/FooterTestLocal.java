package test.global.footer;

import org.testng.annotations.Test;
import test.BaseTest;
import test_flow.global.FooterTestFlow;
import url.Urls;

public class FooterTestLocal extends BaseTest {

    @Test()
    public void testFooterCategoryPage() {
//        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComp();
    }
}
