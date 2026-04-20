package com.bekacookware.pages;


import com.bekacookware.utility.MouseKeyboardAction;
import com.bekacookware.utility.PageScrollUtility;
import com.bekacookware.utility.UrlCheck;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProductDetailsPage{

    WebDriver driver;
    MouseKeyboardAction mka = new MouseKeyboardAction();
    public ProductDetailsPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "(//div[@id='product-grid']//div[@class='product-item__wrapper'])[3]")
    private WebElement thirdItemUnderProductOrCollectionResultPage;
    public void clickThirdItemUnderProductOrCollectionResultPage() {
        WaitUtils.waitUntillElementVisibility(driver,thirdItemUnderProductOrCollectionResultPage).click();
    }

    @FindBy(xpath = "(//div[@id='product-grid']//div[@class='product-item__wrapper'])[2]")
    private WebElement secondItemUnderProductOrCollectionResultPage;
    public void clickSecondItemUnderProductOrCollectionResultPage() {
        WaitUtils.waitUntillElementVisibility(driver,secondItemUnderProductOrCollectionResultPage).click();
    }

    @FindBy(xpath = "(//div[@id='product-grid']//div[@class='product-item__wrapper'])[4]")
    private WebElement fourthItemUnderProductOrCollectionResultPage;
    public void clickFourthtemUnderProductOrCollectionResultPage() {
        WaitUtils.waitUntillElementVisibility(driver,thirdItemUnderProductOrCollectionResultPage).click();
    }

    @FindBy(xpath = "//h1[@class='product__title']")
    private WebElement productNameOnProductDetailsPage;
    public void verifyProductNameIsNotEmpty() {
      Assert.assertFalse(WaitUtils.waitUntillElementVisibility(driver,productNameOnProductDetailsPage).getText().isEmpty());
    }

    @FindBy(xpath = "//div[@class='product__price']/span")
    private WebElement productPriceOnProductDetailsPage;
    public void verifyProductPrinceIsNotEmpty() {
        Assert.assertFalse(WaitUtils.waitUntillElementVisibility(driver,productPriceOnProductDetailsPage).getText().isEmpty());
    }

    public void verifyProductPrinceIsInEuro() {
        Assert.assertTrue(WaitUtils.waitUntillElementVisibility(driver,productPriceOnProductDetailsPage).getText().matches("€.*\\d.*"));
    }

    @FindBy(xpath = "//button[@data-tab-id='description']")
    private WebElement productDescriptionButtonOnProductDetailsPage;
    public void clickProductDescriptionButton() {
        PageScrollUtility.scrollToElement(driver,productDescriptionButtonOnProductDetailsPage);
        WaitUtils.waitUntillElementVisibility(driver,productDescriptionButtonOnProductDetailsPage).click();
    }

    @FindBy(xpath = "//div[@data-tab-id='description']/div")
    private WebElement productDescriptionOnProductDetailsPage;
    public void verifyProductDescriptionIsNotEmpty() {
        Assert.assertFalse(WaitUtils.waitUntillElementVisibility(driver,productDescriptionOnProductDetailsPage).getText().isEmpty());
    }

    @FindBy(xpath = "//img[@loading='eager']")
    private WebElement productImageOnProductDetailsPage;
    public void verifyProductImageIsNotBrokenOnProductDetailsPage() {
        Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck("https:"+WaitUtils.waitUntillElementVisibility(driver,productImageOnProductDetailsPage).getDomAttribute("src")),200);
    }

    @FindBy(xpath = "//div[@data-config='product-thumbs-slider']/div//img")
    private List<WebElement> productImageGalleryOnProductDetailsPage;
    public void verifyProductImagesGalleryOnProductDetailsPage() {
        for (WebElement image:productImageGalleryOnProductDetailsPage){
         Assert.assertEquals(UrlCheck.brokenUrlAndImageCheck("https:"+image.getDomAttribute("src")),200);
        }
    }


}