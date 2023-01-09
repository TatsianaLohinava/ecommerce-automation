package com.solvd.ecommerce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultPage {

    private final WebDriver webDriver;

    @FindBy(css = ".item-type-card")
    private List<WebElement> resultTableElementList;

    public int getResultListSize() {
        return resultTableElementList.size();
    }

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void printItemData() {
        getResultTableElementList().forEach(el -> {
            String brandName = el.findElement(By.cssSelector(".item-type-card__info")).getText();
            String itemName = el.findElement(By.cssSelector(".item-type-card__title")).getText();
            System.out.println(brandName + " " + itemName);
        });
    }

    public List<WebElement> getResultTableElementList() {
        return resultTableElementList;
    }
}