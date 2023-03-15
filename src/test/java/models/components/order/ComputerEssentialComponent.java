package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {

    private final static By allOptionsSel = By.cssSelector(".option-list input");
    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);
    public void unselectDefaultOptions(){
        List<WebElement> allOptionsElem = component.findElements(allOptionsSel);
        allOptionsElem.forEach(option -> {
            if (option.getAttribute("checked") !=null){
                option.click();
            }
        });
    }

    // cach tim xpath $x("//label[contains(text(), '400 GB')]")[0].click()
    // [0] la tra ve 1 cai list roi lay cai dau tien; click() la an nut

    public String selectHDD(String type){
        return selectComOption(type);
    }

    public String selectOS(String type){
        return selectComOption(type);
    }

    public String selectSoftware(String type){
        return selectComOption(type);
    }

    protected String selectComOption(String type){
        String selectorStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception ignored){}
        if (optionElem != null){
            optionElem.click();
            return optionElem.getText();
        }else {
            throw new RuntimeException("The option " + type + "is not existing to select");
        }
    }

}

