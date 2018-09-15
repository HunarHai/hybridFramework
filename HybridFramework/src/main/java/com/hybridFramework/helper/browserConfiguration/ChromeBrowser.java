package com.hybridFramework.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.hybridFramework.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class ChromeBrowser {

	/**
	 * 
	 * @return
	 */
	public ChromeOptions getChromeOptions() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		
		if(System.getProperty("os.name").contains("Linux")) {
			option.addArguments("--headless","window-size=1024,768","--no--sandbox");
		}
		
		return option;
	}
	
	/**
	 * 
	 * @param cap
	 * @return
	 */
	public WebDriver getChromeDriver(ChromeOptions cap) {
		if(System.getProperty("os.name").contains("Windows")) {
			System.out.println(System.getProperty("os.name"));
			System.setProperty("webdriver.chrome.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/windows/chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/mac/chromedriver"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/linux/chromedriver"));
			return new ChromeDriver(cap);
		}
		return null;
	}
}
