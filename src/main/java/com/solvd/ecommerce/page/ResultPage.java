package com.solvd.ecommerce.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultPage.class);

    @FindBy(css = ".item-type-card")
    private List<WebElement> resultTableElementList;

    @FindBy(xpath = ".//*[@class = 'item-type-card__title']")
    private List<WebElement> resultNameList;

    private final String brandXPath = ".//following-sibling::p[1]";

    @FindBy(xpath = ".//*[@class = 'link-facet-bot' and text() = 'Новинки']")
    private WebElement newFilterCheckbox;

    public ResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public int getResultListSize() {
        return resultTableElementList.size();
    }

    public void printItemData() {
        resultNameList.forEach(el -> {
            WebElement brand = findElement(el, brandXPath);
            LOGGER.info(getText(el) + " " + getText(brand));
        });
    }

    public void checkNewFilterBox() {
        if (newFilterCheckbox.isDisplayed()) {
            clickElement(newFilterCheckbox);
        }
    }

    public List<Boolean> checkNewItemsList() {
        List<Boolean> newItems = new ArrayList<>();
        for (WebElement webElement : resultTableElementList) {
            boolean isNew = getText(webElement).contains("Новинка");
            newItems.add(isNew);
        }
        return newItems;
    }
}