package com.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.config.ObjectReader;
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
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, womenMenu, ObjectReader.reader.getExplicitWait());
		
		log.info("HomePage object created...");
		TestBase.logExtentReport("Homepage object created...");
		new TestBase().getNavigationScreen(driver);
	}
	
	/**
	 * 
	 * @param data
	 */
	public void mouseHover(String data) {
		log.info("Mouse hovering on: " + data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"']"))).build().perform();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ProductCategoryPage clickOnItem(String data) {
		log.info("Clicking on: " + data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"']")).click();
		return new ProductCategoryPage(driver);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("Clicking on: " +element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}
	
}
