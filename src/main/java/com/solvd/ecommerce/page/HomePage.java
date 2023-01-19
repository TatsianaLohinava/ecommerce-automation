package com.solvd.ecommerce.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(css = ".main-nav__list__li.main-nav__list__li_wnav")
    private List<WebElement> sidebarMenuList;

    @FindBy(xpath = ".//*[contains(@id, 'category_goods_container') and not(contains(@class, 'mpgs-nopin'))]")
    private List<WebElement> categorySectionWithPinList;

    private final String pinButtonPath = "//*[contains(@class, 'i-oz') and contains(@class, 'mpgs-unpin')]";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> unpinCategoryMenu() {
        List<String> unpinnedClasses = new ArrayList<>();
        for (WebElement el : categorySectionWithPinList) {
            WebElement pinButton = findElement(el, pinButtonPath);
            clickElement(pinButton);
            unpinnedClasses.add(getAttribute(el, "class"));
        }
        return unpinnedClasses;
    }

    public List<String> getElementClassList(WebDriver webDriver) {
        List<String> sidebarElementClassList = new ArrayList<>();
        Actions action = new Actions(webDriver);
        for (WebElement el : sidebarMenuList) {
            action.moveToElement(el).perform();
            String elementClassName = el.getAttribute("class");
            sidebarElementClassList.add(elementClassName);
        }
        return  sidebarElementClassList;
    }

}