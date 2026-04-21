package com.bekacookware.base;


import com.bekacookware.config.ConfigReader;
import com.bekacookware.pages.HomePage;

import com.bekacookware.utility.CaptureScreenshotEvidence;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;


public class DriverFactory {

    HomePage homepage;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Set driver
    public static void setDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--force-device-scale-factor=0.8");
        chromeOptions.addArguments("--incognito");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--force-device-scale-factor=0.8"); // 80% zoom

        if (ConfigReader.propValueFromConfigFile("browserName").equals("chrome")) {
            driver.set(new ChromeDriver(chromeOptions));
        } else if (ConfigReader.propValueFromConfigFile("browserName").equals("edge")) {
            driver.set(new EdgeDriver(edgeOptions));
        } else {
            System.out.println("Please provide the correct browser name" + ConfigReader.propValueFromConfigFile("browserName"));
        }
    }

    // Get driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // VERY IMPORTANT
        }
    }


    @BeforeMethod
    public void setUpBrowser() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        DriverFactory.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.propValueFromConfigFile("url"));
    }

    @BeforeMethod(dependsOnMethods = "setUpBrowser")
    public void  closeCookiesAndAddPopUp(){
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickCookiesPopupAcceptance();
        homepage.clickCrossButtonOnGermanyCountrySelectionPopup();
        homepage.clickCloseButtonOnAddPopUp();
    }

    @BeforeMethod(dependsOnMethods = "closeCookiesAndAddPopUp")
    public void  changeTheLanguageOfApplication(){
        homepage = new HomePage(DriverFactory.getDriver());
        String lang =ConfigReader.propValueFromConfigFile("Language");
        homepage.clickOnLanguageDropDown();
        switch (lang) {
            case "EN" -> homepage.selectEnglishLanguage();
            case "DE" -> homepage.selectDeutschLanguage();
            case "NL" -> homepage.selectDutchLanguage();
            case "FR" -> homepage.selectFrenchLanguage();
        }
        WaitUtils.waitUntillPageLoaded(DriverFactory.getDriver());
        homepage.clickCloseButtonOnAddPopUp();
    }


    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        CaptureScreenshotEvidence.takeScreenshotOnFailure(DriverFactory.getDriver(),result);
        DriverFactory.quitDriver();
    }


//    public WebDriver driver;
//    HomePage homepage;
//    @BeforeMethod
//    public void setUpdriver() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--force-device-scale-factor=0.8");
//        chromeOptions.addArguments("--incognito");
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--force-device-scale-factor=0.8"); // 80% zoom
//
//        if (ConfigReader.propValueFromConfigFile("browserName").equals("chrome")) {
//            driver = new ChromeDriver(chromeOptions);
//        } else if (ConfigReader.propValueFromConfigFile("browserName").equals("edge")) {
//            driver = new EdgeDriver(edgeOptions);
//        } else {
//            System.out.println("Please provide the correct browser name" + ConfigReader.propValueFromConfigFile("browserName"));
//        }
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//        driver.get(ConfigReader.propValueFromConfigFile("url"));
//    }
//
//    @BeforeMethod(dependsOnMethods = "setUpdriver")
//    public void  closeCookiesAndAddPopUp(){
//        homepage = new HomePage(driver);
//        homepage.clickCookiesPopupAcceptance();
//        homepage.clickCrossButtonOnGermanyCountrySelectionPopup();
//        homepage.clickCloseButtonOnAddPopUp();
//    }
//
//    @BeforeMethod(dependsOnMethods = "closeCookiesAndAddPopUp")
//    public void  changeTheLanguageOfApplication(){
//        homepage = new HomePage(driver);
//        String lang =ConfigReader.propValueFromConfigFile("Language");
//        homepage.clickOnLanguageDropDown();
//        switch (lang) {
//            case "EN" -> homepage.selectEnglishLanguage();
//            case "DE" -> homepage.selectDeutschLanguage();
//            case "NL" -> homepage.selectDutchLanguage();
//            case "FR" -> homepage.selectFrenchLanguage();
//        }
//    }
//
//    @AfterMethod
//    public void teardown() {
//        driver.quit();
//    }
}
