package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private By orderHeaderButton = By.className("Button_Button__ra12g");
    private By orderBottomButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver){
        this.driver =driver;
    }

    public void clickOrderHeaderButton(){
        driver.findElement(orderHeaderButton).click();
    }

    public void clickOrderBottomButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderBottomButton));
        driver.findElement(orderBottomButton).click();
    }
}
