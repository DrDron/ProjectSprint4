package pages.FAQ;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FAQElement {
    private WebDriver driver;
    private By headButton;
    public By panel;
    public FAQElement(WebDriver driver, By headButton, By panel){
        this.driver = driver;
        this.headButton = headButton;
        this.panel = panel;
    }
    public void clickHeadButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(headButton));
        driver.findElement(headButton).click();
        ExpectedConditions.elementToBeClickable(panel);
    }
}
