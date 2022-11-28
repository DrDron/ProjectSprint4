package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;

import pages.FAQ.FAQElement;
import static org.junit.Assert.assertEquals;

public class CheckAccordionPanelValues {
    private WebDriver driver;
    private int listSize = 8;
    private FAQElement[] faqElements = new FAQElement[listSize];
    String[] expectedValues = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void checkAccordionPanelValues(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        WebElement element = driver.findElement(By.className("Home_FourPart__1uthg"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        for(int i = 0; i < listSize; i++){
            faqElements[i] = new FAQElement(driver, By.xpath(".//div[@id = 'accordion__heading-" + i + "']"), By.xpath(".//div[@id = 'accordion__panel-" + i + "']/p"));
            faqElements[i].clickHeadButton();
            assertEquals("Ошибка в тексте элемента № " + i,expectedValues[i],driver.findElement(faqElements[i].panel).getAttribute("innerHTML"));
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
