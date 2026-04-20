package com.bekacookware.pages;



import com.bekacookware.config.ConfigReader;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage{

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//img[@class='logo-desktop']")
	private WebElement logo;
	public boolean verifyLogoIsPresent() {
		return WaitUtils.waitUntillElementVisibility(driver,logo).isDisplayed();
	}

    @FindBy(xpath = "//button[@aria-label='Close dialog']/*[local-name()='svg']")
    private WebElement closeButtonOnAddPopUp;
    public void clickCloseButtonOnAddPopUp() {
        WaitUtils.waitUntillElementVisibility(driver,closeButtonOnAddPopUp).click();
    }

    @FindBy(xpath = "//button[text()='Cookies accepteren']")
    private WebElement cookiesPopupAcceptance;
    public void clickCookiesPopupAcceptance() {
        WaitUtils.waitUntillElementVisibility(driver,cookiesPopupAcceptance).click();
    }


    @FindBy(xpath = "//button[@class='modal__close | js-modal-close']")
    private WebElement crossButtonOnGermanyCountrySelectionPopup;
    public void clickCrossButtonOnGermanyCountrySelectionPopup() {
        WaitUtils.waitUntillElementVisibility(driver,crossButtonOnGermanyCountrySelectionPopup).click();
    }

    public String getPageTitleInEnglish() {
        return driver.getTitle();
    }

    @FindBy(xpath = "//ul//form[@id='localization_form']")
    private WebElement languageDropDownButton;
    public void clickOnLanguageDropDown() {
        WaitUtils.waitUntillElementVisibility(driver,languageDropDownButton).click();
    }

    @FindBy(xpath = "(//ul[@id='lang-list']/li/a[@data-value='nl'])[1]")
    private WebElement nlLanguage;
    public void selectDutchLanguage() {
        WaitUtils.waitUntillElementVisibility(driver,nlLanguage).click();
    }

    @FindBy(xpath = "(//ul[@id='lang-list']/li/a[@data-value='en'])[1]")
    private WebElement enLanguage;
    public void selectEnglishLanguage() {
        WaitUtils.waitUntillElementVisibility(driver,enLanguage).click();
    }

    @FindBy(xpath = "(//ul[@id='lang-list']/li/a[@data-value='fr'])[1]")
    private WebElement frLanguage;
    public void selectFrenchLanguage() {
        WaitUtils.waitUntillElementVisibility(driver,frLanguage).click();
    }

    @FindBy(xpath = "(//ul[@id='lang-list']/li/a[@data-value='de'])[1]")
    private WebElement deLanguage;
    public void selectDeutschLanguage() {
        WaitUtils.waitUntillElementVisibility(driver,deLanguage).click();
    }

    @FindBy(tagName = "footer")
    private WebElement footerOnHomePage;
    public void verifyFooterOnHomePageDisplayed() {
        Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver,footerOnHomePage).isDisplayed());
    }

    @FindBy(tagName = "a")
    private List<WebElement> navigationOnHomePage;
    public void verifyNavigationOnHomePage() {
        Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver,footerOnHomePage).isDisplayed());
    }


    @FindBy(xpath = "//div[@class='icon icon-account']/parent::a")
    private WebElement accountIconButton;
    public void clickOnAccountIconButton() {
        WaitUtils.waitUntillElementVisibility(driver,accountIconButton).click();
    }

    @FindBy(xpath ="//input[@id='customer-authentication-web-email']")
    private WebElement userEmailId;
    public void EnterUserEmailId(String emailid) {
        WaitUtils.waitUntillElementVisibility(driver,userEmailId).sendKeys(emailid);
    }

    @FindBy(xpath ="//input[@id='customer-authentication-web-email']/parent::div/parent::div/following-sibling::button")
    private WebElement loginButton;
    public void clickOnLoginButton() {
        WaitUtils.waitUntillElementVisibility(driver,loginButton).click();
    }

    @FindBy(xpath = "//div[@id='error-for-customer-authentication-web-email']")
    private WebElement errorMessageForInvalidUserName;
    public void verifyErrorMessageForInvalidUserName() {
        String lang = ConfigReader.propValueFromConfigFile("Language");
        switch (lang) {
            case "EN" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidUserName).getText().contains("Enter a valid email address"));
            case "DE" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidUserName).getText().contains("Gib eine gültige E-Mail-Adresse ein"));
            case "NL" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidUserName).getText().contains("Voer een geldig e-mailadres in"));
            case "FR" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidUserName).getText().contains("Saisissez une adresse e-mail valide"));
        }

    }
}
