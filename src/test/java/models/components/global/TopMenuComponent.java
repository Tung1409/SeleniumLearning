package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCatItemComp> mainCatItemElems(){
         return findComponents(MainCatItemComp.class, driver);
    }

    @ComponentCssSelector(".top-menu > li")
    public static class MainCatItemComp extends Component{

        public MainCatItemComp(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public WebElement catItemLinkElem(){
            return component.findElement(By.tagName("a"));
        }

        public List<SubListComp> subListComps(){
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();// goi component la no dang am chi MainCatItemComp
            return findComponents(SubListComp.class, driver);
        }
    }

    @ComponentCssSelector(".sublist li a")
    public static class SubListComp extends Component{

        public SubListComp(WebDriver driver, WebElement component) {
            super(driver, component);
        }


    }
}
