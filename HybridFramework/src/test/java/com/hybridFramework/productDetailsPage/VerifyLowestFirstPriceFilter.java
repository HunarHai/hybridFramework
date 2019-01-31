package com.hybridFramework.productDetailsPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.hybridFramework.helper.assertion.AssertionHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.javascript.JavaScriptHelper;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.pageObject.HomePage;
import com.hybridFramework.pageObject.ProductCategoryPage;
import com.hybridFramework.testBase.TestBase;

public class VerifyLowestFirstPriceFilter extends TestBase {
	
	private static final Logger log = LoggerHelper.getLogger(VerifyLowestFirstPriceFilter.class);
	
	@Test(description="Verify Lowest Price products")
	public void VerifyLowestFirstPriceListInProduct_detailsPage() throws InterruptedException{
		
		log.info("Test for " + VerifyLowestFirstPriceFilter.class.getName() + " started...");
		
		getApplicationURL(new PropertyReader().getURL());
		
		HomePage homePage = new HomePage(driver);
		
		ProductCategoryPage pcatagoryPage = homePage.clickOnMenu(homePage.womenMenu);

		new JavaScriptHelper(driver).scrollIntoView(pcatagoryPage.sortBy);
		
		// select price filter
		pcatagoryPage.selectSortByFilter("Price: Lowest first");
		
/*		// wait for some time to make sure page is ready and price is sorted
		new JavaScriptHelper(driver).waitTillPageIsReady();
*/		Thread.sleep(8000);
		
		List<WebElement> price = pcatagoryPage.getAllProductsPrice();
		Iterator<WebElement> itr = price.iterator();		
		ArrayList<Integer> array = new ArrayList<Integer>();

		pcatagoryPage.getPriceMasaagedData(itr);
		boolean status = pcatagoryPage.verifyArrayHasAscendingData(array);
		
		AssertionHelper.updateTestStatus(status);
	}

}
