package com.bekacookware.pages;



import com.bekacookware.config.ConfigReader;
import com.bekacookware.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;

public class CartPage{


    Map<String, Double> map = new HashMap<>();
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//ul/li/a[@data-modal-id='cart-modal']")
    private WebElement cartButton;
    public void clickCartButton() {
        WaitUtils.waitUntillElementVisibility(driver,cartButton).click();
    }

    @FindBy(xpath="//ul/li/a[@data-modal-id='cart-modal']/sup")
    private WebElement countOnCartButton;
    public void verifyCountOnCartButton(Integer count) {
        System.out.println("Thee count on card is: "+countOnCartButton);
        Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver,countOnCartButton).getText().trim(),String.valueOf(count));
    }

    @FindBy(xpath="//div[@class='ajaxcart__product-info']/a")
    List<WebElement> nameOfEachItemUnderCartPopup;
    @FindBy(xpath="//div[contains(@class,'ajaxcart__inner')]/div[@class='ajaxcart__product']/div/span")
    List<WebElement> priceOfEachItemUnderCartInEuro;
    public void verifyPriceOfEachItemUnderCartInEuro() {
        for(WebElement item:priceOfEachItemUnderCartInEuro){
            Assert.assertTrue(item.getText().contains("€"));
        }
    }

    public void verifyPriceOfItemPerCountUnderCart() {
        if(nameOfEachItemUnderCartPopup.size()==priceOfEachItemUnderCartInEuro.size()){
            for(int i=0;i<nameOfEachItemUnderCartPopup.size();i++){
                Integer count =getItemCountnCartPopUp().get(i+1);
                Assert.assertEquals(Math.round((map.get(nameOfEachItemUnderCartPopup.get(i).getText().toUpperCase())
                                * count) * 10.0) / 10.0
                        ,
                        Double.parseDouble(priceOfEachItemUnderCartInEuro.get(i).getText().replace("€", "").replace(",", "."))
                        );
            }
        }

    }



    @FindBy(xpath="//p[@class='ajaxcart__total-price']")
    private WebElement totalPriceOnCart;
    public void verifyTotalPriceIsSumOfEachItemPrice() {
        double price=0.0;
        for(WebElement itemprice:priceOfEachItemUnderCartInEuro){
            price =price+ Double.parseDouble(itemprice.getText().replace("€", "").replace(",", "."));
        }
        Assert.assertEquals(price,Double.parseDouble(totalPriceOnCart.getText().replace("€", "").replace(",", ".")));
    }


    @FindBy(xpath="//button[@data-increment='1']")
    private WebElement incrementItemCountButtonOnProductDetailsPage;
    public void clickIncrementItemCountButtonOnProductDetailsPage() {
        WaitUtils.waitUntillElementVisibility(driver,incrementItemCountButtonOnProductDetailsPage).click();
    }

    @FindBy(xpath="(//button[@data-increment='1'])[2]")
    private WebElement incrementItemCountButtonCartPopUp;
    public void clickIncrementItemCountButtonCartPopUp() {
        WaitUtils.waitUntillElementVisibility(driver,incrementItemCountButtonCartPopUp).click();
    }

    @FindBy(xpath="//button[@data-increment='1']/preceding-sibling::input")
    private List<WebElement> ItemCountCartPopUp;
    public List<Integer> getItemCountnCartPopUp() {
        List<Integer> intList = new ArrayList<>();
        for(WebElement ele:ItemCountCartPopUp){
            intList.add(Integer.parseInt(Objects.requireNonNull(ele.getDomAttribute("value"))));
        }
        return intList;
    }


    @FindBy(xpath="//p[@class='cart-drawer__title h4']/span")
    private WebElement countOnOpenCartPage;
    public void verifyCountOnOpenCartPage(Integer count) {
        Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver,countOnOpenCartPage).getText().trim(),String.valueOf(count));

    }

    @FindBy(xpath="//button[@class='ajaxcart__product-remove | js-remove-item']")
    private WebElement deleteButtonOnOpenCartPage;
    public void clickDeleteButtonOnOpenCartPage() {
        WaitUtils.waitUntillElementVisibility(driver,deleteButtonOnOpenCartPage).click();
    }

    @FindBy(xpath="//button[@data-type='favorite_products']/preceding-sibling::div/button")
    private WebElement addToCartButton;
    @FindBy(xpath = "//div[@class='product__price']/span")
    private WebElement priceOfItem;
    @FindBy(xpath = "//h1[@class='product__title']")
    private WebElement nameOfItem;
    public void clickAddToCartButton() throws InterruptedException {
        map.put(nameOfItem.getText(),Double.parseDouble(
                priceOfItem.getText().replace("€", "").replace(",", ".")
        ));
        WaitUtils.waitUntillElementVisibility(driver,addToCartButton).click();
        Thread.sleep(3000);
    }

    @FindBy(xpath="//p[@class='cart-drawer__title h4']/following-sibling::button")
    private WebElement closeOpenCartPupupButton;
    public void clickCloseOpenCartPupupButton() {
        WaitUtils.waitUntillElementVisibility(driver,closeOpenCartPupupButton).click();
    }

    @FindBy(xpath="//button[@name='checkout']")
    private WebElement checkoutButtonOnCartPage;
    public void clickCheckoutButtonOnCartPage() {

        WaitUtils.waitUntillElementVisibility(driver,checkoutButtonOnCartPage).click();
    }

    @FindBy(xpath="//div[@id='cart-container']/p")
    private WebElement messageOnEmptyCart;
    public void verifyMessageOnEmptyCart() {
        String lang = ConfigReader.propValueFromConfigFile("Language");
        switch (lang) {
            case "EN" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, messageOnEmptyCart).getText(), "Your cart is currently empty.");
            case "DE" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, messageOnEmptyCart).getText(), "Ihr Einkaufswagen ist im Moment leer.");
            case "NL" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, messageOnEmptyCart).getText(), "Uw winkelwagentje is momenteel leeg.");
            case "FR" ->
                    Assert.assertEquals(WaitUtils.waitUntillElementVisibility(driver, messageOnEmptyCart).getText(), "Votre panier est vide.");
        }
    }

}
