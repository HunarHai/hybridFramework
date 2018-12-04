package com.hybridFramework.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

public class AssertionHelper {

	/**
	 * Logger object has to be a 'static'. 
	 * Inside the static method, non-static logger can not be called.
	 */
	private static final Logger log = LoggerHelper.getLogger(AssertionHelper.class);
	
	/**
	 * This method is to verify TextEquals or not 
	 * @param element
	 * @param expectedText
	 * @return
	 */
	public static boolean verifyTextEquals(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if(actualText.equals(expectedText)){
				log.info("Actual Text: " + actualText + " equals with Expected Text: " +expectedText);
				TestBase.logExtentReport("Actual Text: " + actualText + " equals with Expected Text: " +expectedText);
				return flag = true;
			}
			else {
				log.info("Actual Text: " + actualText + " not equals with Expected Text: " +expectedText);
				TestBase.logExtentReport("Actual Text: " + actualText + " not equals with Expected Text: " +expectedText);
				return flag;
			}
		} catch(Exception e) {
			log.info("Actual Text is: " + element.getText() + " AND Expected Text is: " +expectedText);
			TestBase.logExtentReport("Actual Text is: " + element.getText() + " AND Expected Text is: " +expectedText);
			return flag;
		}		
	}
	
	/**
	 * This method asserts that two strings are equal or not
	 * @param s1
	 * @param s2
	 */
	public static void verifyText(String s1, String s2) {
		log.info("Verifying text: "+ s1 + " with " + s2);
		Assert.assertEquals(s1, s2);
	}
	
	/**
	 * This method asserts that a condition is true. 
	 * If not, an AssertionError is thrown.
	 */
	public static void makeTrue() {
		log.info("Making the script PASS...");
		Assert.assertTrue(true);
	}
	
	/**
	 * This method asserts that a condition is true. 
	 * If not, an AssertionError, with the given message, is thrown.
	 * @param message
	 */
	public static void makeTrue(String message) {
		log.info("Making the script PASS..." + message);
		Assert.assertTrue(true, message);
	}
	
	/**
	 * This method asserts that a condition is false. 
	 * If not, an AssertionError is thrown.
	 */
	public static void makeFalse() {
		log.info("Making the script FAIL...");
		Assert.assertTrue(false);
	}
	
	/**
	 * This method asserts that a condition is false. 
	 * If not, an AssertionError, with the given message, is thrown.
	 * @param message
	 */
	public static void makeFalse(String message) {
		log.info("Making the script FAIL..." + message);
		Assert.assertTrue(false, message);
	}
	
	/**
	 * This method is to help in verifying the another method is returning 'True'
	 * @param status
	 */
	public static void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}
	
	/**
	 * This method is to help in verifying the another method is returning 'False'
	 * @param status
	 */
	public static void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}

	/**
	 * This method asserts that an object is null. If it is not, an AssertionError is thrown.
	 * @param s1
	 */
	public static void verifyNull(String s1) {
		log.info("Verifying object is null...");
		Assert.assertNull(s1);
	}
	
	/**
	 * This method asserts that an object is NOT null. If it is not, an AssertionError is thrown.
	 * @param s1
	 */
	public static void verifyNotNull(String s1) {
		log.info("Verifying object is null...");
		Assert.assertNotNull(s1);
	}
	
	/**
	 * 
	 */
	public static void pass() {
		Assert.assertTrue(true);
	}
	
	/**
	 * 
	 */
	public static void fail() {
		Assert.assertTrue(false);
	}
	
	/**
	 * 
	 * @param status
	 */
	public static void updateTestStatus(boolean status) {
		if(status) {
			pass();
		}
		else {
			fail();
		}
	}
	
}
