package com.solvd.ecommerce.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver webDriver;

    @FindBy(css = "#top-s")
    private WebElement searchInput;

    @FindBy(css = ".top-panel__search__btn")
    private WebElement searchButton;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void searchInputEnter(String query) {
        searchInput.sendKeys(query);
    }

    public void searchButtonClick() {
        searchButton.click();
    }

}