package test_flow.global;

import models.components.global.TopMenuComponent;
import static models.components.global.TopMenuComponent.MainCatItemComp;
import static models.components.global.TopMenuComponent.SubListComp;
import static url.Urls.baseUrl;

import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.footerComp();
        verifyInformationColumn(footerComponent.informationColumnComp());
        verifyCustomerService(footerComponent.customerServiceColumnComp());
        verifyAccountColumn(footerComponent.accountColumnComp());
        verifyFollowUsColumn(footerComponent.followUsColumnComp());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoPageUrl;
        List<String> expectedLinkText = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice",
                "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList(baseUrl + "/sitemap", baseUrl + "/shipping-returns", baseUrl + "/privacy-policy",
                baseUrl + "/conditions-of-use", baseUrl + "/about-us", baseUrl + "/contactus");
        verifyFooterColumn(footerColumnComp, expectedLinkText, expectedHrefs);
    }

    private void verifyCustomerService(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoPageUrl;
        List<String> expectedLinkText = Arrays.asList("Search", "News", "Blog", "Recently viewed products",
                "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList(baseUrl + "/search", baseUrl + "/news",
                baseUrl + "/blog", baseUrl + "/recentlyviewedproducts",
                baseUrl + "/compareproducts", baseUrl + "/newproducts");
        verifyFooterColumn(footerColumnComp, expectedLinkText, expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoPageUrl;
        List<String> expectedLinkText = Arrays.asList("My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHrefs = Arrays.asList(baseUrl + "/customer/info",baseUrl + "/customer/orders",
                baseUrl + "/customer/addresses",baseUrl + "/cart",baseUrl + "/wishlist");
        verifyFooterColumn(footerColumnComp, expectedLinkText, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComp) {
        String baseUrl = Urls.demoPageUrl;
        List<String> expectedLinkText = Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHrefs = Arrays.asList(baseUrl + "http://www.facebook.com/nopCommerce", baseUrl + "http://www.twitter.com/nopCommerce",
                baseUrl + "/news/rss/1", baseUrl + "http://www.youtube.com/user/nopCommerce", baseUrl + "https://plus.google.com/+nopcommerce");
        verifyFooterColumn(footerColumnComp, expectedLinkText, expectedHrefs);
    }

    public void verifyProductCatFooterComp(){
        // Random pickup an item
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<MainCatItemComp> mainCatsElem = topMenuComp.mainCatItemElems();
        if (mainCatsElem.isEmpty()) Assert.fail("[ERR] there is no item on top menu");

        MainCatItemComp randomMainItemElem =
                mainCatsElem.get(new SecureRandom().nextInt(mainCatsElem.size()));
        String randomCatHref = randomMainItemElem.catItemLinkElem().getAttribute("href");

        // Get Sublist
        List<SubListComp> subListComps = randomMainItemElem.subListComps();
        if (subListComps.isEmpty()){
            randomMainItemElem.catItemLinkElem().click();
        } else {
            int randomIndex = new SecureRandom().nextInt(subListComps.size());
            SubListComp randomCatItemComp = subListComps.get(randomIndex);
            randomCatHref = randomCatItemComp.getComponent().getAttribute("href");
            randomCatItemComp.getComponent().click();
        }
        // click xong phai wait cho cai url contain hyperlink

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains(randomCatHref));
        } catch (TimeoutException e){
            Assert.fail("The target page is not matched");
        }


        // Verify footer component
        verifyFooterComponent();

    }

    private void verifyFooterColumn(FooterColumnComponent footerColumnComponent,
                                  List<String> expectedLinkText, List<String> expectedHrefs){
        List<String> actualLinkText = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkText.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }
        if (actualLinkText.isEmpty() || actualHrefs.isEmpty()){
            Assert.fail("[ERR] Text or hyperlink is empty in footer column!!");
        }

        // Verify link text
        Assert.assertEquals(actualLinkText, expectedLinkText, "[ERR] actual link text and expected link tex is different");

        // Verify Hrefs
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] actual hrefs and expected hrefs is different");
    }
}
