package com.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.actions.ActionsHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.wait.WaitHelper;
import com.hybridFramework.testBase.TestBase;

public class HomePage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(HomePage.class);

	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtMenu;
	
	/**
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, womenMenu, new PropertyReader().getExplicitWait());
		
		log.info("HomePage object created...");
		TestBase.logExtentReport("Homepage object created...");
		new TestBase().getNavigationScreen(driver);
	}
	
	/**
	 * 
	 * @param data
	 */
	public void mouseHover(String toElement) {
		log.info("Mouse hovering on: " + toElement);
		new ActionsHelper(driver).mouseHover(toElement);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ProductCategoryPage clickOnItem(String toElement) {
		log.info("Clicking on: " + toElement);
		new ActionsHelper(driver).mouseClick(toElement);
		return new ProductCategoryPage(driver);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("Clicking on: " +element.getText());
		//	element.click();
		new ActionsHelper(driver).mouseHoverAndClick(element, element);
		return new ProductCategoryPage(driver);
	}
}
