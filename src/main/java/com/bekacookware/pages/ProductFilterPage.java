package com.bekacookware.pages;


import com.bekacookware.config.ConfigReader;
import com.bekacookware.utility.PageScrollUtility;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.regex.*;


public class ProductFilterPage{

    WebDriver driver;
    public ProductFilterPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='FacetsWrapperDesktop']/details[@data-index='1']")
    private WebElement filterSection;
    public void scrollToFilterSection() {
        PageScrollUtility.scrollToElement(driver,filterSection);
    }

    @FindBy(xpath = "//span[@id='ProductCountDesktop']")
    private WebElement totalResultCountOnFilterSection;
    public Integer getTotalResultCountOnFilterSection() {
        return Integer.parseInt(WaitUtils.waitUntillElementVisibility(driver,totalResultCountOnFilterSection).getText().replaceAll("\\D+", ""));
    }

    public Integer extractFilterCount() {
        Matcher matcher = Pattern.compile("\\d+").matcher(WaitUtils.waitUntillElementVisibility(driver,totalResultCountOnFilterSection).getText());
        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;
    }

    @FindBy(xpath = "(//div[@id='FacetsWrapperDesktop']/details[@data-index='1']/div//ul//div)[1]")
    private WebElement filterByPannTypeUnderFilterSection;
    public void clickFilterByFishPannUnderFilterSection() {
        Integer countWithoutFilter =getTotalResultCountOnFilterSection();
        WaitUtils.waitUntillElementVisibility(driver,filterByPannTypeUnderFilterSection).click();
        Assert.assertTrue(countWithoutFilter >= extractFilterCount());
    }

    @FindBy(xpath = "(//div[@id='FacetsWrapperDesktop']/details[@data-index='3']/div//ul/li)[3]")
    private WebElement filterMaterialAluminiumButtonOnFilterSection;
    @FindBy(xpath = "//div[@id='FacetsWrapperDesktop']/details[@data-index='3']/summary")
    private WebElement filterMaterialExpandButtonOnFilterSection;
    public void clickFilterMaterialButtonOnFilterSection() throws InterruptedException {
        Thread.sleep(3000);
        Integer countWithoutFilter =getTotalResultCountOnFilterSection();
        WaitUtils.waitUntillElementVisibility(driver,filterMaterialExpandButtonOnFilterSection).click();
        Thread.sleep(3000);
        WaitUtils.waitUntillElementClickable(driver,filterMaterialAluminiumButtonOnFilterSection).click();
        Assert.assertTrue(countWithoutFilter >= extractFilterCount());
        Thread.sleep(3000);
    }

    @FindBy(xpath = "//div[@id='FacetsWrapperDesktop']/details[@data-index='2']/summary")
    private WebElement filterHeatSourceDishwasherExpandButtonOnFilterSection;
    @FindBy(xpath = "(//div[@id='FacetsWrapperDesktop']/details[@data-index='2']/div//ul//div)[1]")
    private WebElement filterHeatSourceTypeElectricButtonOnFilterSection;
    public void clickFilterMaterialTypeAluminumButtonOnFilterSection() throws InterruptedException {
        Integer countWithoutFilter =getTotalResultCountOnFilterSection();
        WaitUtils.waitUntillElementVisibility(driver,filterHeatSourceDishwasherExpandButtonOnFilterSection).click();
        Thread.sleep(3000);
        WaitUtils.waitUntillElementVisibility(driver,filterHeatSourceTypeElectricButtonOnFilterSection).click();
        Assert.assertTrue(countWithoutFilter >= extractFilterCount());
        Thread.sleep(3000);
    }

    @FindBy(xpath = "(//input[@class='input js-max-value' and @type='number'])[1]")
    private WebElement priceEndRangeInputBox;
    public void enterEndPriceRangeInputBox(Integer num) throws InterruptedException {
        WaitUtils.waitUntillElementVisibility(driver,priceEndRangeInputBox).sendKeys(Integer.toString(num));
        Thread.sleep(3000);
    }

    @FindBy(xpath = "//div[@id='product-grid']//p")
    private WebElement noRecordFoundMessage;
    public void verifyNoRecordFoundMessageForAllLanguages(){
        String lang =ConfigReader.propValueFromConfigFile("Language");
        switch (lang) {
            case "EN" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, noRecordFoundMessage).getText(), "Sorry, there are no products in this collection");
            case "DE" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, noRecordFoundMessage).getText(), "Es tut uns leid, aber Ihre Suche nach Produkten hat keine Treffer ergeben.");
            case "NL" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, noRecordFoundMessage).getText(), "Sorry, er zitten geen producten in deze collectie");
            case "FR" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, noRecordFoundMessage).getText(), "Aucun produit ne correspond à votre recherche.");
        }
    }

}