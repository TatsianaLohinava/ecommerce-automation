package com.solvd.ecommerce.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

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
        super(webDriver);
    }

    public void clickLoginWithEmailButton() {
        clickElement(emailLoginTab);
    }

    public void fillEmailInput(String string) {
        sendKeys(emailInput, string);
    }

    public void fillPasswordInput(String string) {
        sendKeys(passwordInput, string);
    }

    public void clickLoginFormButton() {
        clickElement(loginFormButton);
    }

    public String getWarningMessageText() {
        return getText(warningMessage);
    }
}