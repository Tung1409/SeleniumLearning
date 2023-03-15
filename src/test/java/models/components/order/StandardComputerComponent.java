package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    private final static By productAttributeSel = By.cssSelector("select[id^=\"product_attribute\"]");


    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem = component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem = component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }

    private String selectOption (WebElement dropdownElem, String type){
        Select select = new Select(dropdownElem);
        List<WebElement> allOptions = select.getOptions();
        String fullStringOption = null;
        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String optionTextWithOutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithOutSpaces.startsWith(type)) {
                // neu ma cai type nguoi ta dua vao no giong voi cai ki tu bat dau cua optionTextWithOutSpaces
                fullStringOption = currentOptionText;
                break;
            }
        }

        if (fullStringOption == null) {
            throw new RuntimeException("[ERR] The option " + type + " is not existing to select!");
        }
        select.selectByVisibleText(fullStringOption);
        return fullStringOption;
    }
}

