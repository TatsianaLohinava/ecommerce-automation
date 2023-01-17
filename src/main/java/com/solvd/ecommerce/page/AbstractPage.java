package com.solvd.ecommerce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);
    WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickElement(WebElement element) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        element.click();
        LOGGER.info(String.format("Element %s type %s was clicked", element.getTagName(), element.getAttribute("type")));
    }

    public void sendKeys(WebElement element, String query) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        LOGGER.info(String.format("'%s' was typed in", query));
        element.sendKeys(query);
    }

    public String getText(WebElement element) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        String textContent = element.getText();
        LOGGER.info(String.format("The text inside the element: %s", textContent));
        return textContent;
    }

    public String getAttribute(WebElement element, String attribute) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        String attributeValue = element.getAttribute(attribute);
        LOGGER.info(String.format("%s: '%s'", attribute, attributeValue));
        return attributeValue;
    }

    public WebElement findElement(WebElement webElement, String path) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(webElement));
        WebElement el = webElement.findElement(By.xpath(path));
        LOGGER.info(String.format("Element %s was found", el.getTagName()));
        return el;
    }
}