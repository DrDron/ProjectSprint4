package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    private By secondNameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath(".//input[@placeholder = '* Станция метро']");
    private By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    private By dateField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By rentField = By.className("Dropdown-placeholder");
    private By blackColourField = By.id("black");
    private By greyColourField = By.id("grey");
    private By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");

    private By nextButton = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[text() = 'Далее']");
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private By confirmButton = By.xpath(".//button[text() = 'Да']");
    private By successOrderWindow = By.className("Order_Modal__YZ-d3");
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    public void clickConfirmButton(){
        driver.findElement(confirmButton).click();
    }

    public void setName(String value){
        driver.findElement(nameField).sendKeys(value);
    }
    public void setSecondName(String value){
        driver.findElement(secondNameField).sendKeys(value);
    }
    public void setAddress(String value){
        driver.findElement(addressField).sendKeys(value);
    }
    public void setMetroStation(String value){
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath(".//div[@class = 'Order_Text__2broi' and text() = '" + value + "']")).click();
    }
    public void setPhone(String value){
        driver.findElement(phoneField).sendKeys(value);
    }
    public void setDate(String value){
        driver.findElement(dateField).sendKeys(value);
        driver.findElement(By.className("Order_Buttons__1xGrp")).click();
    }
    public void setRent(String value){
        driver.findElement(rentField).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + value + "']")).click();
    }
    public void setColourBlack(){
       driver.findElement(blackColourField).click();
    }
    public void setColourGrey(){
        driver.findElement(greyColourField).click();
    }
    public void setComment(String value){
        driver.findElement(commentField).sendKeys(value);
    }
    public boolean checkSuccessWindow(){
        return driver.findElement(successOrderWindow).isDisplayed();
    }

    public void fillFirstForm(String name, String secondName, String address, String metroStation, String phone){
        setName(name);
        setSecondName(secondName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhone(phone);
        clickNextButton();
    }
    public void fillSecondForm(String date, String rent, Boolean isBlack, Boolean isGrey, String comment){
        setDate(date);
        setRent(rent);
        if (isBlack) setColourBlack();
        if (isGrey) setColourGrey();
        setComment(comment);
        clickOrderButton();
    }

}
