package com.hybridFramework.registrationPage;

import org.testng.annotations.Test;

import com.hybridFramework.helper.assertion.AssertionHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.pageObject.LoginPage;
import com.hybridFramework.pageObject.MyAccountPage;
import com.hybridFramework.pageObject.RegistrationPage;
import com.hybridFramework.testBase.TestBase;

public class RegistrationTest extends TestBase{

//	private static final Logger log = LoggerHelper.getLogger(RegistrationTest.class);
	LoginPage loginPage;
	RegistrationPage registration;
	MyAccountPage myAccountPage;
	
	@Test(description="User registration with valid details")
	public void testRegistrationToApplication(){
		// go to application
		getApplicationURL(new PropertyReader().getURL());
		
		// navigate to registration page
		loginPage = new LoginPage(driver);
		loginPage.goToUserRegistrtion();

		// enter user registration data
		registration = new RegistrationPage(driver);
		registration.registerNewUserAccount();
		
		// enter myAccount data
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.verifyYourAccountPageMsg();
		
		AssertionHelper.updateTestStatus(status);	
	}

}
