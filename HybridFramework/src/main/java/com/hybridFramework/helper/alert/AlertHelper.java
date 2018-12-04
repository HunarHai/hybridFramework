package com.hybridFramework.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class AlertHelper {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHelper object is created...");
		TestBase.logExtentReport("AlertHelper object is created...");
	}
	
	/**
	 * This method is to get Alert text.
	 * @return
	 */
	public Alert getAlert() {
		log.info("Alert text is: " + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	} 
	
	/**
	 * This method is to accept alert.
	 */
	public void acceptAlert() {
		getAlert().accept();
		log.info("Accept alert is done.");
	}
	
	/**
	 * This method is to dismiss alert.
	 */
	public void dismissAlert() {
		getAlert().dismiss();
		log.info("Dismiss alert is done.");
	}
	
	/**
	 * This method is to return alert text.
	 */
	public String getAlertText() {
		String text = getAlert().getText();
		log.info("Alert text is: " + text);
		return text;
	}
	
	/**
	 * This method is to validate whether alert is present or not.
	 * @return
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();		//	getAlert();
			log.info("Alert is present...");
			return true;
		} catch(NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}
	
	/**
	 * This method allows to accept the alert if present
	 */
	public void acceptAlertIfPresent() {
		if(isAlertPresent()) {
			acceptAlert();
		}
		else {
			log.info("Alert is not present...");
		}
	}
	
	/**
	 * This method allows to dismiss the alert if present
	 */
	public void dismissAlertIfPresent() {
		if(isAlertPresent()) {
			dismissAlert();
		}
		else {
			log.info("Alert is not present...");
		}
	}
	
	/**
	 * This method prompts the alert and sends required 'text' to accept the alert.
	 * @param text
	 */
	public void acceptPrompt(String text) {
		if(isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("Alert text: " + text);
		}
	}
	
}
