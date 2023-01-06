package com.solvd.ecommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver webDriver;
    @FindBy(css = "#top-s")
    WebElement searchInput;
    @FindBy(css = ".top-panel__search__btn")
    WebElement searchButton;
    @FindBy(css = ".item-type-card")
    List<WebElement> resultTableElementList;

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

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public List<WebElement> getResultTableElementList() {
        return resultTableElementList;
    }
}