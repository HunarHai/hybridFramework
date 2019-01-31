package com.hybridFramework.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.hybridFramework.helper.assertion.AssertionHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.pageObject.HomePage;
import com.hybridFramework.pageObject.LoginPage;
import com.hybridFramework.pageObject.ProductCategoryPage;
import com.hybridFramework.testBase.TestBase;

public class VerifyProductsCount extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(VerifyProductsCount.class);
	LoginPage loginPage;
	HomePage homePage;
	
	@Test
	public void testVerifyProductsCount() {
		log.info(VerifyProductsCount.class.getName() + " started...");
		
		driver.get(new PropertyReader().getURL());
		
		homePage = new HomePage(driver);
		ProductCategoryPage pCate = homePage.clickOnMenu(homePage.womenMenu);
		
		pCate.selectColor(pCate.Orange);
		int count = pCate.getTotalProducts();
		
		if(count==3) {
			log.info("product count is matching...");
		}
		else {
			AssertionHelper.makeFalse("product count is not matching...");
		}
	}
}
