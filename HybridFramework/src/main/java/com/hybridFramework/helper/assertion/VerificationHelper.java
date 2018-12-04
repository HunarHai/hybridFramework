package com.hybridFramework.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

public class VerificationHelper {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
		log.info("VerificationHelper object is created...");
		TestBase.logExtentReport("VerificationHelper object is created...");
	}
	
	/**
	 * This method is to return element is displayed
	 * @param element
	 * @return
	 */
	public static boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is Displayed..." + element.getText());
			TestBase.logExtentReport("Element is Displayed..." + element.getText());
			return true;
		} catch(Exception e) {
		//	log.error("Element is not Displayed..." + e.getMessage());
			log.error("Element is not Displayed..." + e.getCause());
			TestBase.logExtentReport("Element is not Displayed..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is NOT displayed
	 * @param element
	 * @return
	 */
	public static boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is Displayed..." + element.getText());
			TestBase.logExtentReport("Element is Displayed..." + element.getText());
			return false;
		} catch(Exception e) {
			log.error("Element is not Displayed..." + e.getCause());
			TestBase.logExtentReport("Element is not Displayed..." + e.getCause());
			return true;
		}
	}
	
	/**
	 * This method is to return element is enabled
	 * @param element
	 * @return
	 */
	public static boolean isEnabled(WebElement element) {
		try {
			element.isEnabled();
			log.info("Element is Enabled..." + element.getText());
			TestBase.logExtentReport("Element is Enabled..." + element.getText());
			return true;
		} catch(Exception e) {
			log.error("Element is not Enabled..." + e.getCause());
			TestBase.logExtentReport("Element is not Enabled..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is NOT enabled
	 * @param element
	 * @return
	 */
	public static boolean isNotEnabled(WebElement element) {
		try {
			element.isEnabled();
			log.info("Element is Enabled..." + element.getText());
			TestBase.logExtentReport("Element is Enabled..." + element.getText());
			return false;
		} catch(Exception e) {
			log.error("Element is not Enabled..." + e.getCause());
			TestBase.logExtentReport("Element is not Enabled..." + e.getCause());
			return true;
		}
	}
	
	/**
	 * This method is to return element is Selected
	 * @param element
	 * @return
	 */
	public static boolean isSelected(WebElement element) {
		try {
			element.isSelected();
			log.info("Element is Selected..." + element.getText());
			TestBase.logExtentReport("Element is Selected..." + element.getText());
			return true;
		} catch(Exception e) {
			log.error("Element is not Selected..." + e.getCause());
			TestBase.logExtentReport("Element is not Selected..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is NOT Selected
	 * @param element
	 * @return
	 */
	public static boolean isNotSelected(WebElement element) {
		try {
			element.isSelected();
			log.info("Element is Selected..." + element.getText());
			TestBase.logExtentReport("Element is Selected..." + element.getText());
			return false;
		} catch(Exception e) {
			log.error("Element is not Selected..." + e.getCause());
			TestBase.logExtentReport("Element is not Selected..." + e.getCause());
			return true;
		}
	}
	
	/**
	 * This method is to read value/text from displayed element
	 * @param element
	 * @return
	 */
	public static String getText(WebElement element) {
		if(null == element) {
			log.info("Element is null...");
			TestBase.logExtentReport("Element is null...");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("Element text is: " + element.getText());
			TestBase.logExtentReport("Element text is: " + element.getText());
			return element.getText();
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is to provide web page title
	 * @return
	 */
	public String getTitle() {
		log.info("Page Title is: " + driver.getTitle());
		TestBase.logExtentReport("Page Title is: " + driver.getTitle());
		return driver.getTitle();
	}
	
}
