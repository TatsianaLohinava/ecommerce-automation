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

    @FindBy(xpath = ".//*[@class = 'item-type-card__title']")
    private List<WebElement> resultNameList;

    private final String brandXPath = ".//following-sibling::p[1]";

    public int getResultListSize() {
        return resultTableElementList.size();
    }

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void printItemData() {
        resultNameList.forEach(el -> {
            WebElement brand = el.findElement(By.xpath(brandXPath));
            System.out.println(el.getText() + " " + brand.getText());
        });
    }
}