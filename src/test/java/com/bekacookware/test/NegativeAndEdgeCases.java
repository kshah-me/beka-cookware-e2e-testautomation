package com.bekacookware.test;

import com.bekacookware.base.DriverFactory;
import com.bekacookware.pages.*;
import org.testng.annotations.Test;


public class NegativeAndEdgeCases extends DriverFactory {


    HomePage homepage;
    ProductGridPage productgridpage;
    ProductDetailsPage productdetailspage;
    CartPage cartpage;
    CheckoutPage checkout;

    @Test
    public void verifyErrorMessageForInvalidUserName() {

        homepage = new HomePage(DriverFactory.getDriver());
        homepage.clickOnAccountIconButton();
        homepage.EnterUserEmailId("test.com");
        homepage.clickOnLoginButton();
        homepage.verifyErrorMessageForInvalidUserName();

    }

    @Test
    public void verifyErrorMessageForInvalidVoucherCode() throws InterruptedException {
        homepage = new HomePage(DriverFactory.getDriver());
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        cartpage = new CartPage(DriverFactory.getDriver());
        checkout = new CheckoutPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        cartpage.clickAddToCartButton();
        homepage.clickCloseButtonOnAddPopUp();
        cartpage.clickCheckoutButtonOnCartPage();
        checkout.enterVoucherCodeOnCheckoutPage("ABC");
        checkout.clickVoucherCodeRedeemButtonOnCheckoutPage();
        checkout.verifyErrorMessageForInvalidVoucherCodeOnCheckoutPage();
    }

}
