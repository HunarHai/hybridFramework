package com.hybridFramework.testBase;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.hybridFramework.helper.browserConfiguration.BrowserType;
import com.hybridFramework.helper.browserConfiguration.ChromeBrowser;
import com.hybridFramework.helper.browserConfiguration.FirefoxBrowser;
import com.hybridFramework.helper.browserConfiguration.IExplorerBrowser;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.resource.ResourceHelper;


/**
 * This is a Base Class of the project
 * @author vasudevp
 *
 */
public class TestBase {
	
	public WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	
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
	
	
/*	
  	public static void main(String[] args) throws Exception {
		TestBase test= new TestBase();
		System.out.println("Program started.");
		test.setUpDriver(BrowserType.Chrome);
		System.out.println("Program finished.");
	}
*/
	
	/**
	 * 
	 * @param fileName
	 * @param driver
	 * @return
	 */
	public String captureScreenshot(String fileName, WebDriver driver){
		if(driver == null){
			log.info("driver is null...");
			return null;
		}
		if(fileName==""){
			fileName="blank";
		}
		
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		reportDirectory = new File(ResourceHelper.getResourcePath("/src/test/java/com/hybridFramework/screenshots/"));
		
		try{
			destFile = new File(reportDirectory + "/" + fileName + "_" +formater.format(calender.getTime()) + ".png");
			Files.copy(scrFile.toPath(), destFile.toPath());
		//	Reporter.log("<a href='" +destFile.getAbsolutePath()+ "'> <img src='" +destFile.getAbsolutePath()+ "'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return destFile.toString();
	}

	
	
}