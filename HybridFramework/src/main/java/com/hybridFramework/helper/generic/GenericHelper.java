package com.hybridFramework.helper.generic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.hybridFramework.helper.logger.LoggerHelper;

public class GenericHelper {

	/**
	 * Logger object has to be a 'static'. 
	 * Inside the static method, non-static logger can not be called.
	 */
	private static final Logger log = LoggerHelper.getLogger(GenericHelper.class);
	
	/**
	 * 
	 * @param ele
	 * @return
	 */
	public String readValueOfElement(WebElement ele) {
		if(null == ele) {
			log.info("Webelement is null.");
			return null;
		}
		
		boolean displayed = false;
		try{
			displayed = isDisplayed(ele);
		} catch(Exception e) {
			log.error(e);
			return null;
		}
		
		if(!displayed) 
			return null;
			
		String text = ele.getText();	
		log.info("Webelement value is: " + text);

		return text;
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	public String readValueFromInput(WebElement ele) {
		if(null == ele) {
			log.info("Webelement is null.");
			return null;
		}
				
		if(!isDisplayed(ele)) {
			return null;			
		}
		
		String value = ele.getAttribute("value");
		log.info("Webelement value is: " + value);

		return value;
	}
	
	/**
	 * 
	 * @param ele
	 * @return
	 */
	public boolean isDisplayed(WebElement ele) {
		try {
			ele.isDisplayed();
			log.info("Element is displayed..."+ele);
			return true;
		} catch(Exception e) {
			log.info(e);
			return false;
		}
	}
	
	/**
	 * 
	 * @param ele
	 * @return
	 */
	public boolean isNotDisplayed(WebElement ele) {
		try {
			ele.isDisplayed();
			log.info("Element is displayed..."+ele);
			return false;
		} catch(Exception e) {
			log.info(e);
			return true;
		}
	}	
	
	/**
	 * 
	 * @param ele
	 * @return
	 */
	public String getDisplayText(WebElement ele) {
		if(null == ele) {
			return null;
		}
		if(!isDisplayed(ele)){
			return null;
		}
		
		return ele.getText();
	}
	
	/**
	 * 
	 * @param ele
	 * @return
	 */
	public static synchronized String getElementText(WebElement ele) {
		if(null == ele) {
			log.info("Webelement is null...");
			return null;
		}
		
		String elementText = null;
		try {
			elementText = ele.getText();
		} catch(Exception e) {
			log.info("Element not found... " + e);
		}
		return elementText;
	}
	
	
}
