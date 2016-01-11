package com.epam.webdriver.task1.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Siarhei_Chyhir on 12/29/2015.
 */
public class Driver {
    private static WebDriver driver;

    public static WebDriver get() {
        return driver;
    }

    public boolean isElementPresent(By locator) {
        get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> elements = get().findElements(locator);
        get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static void set(WebDriver driverInput) {
        driver = driverInput;
    }

    public static void init() {
        WebDriver driverInput = new FirefoxDriver();
        driverInput.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Driver.set(driverInput);
    }

    public static void tearDown() {
        Driver.get().quit();
    }

}
