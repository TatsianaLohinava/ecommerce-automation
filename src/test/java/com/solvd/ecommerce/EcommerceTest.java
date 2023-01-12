package com.solvd.ecommerce;

import com.solvd.ecommerce.page.Header;
import com.solvd.ecommerce.page.HomePage;
import com.solvd.ecommerce.page.LoginPage;
import com.solvd.ecommerce.page.ResultPage;
import com.solvd.ecommerce.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EcommerceTest {

    @BeforeTest
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void verifySearchResultTest() {
        String query = "Maped";
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        ResultPage resultPage = new ResultPage(webDriver);
        Header header = new Header(webDriver);
        header.searchInputEnter(query);
        header.searchButtonClick();
        Assert.assertNotEquals(resultPage.getResultListSize(), 0);
        resultPage.printItemData();
        webDriver.close();
    }

    @Test
    public void checkBackgroundChangeTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        HomePage homePage = new HomePage(webDriver);
        Actions action = new Actions(webDriver);
        SoftAssert sa = new SoftAssert();
        List<WebElement> list = homePage.getSidebarMenuList();
        for (WebElement el : list) {
            action.moveToElement(el).perform();
            sa.assertTrue(el.getAttribute("class").contains("main-nav__list__li_wnav_active"));
        }
        sa.assertAll();
        webDriver.close();
    }

    @Test
    public void checkPinButtonTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        HomePage homePage = new HomePage(webDriver);
        List<String> classList = homePage.unpinCategoryMenu();

        classList.forEach(el -> sa.assertTrue(el.contains("mpgs-nopin")));
        sa.assertAll();
        webDriver.close();
    }

    @Test
    public void checkInputResetTest() {
        String query = "Maped";
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        Header header = new Header(webDriver);
        header.searchInputEnter(query);
        header.clickResetButton();
        Assert.assertTrue(header.isSearchInputEmpty());
        webDriver.close();
    }

    @Test
    public void checkSearchFilterResultTest() {
        String query = "английский";
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        ResultPage resultPage = new ResultPage(webDriver);
        Header header = new Header(webDriver);
        SoftAssert sa = new SoftAssert();

        header.searchInputEnter(query);
        header.searchButtonClick();
        resultPage.checkNewFilterBox();
        List<Boolean> isNewList = resultPage.checkNewItemsList();
        isNewList.forEach(sa::assertTrue);
        sa.assertAll();
        webDriver.close();
    }

    @Test
    public void checkInvalidLoginDataTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(ConfigReader.getValue("URL"));
        Header header = new Header(webDriver);
        LoginPage loginPage = header.clickMainLoginButton();
        loginPage.clickLoginWithEmailButton();
        loginPage.fillEmailInput("abc@gmail.com");
        loginPage.fillPasswordInput("abcd");
        loginPage.clickLoginFormButton();
        String warningMessage = loginPage.getWarningMessageText();
        Assert.assertTrue(warningMessage.contains("Адрес электронной почты не зарегистрирован."));
        webDriver.close();
    }

    @AfterTest(alwaysRun = true)
    public void quitWebDriver() {
        WebDriverManager.chromedriver().quit();
    }
}