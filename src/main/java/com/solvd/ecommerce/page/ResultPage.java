package com.solvd.ecommerce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultPage {

    Logger logger = LoggerFactory.getLogger(ResultPage.class);

    private final WebDriver webDriver;

    @FindBy(css = ".item-type-card")
    private List<WebElement> resultTableElementList;

    @FindBy(xpath = ".//*[@class = 'item-type-card__title']")
    private List<WebElement> resultNameList;

    private final String brandXPath = ".//following-sibling::p[1]";

    @FindBy(xpath = ".//*[@class = 'link-facet-bot' and text() = 'Новинки']")
    private WebElement newFilterCheckbox;

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public int getResultListSize() {
        return resultTableElementList.size();
    }

    public void printItemData() {
        resultNameList.forEach(el -> {
            WebElement brand = el.findElement(By.xpath(brandXPath));
            logger.info(el.getText() + " " + brand.getText());
        });
    }

    public void checkNewFilterBox() {
        if (newFilterCheckbox.isDisplayed()) {
            newFilterCheckbox.click();
        }
    }

    public List<Boolean> checkNewItemsList() {
        List<Boolean> newItems = new ArrayList<>();
        for (WebElement webElement : resultTableElementList) {
            boolean isNew = webElement.getText().contains("Новинка");
            newItems.add(isNew);
        }
        return newItems;
    }
}