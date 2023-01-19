package com.solvd.ecommerce.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesFactory {

    public static Capabilities createCapability(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeOptions();
            case "firefox":
                return new FirefoxOptions();
            default:
                return new SafariOptions();
        }
    }

}