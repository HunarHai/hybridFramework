package com.hybridFramework.helper.javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class JavaScriptHelper {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize 
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialized..." + this.driver.hashCode());
		TestBase.logExtentReport("JavaScriptHelper object is created...");
	}
	
	/**
	 * This method will execute javaScript query having single argument
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}
	
	/**
	 * This method will execute javaScript query having multiple arguments
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}
	
	/**
	 * This method will scroll till element given as per X and Y coordinates
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		log.info("Scrolling to WebElement... " + element.toString());
		executeScript("window.scrollTo(arguments[0],arguments[1])",element.getLocation().x, element.getLocation().y);
		log.info("Scrolled till " + element.getLocation().x + "," + element.getLocation().y + " for element " +element.toString());
	}
	
	/**
	 * This method will click on element
	 * @param element
	 */
	public void clickElement(WebElement element) {
		log.info("Clicking on element..." + element.toString());
		executeScript("argument[0].click();",element);
		log.info("Clicked on element " + element.toString());
	}
	
	/**
	 * This method will scroll to element and click
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		log.info("Scrolling to element " + element.toString());
		scrollToElement(element);
		element.click();		//	clickElement(element);
		log.info("Scrolled to element "+element.toString()+" and clicked.");
	}
	
	/**
	 * This method will scrollIntoView 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		log.info("Scrolling to element " + element.toString());
		executeScript("argument[0].scrollIntoView()", element);
		log.info("Scrolled till element: "+ element.toString() + " and viewed.");
	}
	
	/**
	 * This method will scrollIntoView and click
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		log.info("Scrolling to element..." + element.toString());
		scrollIntoView(element);
		element.click();		//	clickElement(element);
		log.info("Clicked on the element: "+element.toString()+" after scrolling.");
	}
	
	/**
	 * This method will scroll down till bottom
	 */
	public void scrollDownVertically() {
		log.info("Scrolling Down Vertically...");
		executeScript("window.scrollBy(0,document.body.scrollHeight)");
		log.info("Scrolled Down till bottom.");
	}
	
	/**
	 * This method will scroll up to top
	 */
	public void scrollUpVertically() {
		log.info("Scrolling Up Vertically...");
		executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		log.info("Scrolled Up till bottom.");
	}
	
	/**
	 * This  method will scroll down till given pixel (e.g., pixel = 1500)
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		log.info("Scrolling Down Vertically till pixel: (0,"+pixel+").");
		executeScript("window.scrollBy(0,"+pixel+")");
		log.info("Scrolled Down Vertically till pixel: (0,"+pixel+").");
	}
	
	/**
	 * This  method will scroll up till given pixel
	 * @param pixel
	 */
	public void scrollUpByPixel(int pixel) {
		log.info("Scrolling Down Vertically till pixel: (0,"+pixel+").");
		executeScript("window.scrollBy(0,-"+pixel+")");
		log.info("Scrolled Down Vertically till pixel: (0,"+pixel+").");
	}
	
	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomBy100Percentage() {
		log.info("Page view zoom to 100%.");
		executeScript("document.body.style.zoom='100%'");
	}
	
	/**
	 * This method will zoom screen by 40%
	 */
	public void zoomInBy40Percentage() {
		log.info("Page view zoom to 40%.");
		executeScript("document.body.style.zoom='40%'");
	}
	
	/**
	 * This method will Refresh the browser page
	 */
	public void refresh(){
		log.info("Refreshing the browser window...");
		executeScript("history.go(0)");
	}
	
	/**
	 * This method will return if page is in complete ready state and loaded
	 */
/*	public void waitTillPageIsReady() {
		log.info("Confirming that page is loaded completely...");
		executeScript("return document.readyState").equals("complete");
		log.info("Page is in complete readyState...");
	}*/
	
}
