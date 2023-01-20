package com.solvd.ecommerce.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenCapturer {

    public static void captureScreenshot(WebDriver webDriver, String methodName, String browserName) throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(String.format("./screenshot/scr-%s-%s-%s.png", browserName, methodName, LocalDateTime.now())));
    }

}