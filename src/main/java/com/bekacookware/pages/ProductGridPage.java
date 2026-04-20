package com.bekacookware.pages;


import com.bekacookware.utility.MouseKeyboardAction;
import com.bekacookware.utility.UrlCheck;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProductGridPage{

    WebDriver driver;
    public ProductGridPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    MouseKeyboardAction mka = new MouseKeyboardAction();
    UrlCheck urlcheck = new UrlCheck();
    

    @FindBy(xpath = "//ul[@class='main-nav__list']/li/a[contains(@href,'collections/all')]")
    private WebElement productMenuDropDown;
    public void HoverOnProductMenuDropDown() {
        mka.mouseHoverOnMainMenu(driver,WaitUtils.waitUntillElementVisibility(driver,productMenuDropDown));
    }

    @FindBy(xpath = "//img[contains(@src,'cdn/shop/files/pannen.png?')]/ancestor::a")
    private WebElement fryingPannUnderProductGridDropDown;
    public void clickFryingPannUnderProductGridDropDown() {
        mka.clickSubMenuViaMouseHover(driver,WaitUtils.waitUntillElementVisibility(driver,productMenuDropDown), fryingPannUnderProductGridDropDown);
    }


    @FindBy(xpath = "//a[contains(@href,'collections/all')]/following-sibling::ul//ul/li//img")
    private List<WebElement> productMenuList;
    public void verifyImagesOfItemsUnderProductList(){
        for(WebElement productItem:productMenuList){
            Assert.assertEquals(urlcheck.brokenUrlAndImageCheck("https:"+productItem.getDomAttribute("src")),200);
        }
    }

    @FindBy(xpath = "//ul[@class='main-nav__dropdown']/preceding-sibling::a[contains(@href,'page')]")
    private WebElement collectionMenuDropDown;
    public void HoverOnCollectionMenuDropDown() {
        mka.mouseHoverOnMainMenu(driver,WaitUtils.waitUntillElementVisibility(driver,collectionMenuDropDown));
    }

    @FindBy(xpath = "//ul[@class='main-nav__dropdown']/preceding-sibling::a[contains(@href,'page')]/following-sibling::ul//ul/li//img")
    private List<WebElement> collectionMenuList;
    public void verifyImagesOfItemsUnderCollectionMenuList(){
        for(WebElement collectionItem:collectionMenuList){
            Assert.assertEquals(urlcheck.brokenUrlAndImageCheck("https:"+collectionItem.getDomAttribute("src")),200);
        }
    }

    @FindBy(xpath = "//img[contains(@src,'cdn/shop/files/Collection_images4_Chef')]/ancestor::a")
    private WebElement chefProductUnderCollectionGridDropDown;
    public void clickChefProductUnderCollectionGridDropDown() {
        mka.clickSubMenuViaMouseHover(driver,WaitUtils.waitUntillElementVisibility(driver,collectionMenuDropDown), chefProductUnderCollectionGridDropDown);
    }

    @FindBy(xpath = "(//div[@id='product-grid']//div[@class='product-item__wrapper'])[3]")
    private WebElement fryingPannUnderProductResultPage;
    public void clickFryingPannUnderProductResultPage() {
        WaitUtils.waitUntillElementVisibility(driver,fryingPannUnderProductResultPage).click();
    }
}
