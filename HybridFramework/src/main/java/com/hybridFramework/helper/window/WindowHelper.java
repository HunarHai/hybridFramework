package com.hybridFramework.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.testBase.TestBase;

public class WindowHelper {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(WindowHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize 
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public WindowHelper(WebDriver driver){
		this.driver = driver;
		log.info("WindowHelper object created...");
		TestBase.logExtentReport("WindowHelper object is created...");
	}
	/**
	 * This method will switch to Parent window
	 */
	public void switchToParentWindow(){
		log.info("Switching to Parent window...");
		driver.switchTo().defaultContent();
		log.info("Switched to Parent window.");
	}
	
	/**
	 * This method will switch to child window based on index
	 * @param index
	 */
	public void switchToWindow(int index){
		Set<String> windows = driver.getWindowHandles();
		int i=1;
		for (String window : windows) {
			if(i == index){
				log.info("Switching to "+ index +" window.");
				driver.switchTo().window(window);
				log.info("Switched to "+ index +" window.");
			}
			else{
				i++;
			}
		}
	}

	/**
	 * This method will close all tab window 
	 * AND switch to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow(){
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		for (String window : windows) {
			if(!window.equalsIgnoreCase(mainWindow)){
				driver.close();
			}
		}
		log.info("Switching to Main window.");
		driver.switchTo().window(mainWindow);
		log.info("Switched to Main window "+ mainWindow);
	}
	
	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack(){
		log.info("Navigating Browser Back...");
		driver.navigate().back();
		log.info("Navigated Browser Back.");
	}
	
	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward(){
		log.info("Navigating Browser Forward...");
		driver.navigate().forward();
		log.info("Navigated Browser Forward.");
	}
	
	public void refresh() {
		log.info("Refreshing Browser...");
		driver.navigate().refresh();
		log.info("Browser Refreshed...");
	}
}
