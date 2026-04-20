package com.bekacookware.test;

import com.bekacookware.base.DriverFactory;
import com.bekacookware.pages.CartPage;
import com.bekacookware.pages.ProductDetailsPage;
import com.bekacookware.pages.ProductGridPage;
import org.testng.annotations.Test;

public class VerifyCartDetails extends DriverFactory {

    ProductDetailsPage productdetailspage;
    ProductGridPage productgridpage;
    CartPage cartpage;

    @Test()
    public void addProductIntoCart() throws InterruptedException {
        cartpage = new CartPage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickThirdItemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.clickCloseOpenCartPupupButton();
        cartpage.verifyCountOnCartButton(2);
    }

    @Test()
    public void verifyProductCountAndPriceOnCartDetailsPage() throws InterruptedException {
        cartpage = new CartPage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickSecondItemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.clickCloseOpenCartPupupButton();
        productgridpage.clickChefProductUnderCollectionGridDropDown();
        productdetailspage.clickThirdItemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.verifyCountOnOpenCartPage(4);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();
    }

    @Test()
    public void verifyUpdatedProductCountAndPriceOnCartDetailsPage() throws InterruptedException {
        cartpage = new CartPage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickThirdItemUnderProductOrCollectionResultPage();
        cartpage.clickAddToCartButton();
        cartpage.verifyCountOnOpenCartPage(1);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();
        cartpage.clickCloseOpenCartPupupButton();
        productgridpage.clickChefProductUnderCollectionGridDropDown();
        productdetailspage.clickSecondItemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.clickIncrementItemCountButtonCartPopUp();
        Thread.sleep(5000);
        cartpage.verifyCountOnOpenCartPage(4);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();
    }

    @Test()
    public void verifyTotalPriceOnCartDetailsPageAfterRemovingProduct() throws InterruptedException {
        cartpage = new CartPage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        cartpage.clickAddToCartButton();
        cartpage.verifyCountOnOpenCartPage(1);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();
        cartpage.clickCloseOpenCartPupupButton();
        productgridpage.clickChefProductUnderCollectionGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.clickDeleteButtonOnOpenCartPage();
        Thread.sleep(3000);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();

    }


    @Test()
    public void verifyZeroProductCountOnCartDetailsPageAfterDeletingProduct() throws InterruptedException {
        cartpage = new CartPage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickIncrementItemCountButtonOnProductDetailsPage();
        cartpage.clickAddToCartButton();
        cartpage.verifyCountOnOpenCartPage(3);
        cartpage.verifyPriceOfEachItemUnderCartInEuro();
        cartpage.verifyPriceOfItemPerCountUnderCart();
        cartpage.verifyTotalPriceIsSumOfEachItemPrice();
        cartpage.clickDeleteButtonOnOpenCartPage();
        Thread.sleep(3000);
        cartpage.verifyCountOnOpenCartPage(0);
        cartpage.verifyMessageOnEmptyCart();

    }
}
