package com.solvd.ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcommerceTest {

    HomePage homePage;

    @Test
    public void verifySearchResultTest() {
        String query = "Maped";
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://oz.by");
        homePage = new HomePage(webDriver);
        homePage.searchInputEnter(query);
        homePage.searchButtonClick();
        Assert.assertNotEquals(homePage.getResultTableElementList().size(), 0);
        for (WebElement el : homePage.getResultTableElementList()) {
            String brand = el.findElement(By.cssSelector(".item-type-card__info")).getText();
            String itemName = el.findElement(By.cssSelector(".item-type-card__title")).getText();
            System.out.println(brand + " " + itemName);
        }
        webDriver.quit();
    }
}