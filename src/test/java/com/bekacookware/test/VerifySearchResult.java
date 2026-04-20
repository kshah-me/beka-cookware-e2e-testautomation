package com.bekacookware.test;

import com.bekacookware.base.DriverFactory;
import com.bekacookware.pages.*;
import org.testng.annotations.Test;

public class VerifySearchResult extends DriverFactory {

    SearchPage searchpage;

    @Test
    public void verifyProductAsHeaderDisplayedInSearchedProductList() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("Pan");
        searchpage.verifyProductAsHeaderInSearchResult();
    }

    @Test
    public void verifySearchedProductListHasPrice() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("water");
        searchpage.verifySearchResultItemHasProductPrice();
    }

    @Test
    public void verifySearchedProductListHasNoBrokenLink() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("fork");
        searchpage.verifySearchResultItemHasNoBrokenLink();
    }

    @Test
    public void verifySearchedProductListHasNoBrokenImage() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("Glass");
        searchpage.verifySearchResultItemHasNoBrokenImg();
    }

    @Test
    public void verifyMessageForNoResultFoundOnSearchedProductPage() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("ABC");
        searchpage.clickSearchSubmitButtonOnHomePage();
        searchpage.verifyNoSearchResultMessageOnHomePage();
    }

    @Test
    public void verifySearchedProductResultPageHasNoBrokenImage() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("Plate");
        searchpage.clickSearchSubmitButtonOnHomePage();
        searchpage.verifyProductSearchResultPageHasNoBrokenImage();
    }

    @Test
    public void verifySearchedProductResultPageHasProductNameAsLinkAndNotEmpty() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("Plate");
        searchpage.clickSearchSubmitButtonOnHomePage();
        searchpage.verifyProductSearchResultPageHasProductNameAslinkAndNotEmpty();
    }

    @Test
    public void verifySearchedProductResultPageHasProductDescriptionNotEmpty() throws InterruptedException {
        searchpage = new SearchPage(DriverFactory.getDriver());
        searchpage.clickSearchButtonOnHomePage();
        searchpage.enterSearchInputBoxOnHomePage("Plate");
        searchpage.clickSearchSubmitButtonOnHomePage();
        searchpage.verifyProductSearchResultPageHasProductDescriptionNotEmpty();
    }

}
