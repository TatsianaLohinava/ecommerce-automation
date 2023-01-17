package com.solvd.ecommerce;

import com.solvd.ecommerce.page.Header;
import com.solvd.ecommerce.page.HomePage;
import com.solvd.ecommerce.page.LoginPage;
import com.solvd.ecommerce.page.ResultPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EcommerceTest extends AbstractTest {

    @Test(dataProvider = "query")
    public void verifySearchResultTest(String query) {
        ResultPage resultPage = new ResultPage(getDriver());
        Header header = new Header(getDriver());
        header.searchInputEnter(query);
        header.searchButtonClick();
        Assert.assertNotEquals(resultPage.getResultListSize(), 0);
        resultPage.printItemData();
    }

    @Test
    public void checkBackgroundChangeTest() {
        HomePage homePage = new HomePage(getDriver());
        Actions action = new Actions(getDriver());
        SoftAssert sa = new SoftAssert();
        List<WebElement> list = homePage.getSidebarMenuList();
        for (WebElement el : list) {
            action.moveToElement(el).perform();
            sa.assertTrue(el.getAttribute("class").contains("main-nav__list__li_wnav_active"), "List element hasn't changed appearance on hover.");
        }
        sa.assertAll();
    }

    @Test
    public void checkPinButtonTest() {
        SoftAssert sa = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        List<String> classList = homePage.unpinCategoryMenu();

        classList.forEach(el -> sa.assertTrue(el.contains("mpgs-nopin"), "Heading hasn't changed its appearance."));
        sa.assertAll();
    }

    @Test(dataProvider = "query")
    public void checkInputResetTest(String query) {
        Header header = new Header(getDriver());
        header.searchInputEnter(query);
        header.clickResetButton();
        Assert.assertTrue(header.isSearchInputEmpty(), "Search input is not empty.");
    }

    @Test(dataProvider = "query")
    public void checkSearchFilterResultTest(String query) {
        ResultPage resultPage = new ResultPage(getDriver());
        Header header = new Header(getDriver());
        SoftAssert sa = new SoftAssert();

        header.searchInputEnter(query);
        header.searchButtonClick();
        resultPage.checkNewFilterBox();
        List<Boolean> isNewList = resultPage.checkNewItemsList();
        isNewList.forEach(el -> sa.assertTrue(el, "The element does not contain the badge."));
        sa.assertAll();
    }

    @Test(dataProvider = "credentials")
    public void checkInvalidLoginDataTest(String email, String pass) {
        Header header = new Header(getDriver());
        LoginPage loginPage = header.clickMainLoginButton();
        loginPage.clickLoginWithEmailButton();
        loginPage.fillEmailInput(email);
        loginPage.fillPasswordInput(pass);
        loginPage.clickLoginFormButton();
        String warningMessage = loginPage.getWarningMessageText();
        Assert.assertTrue(warningMessage.contains("Адрес электронной почты не зарегистрирован."), "Warning message does not contain the required text.");
    }

}