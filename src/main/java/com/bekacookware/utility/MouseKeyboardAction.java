package com.bekacookware.utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseKeyboardAction {


    public void clickSubMenuViaMouseHover(WebDriver driver,WebElement menu, WebElement submenu){
        new Actions(driver)
        .moveToElement(menu)
                .moveToElement(submenu)
                .click()
                .perform();
    }

    public void mouseHoverOnMainMenu(WebDriver driver,WebElement menu){
        new Actions(driver)
        .moveToElement(menu)
                .perform();

    }

}
