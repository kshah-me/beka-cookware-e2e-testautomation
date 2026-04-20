package com.bekacookware.test;


import com.bekacookware.base.DriverFactory;
import com.bekacookware.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bekacookware.pages.HomePage;
import org.testng.asserts.SoftAssert;

import java.util.Objects;


public class VerifyHomePage extends DriverFactory {

    SoftAssert softassert;
    HomePage homepage;

    @Test
	public void checkHomePage() {
        softassert = new SoftAssert();
        homepage = new HomePage(DriverFactory.getDriver());
        Assert.assertTrue(homepage.verifyLogoIsPresent());
        softassert.assertAll();
	}

	@Test
	public void verifyPageTitleInEnglish() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectEnglishLanguage();
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Beka Cookware");
	}

	@Test
	public void verifyPageTitleDutch() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectDutchLanguage();
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Beka Cookware");
    }

	@Test
	public void verifyApplicationInDutchLanguage() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectDutchLanguage();
        Assert.assertEquals(ConfigReader.propValueFromConfigFile("url"), DriverFactory.getDriver().getCurrentUrl());
	}

    @Test
    public void verifyApplicationInEnglishLanguage() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectEnglishLanguage();
        Assert.assertTrue(Objects.requireNonNull(DriverFactory.getDriver().getCurrentUrl()).contains("/en"));
    }

    @Test
    public void verifyApplicationInFranchLanguage() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectFrenchLanguage();
        Assert.assertTrue(Objects.requireNonNull(DriverFactory.getDriver().getCurrentUrl()).contains("/fr"));
    }

    @Test
    public void verifyApplicationInDeutschLanguage() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnLanguageDropDown();
        homepage.selectDeutschLanguage();
        Assert.assertTrue(Objects.requireNonNull(DriverFactory.getDriver().getCurrentUrl()).contains("/de"));
    }

    @Test
    public void verifyFooterIsAvailableOnHomePage() {
        homepage = new HomePage(DriverFactory.getDriver());
        homepage.verifyFooterOnHomePageDisplayed();
    }

}
