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

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

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

    @Test
    public void verifySearchResultTest() {
        String query = "Maped";
        ResultPage resultPage = new ResultPage(webDriver.get());
        Header header = new Header(webDriver.get());
        header.searchInputEnter(query);
        header.searchButtonClick();
        Assert.assertNotEquals(resultPage.getResultListSize(), 0);
        resultPage.printItemData();
    }

    @Test
    public void checkBackgroundChangeTest() {
        HomePage homePage = new HomePage(webDriver.get());
        Actions action = new Actions(webDriver.get());
        SoftAssert sa = new SoftAssert();
        List<WebElement> list = homePage.getSidebarMenuList();
        for (WebElement el : list) {
            action.moveToElement(el).perform();
            sa.assertTrue(el.getAttribute("class").contains("main-nav__list__li_wnav_active"));
        }
        sa.assertAll();
    }

    @Test
    public void checkPinButtonTest() {
        SoftAssert sa = new SoftAssert();
        HomePage homePage = new HomePage(webDriver.get());
        List<String> classList = homePage.unpinCategoryMenu();

        classList.forEach(el -> sa.assertTrue(el.contains("mpgs-nopin")));
        sa.assertAll();
    }

    @Test
    public void checkInputResetTest() {
        String query = "Maped";
        Header header = new Header(webDriver.get());
        header.searchInputEnter(query);
        header.clickResetButton();
        Assert.assertTrue(header.isSearchInputEmpty());
    }

    @Test
    public void checkSearchFilterResultTest() {
        String query = "английский";
        ResultPage resultPage = new ResultPage(webDriver.get());
        Header header = new Header(webDriver.get());
        SoftAssert sa = new SoftAssert();

        header.searchInputEnter(query);
        header.searchButtonClick();
        resultPage.checkNewFilterBox();
        List<Boolean> isNewList = resultPage.checkNewItemsList();
        isNewList.forEach(sa::assertTrue);
        sa.assertAll();
    }

    @Test
    public void checkInvalidLoginDataTest() {
        Header header = new Header(webDriver.get());
        LoginPage loginPage = header.clickMainLoginButton();
        loginPage.clickLoginWithEmailButton();
        loginPage.fillEmailInput("abc@gmail.com");
        loginPage.fillPasswordInput("abcd");
        loginPage.clickLoginFormButton();
        String warningMessage = loginPage.getWarningMessageText();
        Assert.assertTrue(warningMessage.contains("Адрес электронной почты не зарегистрирован."));
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.get().close();
    }


    @AfterTest(alwaysRun = true)
    public void quitWebDriver() {
        WebDriverManager.chromedriver().quit();
    }
}