package com.hybridFramework.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hybridFramework.helper.browserConfiguration.BrowserType;
import com.hybridFramework.helper.browserConfiguration.ChromeBrowser;
import com.hybridFramework.helper.browserConfiguration.FirefoxBrowser;
import com.hybridFramework.helper.browserConfiguration.IExplorerBrowser;
import com.hybridFramework.helper.config.ObjectReader;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.excel.ExcelHelper;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.resource.ResourceHelper;
import com.hybridFramework.utils.ExtentManager;


/**
 * This is a Base Class of the project
 * @author vasudevp
 *
 */
public class TestBase {

	private static final Logger log = LoggerHelper.getLogger(TestBase.class);
	public static WebDriver driver;
	public static File imageLocation;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ExcelHelper excelHelper;
	
	/**
	 * 
	 * @param browsertype
	 * @throws Exception 
	 */
	public void setUpDriver(BrowserType browserType) throws Exception {
		driver = getBrowserObject(browserType);
		log.info("Initialized WebDriver: " + driver.hashCode());	
		driver.manage().window().maximize();
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
				log.info("Chrome browser object created...");
				return chrome.getChromeDriver(chromeOptions);
			
			case Firefox:
				//	get object of FirefoxBrowser class
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions ffOptions = firefox.getFirefoxOption();
				log.info("Firefox browser object created...");
				return firefox.getFirefoxDriver(ffOptions);

			case Iexplorer:
				//	get object of IExplorerBrowser class
				IExplorerBrowser ie = IExplorerBrowser.class.newInstance();
				InternetExplorerOptions ieOptions = ie.getIExplorerOptions();
				log.info("IExplorer browser object created...");
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
	
	/**
	 * 
	 * @param fileName
	 * @param driver
	 * @return
	 */
	public String captureScreenshot(String imageName, WebDriver driver){
		if(driver == null){
			log.info("driver is null...");
			return null;
		}
		if(imageName==""){
			imageName="blank";
		}
		
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String actualImageName = imageLocation + "/" + imageName + "_" +formater.format(calender.getTime()) + ".png";
		try{
			destFile = new File(actualImageName);
			Files.copy(scrFile.toPath(), destFile.toPath());
		//	Reporter.log("<a href='" +destFile.getAbsolutePath()+ "'> <img src='" +destFile.getAbsolutePath()+ "'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		log.info("Screenshot image " +actualImageName+ " captured...");
		return actualImageName;
	}

	/**
	 * 
	 * @param driver
	 */
	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing screenshot for UI navigation...");
		String screen = captureScreenshot("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 */
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
		log.info("ExtentManager instance created before suite...");
	}
		
	/**
	 * 
	 */
	@BeforeTest
	public void beforeTest() {
		ObjectReader.reader = new PropertyReader();
		imageLocation = new File(ResourceHelper.getResourcePath("/src/test/java/com/hybridFramework/screenshots"));
		try {
			setUpDriver(ObjectReader.reader.getBrowserType());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());
	}

	/**
	 * 
	 * @param method
	 */
	@BeforeMethod()
	public void beforeMethod(Method method){
	//	test = extent.startTest(result.getName());
		test.log(Status.INFO, method.getName() + "() test started...");
	}
	
	/**
	 * 
	 * @param result
	 */
	public void getResult(ITestResult result) {
		if(result.getStatus() ==  ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			test.log(Status.FAIL, result.getName() + " is FAILED...");
			
			try {
				String imagePath = captureScreenshot(result.getName(), driver);
				test.addScreenCaptureFromPath(imagePath);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is PASSED...");
			test.log(Status.PASS, result.getName()+"() test is completed.");
			try {
				String imagePath = captureScreenshot(result.getName(), driver);
				test.addScreenCaptureFromPath(imagePath);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
			test.log(Status.SKIP, result.getName()+"() test is skipped.");
		}
		else if(result.getStatus() == ITestResult.STARTED) {
			test.log(Status.INFO, result.getName() + "() test is started... ");
		}
		extent.flush();
	}
	
	/**
	 * 
	 * @param result
	 */
	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getResult(result);
	}
	
	/**
	 * 
	 */
	@AfterTest
	public void afterTest() {
		if(driver!=null){
			driver.quit();
		}
	//	extent.endTest(test);
	//	extent.flush();
	}
	
	/**
	 * 
	 * @param str
	 */
	public static void logExtentReport(String str) {
		test.log(Status.INFO, str);
	}
	
	/**
	 * 
	 * @param url
	 */
	public void getApplicationURL(String url) {
		driver.get(url);
		log.info("Getting application thru... " +url);
		logExtentReport("Getting application thru... " +url);
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		if(locatorType.equalsIgnoreCase("id"))
			return driver.findElement(By.id(locatorValue));
		
		else if(locatorType.equalsIgnoreCase("name"))
			return driver.findElement(By.name(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("classname")) || (locatorType.equalsIgnoreCase("class"))) 
			return driver.findElement(By.className(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("tagname")) || (locatorType.equalsIgnoreCase("tag"))) 
			return driver.findElement(By.tagName(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("linktext")) || (locatorType.equalsIgnoreCase("link"))) 
			return driver.findElement(By.linkText(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("partiallinktext")) || (locatorType.equalsIgnoreCase("partiallink"))) 
			return driver.findElement(By.partialLinkText(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("cssSelector")) || (locatorType.equalsIgnoreCase("css"))) 
			return driver.findElement(By.cssSelector(locatorValue));
		
		else if(locatorType.equalsIgnoreCase("xpath")) 
			return driver.findElement(By.xpath(locatorValue));
		
		else 
			throw new Exception("Unknown locator type: " +locatorType);
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		if(locatorType.equalsIgnoreCase("id"))
			return driver.findElements(By.id(locatorValue));
		
		else if(locatorType.equalsIgnoreCase("name"))
			return driver.findElements(By.name(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("classname")) || (locatorType.equalsIgnoreCase("class"))) 
			return driver.findElements(By.className(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("tagname")) || (locatorType.equalsIgnoreCase("tag"))) 
			return driver.findElements(By.tagName(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("linktext")) || (locatorType.equalsIgnoreCase("link"))) 
			return driver.findElements(By.linkText(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("partiallinktext")) || (locatorType.equalsIgnoreCase("partiallink"))) 
			return driver.findElements(By.partialLinkText(locatorValue));
		
		else if((locatorType.equalsIgnoreCase("cssSelector")) || (locatorType.equalsIgnoreCase("css"))) 
			return driver.findElements(By.cssSelector(locatorValue));
		
		else if(locatorType.equalsIgnoreCase("xpath")) 
			return driver.findElements(By.xpath(locatorValue));
		
		else 
			throw new Exception("Unknown locator type: " +locatorType);
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement getWebElement(String locator) throws Exception {
		return getLocator(PropertyReader.OR.getProperty(locator));
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(PropertyReader.OR.getProperty(locator));
	}
	
	/**
	 * 
	 * @param excelName
	 * @param sheetName
	 * @return
	 */
	public Object[][] getData(String excelName, String sheetName) {
		
		String excelLocation = ResourceHelper.getResourcePath("/src/main/resources/data/" +excelName);
	//	System.out.println(excelLocation);
		excelHelper = new ExcelHelper();
		return excelHelper.getExcelData(excelLocation, sheetName);
	}
	
/*
	public static void main(String[] args) throws Exception {
		TestBase test = new TestBase();
		PropertyReader p = new PropertyReader();
		test.setUpDriver(p.getBrowserType());
	//	String temp = PropertyReader.OR.getProperty("userName");
	//	System.out.println(temp);
		String temp1 = p.getURL();
		test.getApplicationURL(temp1);
		test.captureScreenshot("Temp", driver);
	}
	
*/	
}