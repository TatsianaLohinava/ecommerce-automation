package com.solvd.ecommerce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver webDriver;

    @FindBy(css = ".main-nav__list__li.main-nav__list__li_wnav")
    private List<WebElement> sidebarMenuList;

    @FindBy(xpath = ".//*[contains(@id, 'category_goods_container') and not(contains(@class, 'mpgs-nopin'))]")
    private List<WebElement> categorySectionWithPinList;

    private final String pinButtonPath = "//*[contains(@class, 'i-oz') and contains(@class, 'mpgs-unpin')]";

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public List<String> unpinCategoryMenu() {
        List<String> unpinnedClasses = new ArrayList<>();
        for (WebElement el : categorySectionWithPinList) {
            WebElement pinButton = el.findElement(By.xpath(pinButtonPath));
            pinButton.click();
            unpinnedClasses.add(el.getAttribute("class"));
        }
        return unpinnedClasses;
    }

    public List<WebElement> getSidebarMenuList() {
        return sidebarMenuList;
    }

}