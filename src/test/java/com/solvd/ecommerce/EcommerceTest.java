package com.solvd.ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EcommerceTest {

    private WebDriver webDriver;

    @BeforeTest
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void verifySearchResultTest() {
        String query = "Maped";
        webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        HomePage homePage = new HomePage(webDriver);
        ResultPage resultPage = new ResultPage(webDriver);
        homePage.searchInputEnter(query);
        homePage.searchButtonClick();
        Assert.assertNotEquals(resultPage.getResultListSize(), 0);
        resultPage.printItemData();
    }

    @AfterTest
    public void quitWebDriver() {
        webDriver.quit();
    }
}