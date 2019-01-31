package com.hybridFramework.loginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.hybridFramework.helper.assertion.AssertionHelper;
import com.hybridFramework.helper.config.PropertyReader;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.pageObject.LoginPage;
import com.hybridFramework.testBase.TestBase;

public class LoginTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Test for Login To Application")
	public void testLoginToApplication() {
		PropertyReader config = new PropertyReader();
		getApplicationURL(config.getURL());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(config.getUserName(), config.getPassword());
		
		boolean status = loginPage.verifyLoginSuccessMsg();

		//	AssertionHelper.updateTestStatus(status);
		if(status) {
			log.info("Login is successful...");
			AssertionHelper.makeTrue("Login is successful...");
			AssertionHelper.updateTestStatus(status);
		}
		else {
			
			AssertionHelper.makeFalse("Login is not successful...");
			AssertionHelper.updateTestStatus(status);
		}
	}

}
