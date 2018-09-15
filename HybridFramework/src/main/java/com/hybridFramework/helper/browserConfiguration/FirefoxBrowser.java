package com.hybridFramework.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.hybridFramework.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class FirefoxBrowser {

	/**
	 * 
	 * @return
	 */
	public FirefoxOptions getFirefoxOption() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability(FirefoxDriver.PROFILE, profile);
		cap.setCapability("marrionette", true);
		
		FirefoxOptions option = new FirefoxOptions(cap);
		
		//Linux
		if (System.getProperty("os.name").contains("Linux")) {
			option.addArguments("--headless", "window-size=1024,768",
					"--no-sandbox");
		}
		
		return option;
	}
	
	/**
	 * 
	 * @param cap
	 * @return
	 */
	public WebDriver getFirefoxDriver(FirefoxOptions cap) {
		if(System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/windows/geckodriver.exe"));
			return new FirefoxDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/mac/geckodriver"));
			return new FirefoxDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/linux/geckodrive"));
			return new FirefoxDriver(cap);
		}
		return null;
	}
	
}
