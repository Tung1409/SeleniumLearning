package test.computer;

import models.components.order.StandardComputerComponent;
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

public class BuyingStandardComputerTest extends BaseTestParallel implements Urls {

    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoPageUrl.concat("/build-your-own-computer"));
        OrderComputerFlowLession11<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlowLession11<>(driver, StandardComputerComponent.class, computerData);

        orderComputerFlow.buildCompSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfor(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\test\\java\\test\\computer\\StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }

}