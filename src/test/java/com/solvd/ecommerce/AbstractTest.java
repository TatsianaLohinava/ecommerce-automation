package com.solvd.ecommerce;

import com.solvd.ecommerce.utils.CapabilitiesFactory;
import com.solvd.ecommerce.utils.ConfigReader;

import com.solvd.ecommerce.utils.ScreenCapturer;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private Capabilities capabilities;

    @DataProvider(name = "query")
    public Object[][] createQueryData() {
        return new Object[][]{
                new Object[]{"Ластик"}
        };
    }

    @DataProvider(name = "credentials")
    public Object[][] createCredentialsData() {
        return new Object[][]{
                {"abc@gmail.com", "abcd"}
        };
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void openBrowser(String browser) throws MalformedURLException {
        capabilities = CapabilitiesFactory.createCapability(browser);
        WebDriver driver = new RemoteWebDriver(new URL(ConfigReader.getValue("selenium_url")), capabilities);
        driver.get(ConfigReader.getValue("URL"));
        webDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result) throws IOException {
        String browser = capabilities.getBrowserName();

        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenCapturer.captureScreenshot(getDriver(), result.getName(), browser);
        }
        getDriver().quit();
        webDriver.remove();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

}