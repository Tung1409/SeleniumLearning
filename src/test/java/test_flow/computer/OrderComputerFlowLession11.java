package test_flow.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.PaymentInformationComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutOptionPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlowLession11<T extends ComputerEssentialComponent> {

    // dua type T vao trong class vi type T minh con refer nhieu hon 1 cho
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;
    private UserDataObject defaultCheckOutUser;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;


    public OrderComputerFlowLession11(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComponent(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        String hddFullStr = computerEssentialComp.selectHDD(computerData.gethdd());
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);

        double osAdditionalPrice = 0; // tai vi thang co thang khong nen se cho no = 0

        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.selectOS(computerData.getOs());
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }
        String softwareFullStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);

        // Calculate item price
        double basePrice = computerEssentialComp.productPrice();
        double allAddionalPrice = processorAdditionalPrice + ramAdditionalPrice +
                hddAdditionalPrice + osAdditionalPrice + softwareAdditionalPrice;
        totalItemPrice = (basePrice + allAddionalPrice) * quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddToCart();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        // (.*?) la lay tat ca cai gi trong cap ngoac vuong
        // \\[\\] la khong lay ngoac vuong
        Matcher matcher = pattern.matcher(itemStr);
        // itemStr la cai string nguoi ta dua vao
        // matcher la lay ra
        // matcher.group(1) la lay thang match dau tien
        // tai khong biet la no giam hay tang gia nen loai ca - va +
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("-+", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.cartItemRowComponents();
        if (cartItemRowComps.isEmpty()) {
            Assert.fail("there is no iteam displayed in the shopping cart!");
        }

        double currentSubtotal = 0;
        double currentTotalUnitPrice = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubtotal = currentSubtotal + cartItemRowComp.subtotal();
            currentTotalUnitPrice = currentTotalUnitPrice + (cartItemRowComp.unitPrice() * cartItemRowComp.quantity());

        }

        Assert.assertEquals(currentSubtotal, currentTotalUnitPrice,
                "[ERR] Shopping cart's subtotal is incorrect");
        TotalComponent totalComponent = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
        }

        Assert.assertEquals(checkoutSubTotal, currentSubtotal, "[ERR] ...");
        Assert.assertEquals(checkoutTotal, checkoutSubTotal + checkoutOtherFeesTotal, "[ERR] ...");
    }

    public void agreeTOSAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckOutBtn();
        new CheckOutOptionPage(driver).checkOutAsGuest();

    }

    public void inputBillingAddress() {
        String defaultCheckoutUserJSONLoc = "\\src\\test\\java\\test_data\\DefaultCheckoutUser.json";
        defaultCheckOutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDataObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComponent = checkOutPage.billingAddressComponent();
        billingAddressComponent.selectInputNewAddress();
        billingAddressComponent.inputFirstname(defaultCheckOutUser.getFirstname());
        billingAddressComponent.inputLastName(defaultCheckOutUser.getLastname());
        billingAddressComponent.inputEmail(defaultCheckOutUser.getEmail());
        billingAddressComponent.selectCountry(defaultCheckOutUser.getCountry());
        billingAddressComponent.selectState(defaultCheckOutUser.getState());
        billingAddressComponent.inputCity(defaultCheckOutUser.getCity());
        billingAddressComponent.inputAdd1(defaultCheckOutUser.getAdd1());
        billingAddressComponent.inputZIPCode(defaultCheckOutUser.getZipcode());
        billingAddressComponent.inputPhoneNo(defaultCheckOutUser.getPhoneNum());
        billingAddressComponent.clickOnContinueBtn();
    }

    public void inputShippingAddress() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.shippingAddressComponent().clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shippingMethod = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethod.get(new SecureRandom().nextInt(shippingMethod.size()));
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComponent = checkOutPage.shippingMethodComponent();
        shippingMethodComponent.selectShippingMethod(randomShippingMethod);
        shippingMethodComponent.clickOnContinueButton();

    }

    public void selectPaymentMethod() {
        // neu nguoi ta goi selectPaymentMethod ma khong dua vao cai gi thi mac dinh chon COD
        this.paymentMethod = PaymentMethod.COD;
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method can't be null!");
        }
        this.paymentMethod = paymentMethod;

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkOutPage.paymentMethodComponent();
        switch (paymentMethod){
            case CHECK_MONEY_ORDER:
                paymentMethodComponent.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComponent.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComponent.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComponent.selectCODMethod();
        }
        paymentMethodComponent.clickOnContinueBtn();
    }

    public void inputPaymentInfor(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentInformationComponent paymentInformationComponent = checkOutPage.paymentInformationComponent();

        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)){
            // this can be dynamic
            paymentInformationComponent.inputPurchaseNum("123");
        }
        if (this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)){
            paymentInformationComponent.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckOutUser.getFirstname();
            String cardHolderLastName = this.defaultCheckOutUser.getLastname();
            paymentInformationComponent.inputCardName(cardHolderFirstName + " " + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" :
                    "6011000990139424";
            paymentInformationComponent.inputCardNum(cardNumber);
            // Select current month and next year
            Calendar calendar = new GregorianCalendar();
            paymentInformationComponent.selectExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            paymentInformationComponent.selectExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInformationComponent.inputCardCode("123");
            paymentInformationComponent.clickOnContinueBtn();
        } else if (this.paymentMethod.equals(PaymentMethod.COD)) {
            // TODO add verification
        } else {
            // TODO verify Check MOney order
        }
    }

    public void confirmOrder() {
        // TODO add verification methods
        new CheckOutPage(driver).confirmOrderComponent().clickOnContinueBtn();
        try {
            Thread.sleep(3000);
        } catch (Exception ignore){}
    }
}
