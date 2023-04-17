package test.computer;

import models.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTestParallel;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_flow.computer.OrderComputerFlowLession11;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTestParallel implements Urls {

    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoPageUrl.concat("/build-your-cheap-own-computer"));
        OrderComputerFlowLession11<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlowLession11<>(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfor(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\test\\java\\test\\computer\\CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }

}
