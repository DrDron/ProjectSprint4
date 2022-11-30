package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.Test;
import pages.MainPage;
import pages.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrder {
    private WebDriver driver;
    private final Boolean isOrderFromHeader;
    private final String name;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rent;
    private final Boolean isBlack;
    private final Boolean isGrey;
    private final String comment;

    public CreateOrder(Boolean isOrderFromHeader, String name, String secondName, String address, String metroStation, String phone, String date, String rent, Boolean isBlack, Boolean isGrey, String comment){
        this.isOrderFromHeader = isOrderFromHeader;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.isBlack = isBlack;
        this.isGrey = isGrey;
        this.comment = comment;
        }
    @Parameterized.Parameters
    public static Object[][] getDataForOrder() {
        return new Object[][] {
                {true,"Андрей","Андреев","Воронеж", "Черкизовская","88005553535","01.11.2022","сутки", true, false, "Test комментарий"},
                {false,"Сергей","Сергеев","Москва", "ВДНХ","88005553535","01.11.2022","сутки", false, false, ""},
        };
    }
    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();

    }
    @Test
    public void createOrderTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        if(isOrderFromHeader)
            mainPage.clickOrderHeaderButton();
            else
            mainPage.clickOrderBottomButton();
        orderPage.fillFirstForm(name,secondName,address,metroStation,phone);
        orderPage.fillSecondForm(date, rent, isBlack, isGrey, comment);
        orderPage.clickConfirmButton();
        assertTrue("Окно успешного заказа не открылось.", orderPage.checkSuccessWindow());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
