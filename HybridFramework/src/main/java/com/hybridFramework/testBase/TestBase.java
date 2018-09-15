package com.hybridFramework.testBase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.hybridFramework.helper.browserConfiguration.BrowserType;
import com.hybridFramework.helper.browserConfiguration.ChromeBrowser;
import com.hybridFramework.helper.browserConfiguration.FirefoxBrowser;
import com.hybridFramework.helper.browserConfiguration.IExplorerBrowser;
import com.hybridFramework.helper.logger.LoggerHelper;


/**
 * This is a Base Class of the project
 * @author vasudevp
 *
 */
public class TestBase {
	
	public WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(TestBase.class);
	
	/**
	 * 
	 * @param browsertype
	 * @throws Exception 
	 */
	public void setUpDriver(BrowserType browserType) throws Exception {
		driver = getBrowserObject(browserType);
	//	log.info("Initialized WebDriver: " + driver.hashCode());	
	//	driver.manage().window().maximize();
		
	}
	
	/**
	 * 
	 * @param browserType
	 * @return
	 * @throws Exception
	 */
	public WebDriver getBrowserObject(BrowserType browserType) throws Exception {
		try {
			switch(browserType) {
			case Chrome: 
				//	get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();	// new ChromeBrowser();
				ChromeOptions chromeOptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeOptions);
			
			case Firefox:
				//	get object of FirefoxBrowser class
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions ffOptions = firefox.getFirefoxOption();
				return firefox.getFirefoxDriver(ffOptions);

			case Iexplorer:
				//	get object of IExplorerBrowser class
				IExplorerBrowser ie = IExplorerBrowser.class.newInstance();
				InternetExplorerOptions ieOptions = ie.getIExplorerOptions();
				return ie.getIExplorerDriver(ieOptions);
			
			default:
				throw new Exception("Driver not found: " + browserType.name());
			}
		}
		catch(Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		TestBase test= new TestBase();
		System.out.println("Program started.");
		test.setUpDriver(BrowserType.Chrome);
		System.out.println("Program finished.");
	}
}