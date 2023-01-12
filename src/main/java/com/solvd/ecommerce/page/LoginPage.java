package com.solvd.ecommerce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver webDriver;

    @FindBy(id = "loginFormLoginEmailLink")
    private WebElement emailLoginTab;

    @FindBy(id = "loginForm")
    private WebElement loginForm;

    @FindBy(xpath = ".//*[@id = 'loginForm']//input[@type = 'email']")
    private WebElement emailInput;

    @FindBy(xpath = ".//*[@id = 'loginForm']//input[@type = 'password']")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*[@value = 'login' and text() = 'Войти']")
    private WebElement loginFormButton;

    @FindBy(css = "#loginForm .i-popover__line")
    private WebElement warningMessage;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickLoginWithEmailButton() {
        emailLoginTab.click();
    }

    public void fillEmailInput(String string) {
        emailInput.sendKeys(string);
    }

    public void fillPasswordInput(String string) {
        passwordInput.sendKeys(string);
    }

    public void clickLoginFormButton() {
        loginFormButton.click();
    }

    public String getWarningMessageText() {
        new WebDriverWait(webDriver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#loginForm .i-popover__line")));

        return warningMessage.getText();
    }
}