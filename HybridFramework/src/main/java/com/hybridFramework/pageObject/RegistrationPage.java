package com.hybridFramework.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.wait.WaitHelper;
import com.hybridFramework.testBase.TestBase;

public class RegistrationPage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(RegistrationPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='id_gender1']")
	public WebElement mrRadioBtn;
	
	@FindBy(xpath="//*[@id='id_gender2']")
	public WebElement mrsRadioBtn;
	
	@FindBy(xpath="//*[@id='customer_firstname']")
	public WebElement firstName;
	
	@FindBy(xpath="//*[@id='customer_lastname']")
	public WebElement lastName;
	
	@FindBy(xpath="//*[@id='email']")
	public WebElement emailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	public WebElement password;
	
	@FindBy(xpath="//*[@id='days']")
	public WebElement days;
	
	@FindBy(xpath="//*[@id='months']")
	public WebElement month;
	
	@FindBy(xpath="//*[@id='years']")
	public WebElement year;
	
	@FindBy(xpath="//*[@id='firstname']")
	public WebElement yourAddressFirstName;
	
	@FindBy(xpath="//*[@id='lastname']")
	public WebElement yourAddressLastName;
	
	@FindBy(xpath="//*[@id='company']")
	public WebElement yourAddressCompany;
	
	@FindBy(xpath="//*[@id='address1']")
	public WebElement address1;
	
	@FindBy(xpath="//*[@id='address2']")
	public WebElement address2;
	
	@FindBy(xpath="//*[@id='city']")
	public WebElement yourAddressCity;
	
	@FindBy(xpath="//*[@id='id_state']")
	public WebElement yourAddressState;
	
	@FindBy(xpath="//*[@id='postcode']")
	public WebElement yourAddressPostCode;
	
	@FindBy(xpath="//*[@id='id_country']")
	public WebElement yourAddressCountry;
	
	@FindBy(xpath="//*[@id='phone']")
	public WebElement homePhone;
	
	@FindBy(xpath="//*[@id='phone_mobile']")
	public WebElement mobilePhone;
	
	@FindBy(xpath="//*[@id='alias']")
	public WebElement addressAlias;
	
	@FindBy(xpath="//*[@id='submitAccount']")
	public WebElement registration;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(firstName, new PropertyReader().getExplicitWait());
		
		log.info("RegistrationPage Object created...");
		TestBase.logExtentReport("RegistrationPage Object created...");
		new TestBase().getNavigationScreen(driver);
	}
	

	public void setMrRadioButton(){
		this.mrRadioBtn.click();
		log.info("Selecting Mr. radio button");
		TestBase.logExtentReport("Selecting Mr. radio button...");
	}
	
	public void setMrsRadioButton(){
		this.mrsRadioBtn.click();
		log.info("Selecting Mrs. radio button");
		TestBase.logExtentReport("Selecting Mrs. radio button...");
	}
	
	public void setFirstName(String firstName){
		this.firstName.sendKeys(firstName);
		log.info("Entering First Name..." +firstName);
		TestBase.logExtentReport("Entering First Name..." +firstName);
	}
	
	public void setLastName(String lastName){
		this.lastName.sendKeys(lastName);
		log.info("Entering Last Name..." +lastName);
		TestBase.logExtentReport("Entering Last Name..." +lastName);
	}
	
	public void setEmailAddress(String emailId){
		TestBase.logExtentReport("Entering Email Address ..." +emailId);
		this.emailAddress.sendKeys(emailId);
		log.info("Entering Email Address ...");
	}
	
	public void setPassword(String password){
		this.password.sendKeys(password);
		log.info("Entering Password..." +password);
		TestBase.logExtentReport("Entering Password..." +password);
	}
	
	/**
	 * 
	 * @param day
	 */
	public void setDay(String day){
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='days']/option"));
		Iterator<WebElement> itr = days.iterator();
		while(itr.hasNext()){
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if(text.equals(day)){
			//	System.out.println(day);
				c.click();
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param month
	 */
	public void setMonth(String month){
		List<WebElement> months = driver.findElements(By.xpath("//*[@id='months']/option"));
		Iterator<WebElement> itr = months.iterator();
		while(itr.hasNext()){
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if(text.equals(month)){
			//	System.out.println(month);
				c.click();
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param year
	 */
	public void setYear(String year){
		List<WebElement> years = driver.findElements(By.xpath("//*[@id='years']/option"));
		Iterator<WebElement> itr = years.iterator();
		while(itr.hasNext()){
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if(text.equals(year)){
			//	System.out.println(year);
				c.click();
				break;
			}
		}
	}
	
	public void setYourAddressFirstName(String yourAddressFirstName){
		this.yourAddressFirstName.sendKeys(yourAddressFirstName);
		log.info("Entering yourAddressFirstName..." +yourAddressFirstName);
		TestBase.logExtentReport("Entering yourAddressFirstName..." +yourAddressFirstName);
	}
	
	public void setYourAddressLastName(String yourAddressLastName){
		this.yourAddressLastName.sendKeys(yourAddressLastName);
		log.info("Entering yourAddressLastName..." +yourAddressLastName);
		TestBase.logExtentReport("Entering yourAddressLastName..." +yourAddressLastName);
	}
	
	public void setYourAddressCompany(String yourAddressCompany){
		this.yourAddressCompany.sendKeys(yourAddressCompany);
		log.info("Entering yourAddressCompany..." +yourAddressCompany);
		TestBase.logExtentReport("Entering yourAddressCompany..." +yourAddressCompany);
	}
	
	public void setAddress1(String address1){
		this.address1.sendKeys(address1);
		log.info("Entering address1..." +address1);
		TestBase.logExtentReport("Entering address1..." +address1);
	}
	
	public void setAddress2(String address2){
		this.address2.sendKeys(address2);
		log.info("Entering address2..." +address2);
		TestBase.logExtentReport("Entering address2..." +address2);
	}

	public void setYourAddressCity(String yourAddressCity){
		this.yourAddressCity.sendKeys(yourAddressCity);
		log.info("Entering yourAddressCity..." +yourAddressCity);
		TestBase.logExtentReport("Entering yourAddressCity..." +yourAddressCity);
	}
	
	public void setYourAddressState(String yourAddressState){
		this.yourAddressState.sendKeys(yourAddressState);
		log.info("Entering yourAddressState..." +yourAddressState);
		TestBase.logExtentReport("Entering yourAddressState..." +yourAddressState);
	}
	
	public void setYourAddressPostCode(String yourAddressPostCode){
		this.yourAddressPostCode.sendKeys(yourAddressPostCode);
		log.info("Entering yourAddressPostCode..." +yourAddressPostCode);
		TestBase.logExtentReport("Entering yourAddressPostCode..." +yourAddressPostCode);
	}
	
	public void setYourAddressCountry(String yourAddressCountry){
		this.yourAddressCountry.sendKeys(yourAddressCountry);
		log.info("Entering yourAddressCountry..." +yourAddressCountry);
		TestBase.logExtentReport("Entering yourAddressCountry..." +yourAddressCountry);
	}
	
	public void setHomePhone(String homePhone){
		this.homePhone.sendKeys(homePhone);
		log.info("Entering homePhone..." +homePhone);
		TestBase.logExtentReport("Entering homePhone..." +homePhone);
	}
	
	public void setMobilePhone(String mobilePhone){
		this.mobilePhone.sendKeys(mobilePhone);
		log.info("Entering mobilePhone..." +mobilePhone);
		TestBase.logExtentReport("Entering mobilePhone..." +mobilePhone);
	}
	
	public void setAddressAlias(String addressAlias){
		this.addressAlias.sendKeys(addressAlias);
		log.info("Entering addressAlias..." +addressAlias);
		TestBase.logExtentReport("Entering addressAlias..." +addressAlias);
	}
	
	public MyAccountPage clickRegisterButton(){
		this.registration.click();
		log.info("Clicking Register button..." +registration);
		TestBase.logExtentReport("Clicking Register button..." +registration);
		return new MyAccountPage(driver);
	}
	
	/**
	 * Wrapper method for new user account registration
	 */
	public void registerNewUserAccount() {
		setMrRadioButton();
		setMrRadioButton();
		setFirstName("Fina");
		setLastName("Lana");
	//	setEmailAddress();
		setPassword("password");
		setDay("5");
		setMonth("June");
		setYear("2017");
		
		setYourAddressFirstName("yourAddressFirstName");
		setYourAddressLastName("yourAddressLastName");
		setYourAddressCompany("yourAddressCompany");
		setAddress1("address1");
		setAddress2("address2");
		setYourAddressCity("yourAddressCity");
		setYourAddressState("Alaska");
		setYourAddressPostCode("99501");
		setYourAddressCountry("yourAddressCountry");
		setMobilePhone("9999855885");
		setAddressAlias("aliasAddress");
		clickRegisterButton();
	}
	
}
