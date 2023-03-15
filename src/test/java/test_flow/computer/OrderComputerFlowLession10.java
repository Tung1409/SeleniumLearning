package test_flow.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlowLession10<T extends ComputerEssentialComponent> {

    // dua type T vao trong class vi type T minh con refer nhieu hon 1 cho
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlowLession10(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public void buildCompSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComponent(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("2.2GHz");
        computerEssentialComp.selectRAMType("4GB");
    }
}
