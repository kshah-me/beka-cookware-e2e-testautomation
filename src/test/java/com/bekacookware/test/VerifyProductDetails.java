package com.bekacookware.test;

import com.bekacookware.base.DriverFactory;
import com.bekacookware.pages.ProductDetailsPage;
import com.bekacookware.pages.ProductGridPage;
import org.testng.annotations.Test;

public class VerifyProductDetails extends DriverFactory {

    ProductDetailsPage productdetailspage;
    ProductGridPage productgridpage;


    @Test
    public void verifyProductName() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.verifyProductNameIsNotEmpty();
    }

    @Test
    public void verifyProductPrice() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.verifyProductPrinceIsInEuro();
    }

    @Test
    public void verifyProductDescription() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.clickProductDescriptionButton();
        productdetailspage.verifyProductDescriptionIsNotEmpty();
    }

    @Test
    public void verifyPriceFormat() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.verifyProductPrinceIsNotEmpty();
        productdetailspage.verifyProductPrinceIsInEuro();
    }

    @Test
    public void verifyProductImageIsNotBroken() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.verifyProductImageIsNotBrokenOnProductDetailsPage();
    }

    @Test
    public void verifyProductGalleryImagesAreNotBroken() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productdetailspage = new ProductDetailsPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productdetailspage.clickFourthtemUnderProductOrCollectionResultPage();
        productdetailspage.verifyProductImagesGalleryOnProductDetailsPage();
    }
}
