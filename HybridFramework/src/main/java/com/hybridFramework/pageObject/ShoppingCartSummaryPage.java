package com.hybridFramework.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.assertion.VerificationHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.wait.WaitHelper;
import com.hybridFramework.testBase.TestBase;

public class ShoppingCartSummaryPage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='columns']/div[1]/span[2]")
	public WebElement yourShoppingCart;
	
	@FindBy(xpath="//a[@title='Delete']")
	List<WebElement> quantity_delete;
	
	@FindBy(xpath="//*[contains(text(),'Your shopping cart is empty')]")
	public WebElement emptyShoppingCartMsg;
	
	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourShoppingCart, new PropertyReader().getExplicitWait());
		
		log.info("RegistrationPage Object created...");
		TestBase.logExtentReport("RegistrationPage Object created...");
		new TestBase().getNavigationScreen(driver);
	}

	public boolean verifyProduct(String prod){
		log.info("Selecting product...");
		TestBase.logExtentReport("Selecting product...");
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'"+prod+"')]"));
		return new VerificationHelper(driver).isDisplayed(element);
	}
	
	
	public void deleteProductFromShoppingCart() throws InterruptedException{
		log.info("Deleting all products from shopping cart...");
		TestBase.logExtentReport("Deleting all products from shopping cart...");
		List<WebElement> deletes = quantity_delete;
		Iterator<WebElement> itr = deletes.iterator();
		while(itr.hasNext()){
			itr.next().click();
			Thread.sleep(7200);
		}
	}
	
	public boolean verifyEmptyShoppingCartMsg(){
		log.info("Verifying deleted shopping cart message...");
		TestBase.logExtentReport("Verifying deleted shopping cart message...");
		return new VerificationHelper(driver).isDisplayed(emptyShoppingCartMsg);
	} 
	
}
