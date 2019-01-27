package com.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.assertion.VerificationHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.wait.WaitHelper;
import com.hybridFramework.testBase.TestBase;

public class LoginPage {
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(LoginPage.class);

	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	public WebElement signIn;

	@FindBy(xpath = "//*[@id='email']")
	public WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id='passwd']")
	public WebElement password;
	
	@FindBy(xpath = "//*[@id='SubmitLogin']")
	public WebElement btnLogin;

	// Below elements noted here just For understanding the Page Object
	@FindBy(xpath = "//*[@id='center_column']/p")
	public WebElement successMsgObject;

	@FindBy(xpath = "//*[@id='email_create']")
	public WebElement registrationEmailAddress;

	@FindBy(xpath = "//*[@id='SubmitCreate']")
	public WebElement btnCreateAnAccount;


	/**
	 * This will make sure that object of this page is loaded and page
	 * WebElements are initialized
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(signIn, new PropertyReader().getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		
		log.info("LoginPage object created...");
		TestBase.logExtentReport("LoginPage object created...");
	}

	/**
	 * 
	 */
	public void clickOnSignInLink() {
		log.info("Clicked on SignIn Link...");
		TestBase.logExtentReport("Clicked on SignIn Link...");
		signIn.click();
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void enterEmailAddress(String emailAddress) { // (String emailId)
		log.info("Entering emailAddress... " + emailAddress); // +emailId
		TestBase.logExtentReport("Entering emailAddress... " + emailAddress);
		this.emailAddress.sendKeys(emailAddress); // emailAddress.sendKeys(emailId);
	}

	/**
	 * 
	 * @param password
	 */
	public void enterPassword(String password) {
		log.info("Entering password..." +password);
		TestBase.logExtentReport("Entering password..." +password);
		this.password.sendKeys(password);
	}
	
	/**
	 * 
	 * @return
	 */
	public HomePage clickOnLogin() {
		log.info("Clicking on Login button...");
		TestBase.logExtentReport("Clicking on Login button...");
		// new JavaScriptHelper(driver).scrollDownVertically();
		btnLogin.click();
		// After clicking the Login button, application navigate to Home page
		// So return type is HomePage object as - new HomePage(driver)
		return new HomePage(driver);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean verifyLoginSuccessMsg() {
		return new VerificationHelper(driver).isDisplayed(successMsgObject);
	}
	
	/**
	 * 
	 */
	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis() + "@gmail.com";
		log.info("Entering Registration Email Address: " + email);
		TestBase.logExtentReport("Entering Regisrtation Email Address: " + email);
		this.registrationEmailAddress.sendKeys(email);
	}

	/**
	 * 
	 * @return
	 */
	public RegistrationPage clickOnCreateAnAccount() {
		log.info("Clicking on Create an Account button...");
		TestBase.logExtentReport("Clicking on Create an Account button...");
		// new JavaScriptHelper(driver).scrollIntoView(btnCreateAnAccount);
		btnCreateAnAccount.click();
		return new RegistrationPage(driver);
	}
	
	/**
	 * 
	 * @param emailAddress
	 * @param password
	 */
	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnLogin();
	}
	
	/**
	 * 
	 */
	public void goToUserRegistrtion() {
		clickOnSignInLink();
		enterRegistrationEmail();
		clickOnCreateAnAccount();
	}
	
}
