package com.bekacookware.utility;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitUtils{


    public static WebElement waitUntillElementVisibility(WebDriver driver,WebElement locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOf(locator));
    }

    public static WebElement waitUntillElementClickable(WebDriver driver,WebElement element) {
        return new WebDriverWait(driver,Duration.ofSeconds(120))
        .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntillPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(webDriver ->
                Objects.equals(((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState"), "complete"));

    }

    public static void waitAndClickIfPresent(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
             wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        } catch (TimeoutException e) {
            System.out.println("Element did not appear in 3 minutes, moving to next step");
        }

    }

}
