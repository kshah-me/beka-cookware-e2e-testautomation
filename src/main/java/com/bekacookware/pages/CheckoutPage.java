package com.bekacookware.pages;

import com.bekacookware.config.ConfigReader;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@name='reductions']")
    private WebElement voucherCodeOnCheckoutPage;
    public void enterVoucherCodeOnCheckoutPage(String voucher) {
        WaitUtils.waitUntillElementVisibility(driver,voucherCodeOnCheckoutPage).sendKeys(voucher);
    }

    @FindBy(xpath = "//button[@data-event-name='apply_discount']")
    private WebElement voucherCodeRedeemButtonOnCheckoutPage;
    public void clickVoucherCodeRedeemButtonOnCheckoutPage() {
        WaitUtils.waitUntillElementClickable(driver,voucherCodeRedeemButtonOnCheckoutPage).click();
    }

    @FindBy(xpath = "//div[@id='error-for-ReductionsInput0']")
    private WebElement errorMessageForInvalidVoucherCodeOnCheckoutPage;
    public void verifyErrorMessageForInvalidVoucherCodeOnCheckoutPage() {
        WaitUtils.waitUntillElementVisibility(driver,errorMessageForInvalidVoucherCodeOnCheckoutPage).click();
        String lang = ConfigReader.propValueFromConfigFile("Language");
        switch (lang) {
            case "EN" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidVoucherCodeOnCheckoutPage).getText(), "Enter a valid discount code");
            case "DE" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidVoucherCodeOnCheckoutPage).getText(), "Gib einen gültigen Rabattcode ein");
            case "NL" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidVoucherCodeOnCheckoutPage).getText(), "Voer een geldige kortingscode in");
            case "FR" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, errorMessageForInvalidVoucherCodeOnCheckoutPage).getText(), "Saisir un code de réduction valide");
        }
    }
}
