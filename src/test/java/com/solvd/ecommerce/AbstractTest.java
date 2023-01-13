package com.solvd.ecommerce;

import com.solvd.ecommerce.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class AbstractTest {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    @DataProvider(name = "query")
    public Object[][] createQueryData() {
        return new Object[][]{
                new Object[]{new String("английский")}
        };
    }

    @DataProvider(name = "credentials")
    public Object[][] createCredentialsData() {
        return new Object[][]{
                {"abc@gmail.com", "abcd"}
        };
    }

    @BeforeTest
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void openBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.get(ConfigReader.getValue("URL"));
        webDriver.set(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.get().close();
        webDriver.remove();
    }

    @AfterTest(alwaysRun = true)
    public void quitWebDriver() {
        WebDriverManager.chromedriver().quit();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

}