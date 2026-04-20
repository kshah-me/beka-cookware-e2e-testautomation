package com.bekacookware.test;

import com.bekacookware.base.DriverFactory;
import com.bekacookware.pages.ProductFilterPage;
import com.bekacookware.pages.ProductGridPage;
import org.testng.annotations.Test;



public class VerifyNavigationCategoriesAndFilters extends DriverFactory {


    ProductGridPage productgridpage;
    ProductFilterPage productfilterpage;
	@Test
	public void verifyProductCategoriesHasNoBrokenImage() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productgridpage.HoverOnProductMenuDropDown();
        productgridpage.verifyImagesOfItemsUnderProductList();
	}

	@Test
    public void verifyCollectionCategoriesHasNoBrokenImage() {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productgridpage.HoverOnCollectionMenuDropDown();
        productgridpage.verifyImagesOfItemsUnderCollectionMenuList();
    }

	@Test
	public void verifyProductResultPageWithFilters() throws InterruptedException {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productfilterpage = new ProductFilterPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productfilterpage.scrollToFilterSection();
        productfilterpage.clickFilterByFishPannUnderFilterSection();
        productfilterpage.clickFilterMaterialButtonOnFilterSection();
        productfilterpage.clickFilterMaterialTypeAluminumButtonOnFilterSection();

	}

    @Test
    public void verifyNoRecordMessageOnResultPageWithFilters() throws InterruptedException {
        productgridpage = new ProductGridPage(DriverFactory.getDriver());
        productfilterpage = new ProductFilterPage(DriverFactory.getDriver());
        productgridpage.clickFryingPannUnderProductGridDropDown();
        productfilterpage.scrollToFilterSection();
        productfilterpage.clickFilterMaterialButtonOnFilterSection();
        productfilterpage.clickFilterMaterialTypeAluminumButtonOnFilterSection();
        productfilterpage.enterEndPriceRangeInputBox(10);
        productfilterpage.verifyNoRecordFoundMessageForAllLanguages();
    }

}
