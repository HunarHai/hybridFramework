package com.hybridFramework.helper.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class ActionsHelper {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(ActionsHelper.class);


	public ActionsHelper(WebDriver driver){
		this.driver = driver;
		log.info("ActionsHelper has been initialized.");
		TestBase.logExtentReport("ActionsHelper object is created...");
	}
		
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public Actions actionsHelper(WebDriver driver){
		this.driver = driver;
		Actions actions = new Actions(this.driver);
		log.info("ActionsHelper object is created...");
		TestBase.logExtentReport("ActionsHelper object is created...");
		return actions;
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebElement toElement){
		actionsHelper(driver).moveToElement(toElement).build().perform();
		log.info("Doing mouse hover on: " +toElement.getText());
		TestBase.logExtentReport("Doing mouse hover on: " +toElement.getText());
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseHover(String toElement){
		WebElement source = driver.findElement(By.xpath("//*[contains(text),'"+toElement+"')]"));
		actionsHelper(driver).moveToElement(source).build().perform();
		log.info("Doing mouse hover on: " +toElement);
		TestBase.logExtentReport("Doing mouse hover on: " +toElement);
	}
	
	public void mouseHover(String firstXPath, int number, String secondXPath){
	//	String firstPath = firstXPath;					// "//*[@id='center_column']/ul/li[";
	//	String secondPath = secondXPath;				//	"]/div/div[2]/h5/a";
		String target = firstXPath + number + secondXPath;
		
		log.info("Doing mouse hover on product number: " +number+ " ...");
		TestBase.logExtentReport("Doing mouse hover on product number: " +number+ " ...");		
		actionsHelper(driver).moveToElement(driver.findElement(By.xpath(target))).build().perform();
	}
	
	/**
	 * 
	 * @param onElement
	 */
	public void mouseClick(WebElement onElement){
		actionsHelper(driver).click(onElement).build().perform();
		log.info("Doing Mouse click on: " +onElement.getText());
		TestBase.logExtentReport("Doing Mouse click on: " +onElement.getText());
	}
	
	/**
	 * 
	 * @param onElement
	 */
	public void mouseClick(String onElement){
		WebElement target = driver.findElement(By.xpath("//*[contains(text),'"+onElement+"')]"));
		actionsHelper(driver).click(target).build().perform();
		log.info("Doing Mouse click on: " +target.getText());
		TestBase.logExtentReport("Doing Mouse click on: " +target.getText());
	}
	
	/**
	 * 
	 * @param toElement
	 * @param onElement
	 */
	public void mouseHoverAndClick(WebElement toElement, WebElement onElement){
	//	actionsHelper(driver).moveToElement(toElement).click(onElement).build().perform();
	//	actionsHelper(driver).moveToElement(toElement).build().perform();
		mouseHover(toElement);
		mouseClick(onElement);
	}
	
	/**
	 * 
	 * @param toElement
	 * @param onElement
	 */
	public void mouseHoverAndClick(String toElement, String onElement){
	//	actionsHelper(driver).moveToElement(toElement).click(onElement).build().perform();
	//	actionsHelper(driver).moveToElement(toElement).build().perform();
		mouseHover(toElement);
		mouseClick(onElement);
	}
	
	
	
}
