package com.bekacookware.pages;


import com.bekacookware.config.ConfigReader;
import com.bekacookware.utility.UrlCheck;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.Objects;

public class SearchPage {


    WebDriver driver;
    public SearchPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-modal-id='search-modal']")
    private WebElement searchButtonOnHomePage;
    public void clickSearchButtonOnHomePage() {
        WaitUtils.waitUntillElementVisibility(driver,searchButtonOnHomePage).click();
    }


    @FindBy(xpath = "(//input[@name='q'])[3]")
    private WebElement searchInputBoxOnHomePage;
    public void enterSearchInputBoxOnHomePage(String item) throws InterruptedException {
        WaitUtils.waitUntillElementVisibility(driver,searchInputBoxOnHomePage).sendKeys(item);
        Thread.sleep(5000);
    }

    @FindBy(xpath = "(//button[contains(@class,'search-bar__submit')])[3]")
    private WebElement searchSubmitButtonOnHomePage;
    public void clickSearchSubmitButtonOnHomePage() {
        WaitUtils.waitUntillElementVisibility(driver,searchSubmitButtonOnHomePage).click();
        WaitUtils.waitUntillPageLoaded(driver);
    }

    @FindBy(xpath = "//ul[@id='predictive-search-results']/div/h3[contains(text(),'Prod')]")
    private WebElement productHeaderInSearchResult;
    public void verifyProductAsHeaderInSearchResult() {
        Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver,productHeaderInSearchResult).getText().trim().toLowerCase().contains("prod"));
    }


    @FindBy(xpath = "//dl[contains(@class,'predictive-search-item__details price')]/ancestor::a")
    List<WebElement> searchResultItemHasNoBrokenLink;
    public void verifySearchResultItemHasNoBrokenLink() {
        for (WebElement link:searchResultItemHasNoBrokenLink){
            Assert.assertEquals(link.getTagName(),"a");
            Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck(Objects.requireNonNull(link.getAttribute("href"))),200);
        }
    }

    @FindBy(xpath = "//dl[contains(@class,'predictive-search-item__details price')]/ancestor::a//img")
    List<WebElement> searchResultItemHasNoBrokenImg;
    public void verifySearchResultItemHasNoBrokenImg() {
        for (WebElement img:searchResultItemHasNoBrokenImg){
            Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck(img.getAttribute("src")),200);
        }
    }

    @FindBy(xpath = "//dl[contains(@class,'predictive-search-item__details price')]//dd/span")
    List<WebElement> searchResultItemHasProductPrice;
    public void verifySearchResultItemHasProductPrice() {
        for (WebElement price:searchResultItemHasProductPrice){
            Assert.assertTrue(price.getText().contains("€"));
            Assert.assertTrue(price.getText().contains(","));
            Assert.assertTrue(price.getText().matches(".*€\\s*\\d+.*"));
        }
    }

    @FindBy(xpath = "//div[@class='search-result__image']//img")
    List<WebElement> productSearchResultPageHasNoBrokenImage;
    public void verifyProductSearchResultPageHasNoBrokenImage() {
        for (WebElement img:productSearchResultPageHasNoBrokenImage){
            Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck(img.getAttribute("src")),200);
        }
    }


    @FindBy(xpath = "//div[@class='grid__item large--three-quarters']//a")
    List<WebElement> productSearchResultPageHasProductNameAslinkAndNotEmpty;
    public void verifyProductSearchResultPageHasProductNameAslinkAndNotEmpty() {
        for (WebElement link:productSearchResultPageHasProductNameAslinkAndNotEmpty){
            Assert.assertFalse(link.getText().isEmpty());
            Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck(link.getAttribute("href")),200);
        }
    }

    @FindBy(xpath = "//div[@class='grid__item large--three-quarters']//a")
    List<WebElement> productSearchResultPageHasProductDescriptionNotEmpty;
    public void verifyProductSearchResultPageHasProductDescriptionNotEmpty() {
        Assert.assertFalse(productSearchResultPageHasProductDescriptionNotEmpty.isEmpty());
        System.out.println("Product Description count"+productSearchResultPageHasProductDescriptionNotEmpty.size());
        for (WebElement productdescription:productSearchResultPageHasProductDescriptionNotEmpty){
            Assert.assertFalse(productdescription.getText().isEmpty());
        }
    }

    @FindBy(xpath = "//a[text()='Home']/following-sibling::span[contains(text(),'0')]")
    private WebElement noSearchResultMessage;
    public void verifyNoSearchResultMessageOnHomePage() {
        String lang = ConfigReader.propValueFromConfigFile("Language");
        switch (lang) {
            case "EN" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, noSearchResultMessage).getText().contains("Search: 0 results found for"));
            case "DE" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, noSearchResultMessage).getText().contains("Suche: 0 Ergebnisse gefunden für"));
            case "NL" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, noSearchResultMessage).getText().contains("Zoeken: 0 resultaten gevonden voor"));
            case "FR" ->
                    Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver, noSearchResultMessage).getText().contains("Recherche : 0 résultat trouvé pour"));
        }
    }
}
