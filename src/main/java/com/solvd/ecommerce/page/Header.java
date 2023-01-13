package com.solvd.ecommerce.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractPage {

    @FindBy(css = "#top-s")
    private WebElement searchInput;

    @FindBy(css = ".top-panel__search__btn")
    private WebElement searchButton;

    @FindBy(css = ".input-reset")
    private WebElement inputResetButton;

    @FindBy(xpath = ".//a[@class = 'top-panel__userbar__auth']")
    private WebElement mainLoginButton;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchInputEnter(String query) {
        sendKeys(searchInput, query);
    }

    public void searchButtonClick() {
        clickElement(searchButton);
    }

    public void clickResetButton() {
        clickElement(inputResetButton);
    }

    public boolean isSearchInputEmpty() {
        String value = getAttribute(searchInput, "value");
        return value.isEmpty();
    }

    public LoginPage clickMainLoginButton() {
        clickElement(mainLoginButton);
        return new LoginPage(webDriver);
    }
}