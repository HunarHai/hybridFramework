package com.hybridFramework.helper.wait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hybridFramework.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WaitHelper object: "+ this.driver.hashCode() +" created...");
	}
	
	/**
	 * This is implicit wait method
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
	//	driver.manage().timeouts().implicitlyWait(timeout, unit);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
		log.info("Implicit wait has been set to " +timeout+ " second..." );
	}
	
	/**
	 * This is help us to get WebDriverWait object
	 * @param timeOutInSeconds
	 * @param pllingEveryInSeconds
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	private WebDriverWait getWait(int timeOutInSeconds, int pllingEveryInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pllingEveryInSeconds, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	/**
	 * This method will make sure element is visible with Polling Time
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryInSec){
		log.info("Waiting for :"+ element.toString() +" for: " + timeOutInSeconds +" seconds.");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInSec);		
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now.");
	}
	
	/**
	 * This method will make sure elementToBeClickable
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementToBeclickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for :" +element.toString()+ " till " +timeOutInSeconds+ " seconds.." );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now..");
	}
	
	/**
	 * This method will make sure invisibility of element
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for :" +element.toString()+ " till " +timeOutInSeconds+ " seconds..");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		log.info("Element is invisible now..");
		return status;
	}
	
	/**
	 * This method will wait for FrameToBeAvailableAndSwitchToIt
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForFrameToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for :" +element.toString()+ " till " +timeOutInSeconds+ " seconds..");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is available and switched..");
	}
	
	/**
	 * This method will give us the Wait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInSeconds
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInSeconds) {
		Wait<WebDriver> flWait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(pollingEveryInSeconds, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return flWait;
	}
	
	/**
	 * This method will make sure element is visible with FluentWait object
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInSec
	 * @return
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInSec) {
		Wait<WebDriver> fWait = getFluentWait(timeOutInSeconds, pollingEveryInSec);
		fWait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/**
	 * This method will make sure element is visible with Wait object
	 * @param driver
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {
		log.info("Waiting for :" +element.toString()+ " till " +timeOutInSeconds+ " seconds..");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element found... " + element.getText());
	}
	
	/**
	 * This method will make sure the page to get load 
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadTime(long timeout, TimeUnit unit){
		log.info("Waiting for page to load for: " + timeout + " seconds.");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("Page is loaded.");
	}
	
	/**
	 * This method will make sure the element to be visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds){
		log.info("Waiting for: " + element.getText() + " for " + timeOutInSeconds + " second");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now.");
	}
	
}
