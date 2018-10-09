package com.hybridFramework.homePage;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridFramework.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class TestDataDrivenScript extends TestBase {
	
	/**
	 * 
	 * @return
	 */
	@DataProvider(name="testData")
	public Object[][] dataSource() {
		return getData("TestData.xlsx", "LoginTestData");
	}
	
	@Test(dataProvider = "testData")
	public void testLogin(String userName, String password, String runMode) {
	//	System.out.println("usreName: " +userName);
	//	System.out.println("password: " + password);
	//	System.out.println("RunMode: " +runMode);
		
		driver.findElement(By.xpath("")).sendKeys(userName);
		driver.findElement(By.xpath("")).sendKeys(password);
		driver.findElement(By.xpath("")).sendKeys(runMode);
	}
}
