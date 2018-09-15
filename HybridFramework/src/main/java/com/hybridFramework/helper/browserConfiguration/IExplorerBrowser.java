package com.hybridFramework.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.hybridFramework.helper.resource.ResourceHelper;

public class IExplorerBrowser {

	/**
	 * 
	 * @return
	 */
	public InternetExplorerOptions getIExplorerOptions() {
		DesiredCapabilities iExplorer = DesiredCapabilities.internetExplorer();
		iExplorer.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		iExplorer.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		iExplorer.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		iExplorer.setJavascriptEnabled(true);
		
		InternetExplorerOptions option = new InternetExplorerOptions(iExplorer);
		
		return option;
	}
	
	/**
	 * 
	 * @param cap
	 * @return
	 */
	public WebDriver getIExplorerDriver(InternetExplorerOptions cap) {
		if(System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/windows/IEDriverServer.exe"));
			return new InternetExplorerDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/mac/IEDriverServer"));
			return new InternetExplorerDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper
					.getResourcePath("/src/main/resources/drivers/linux/IEDriverServer"));
			return new InternetExplorerDriver(cap);
		}
		return null;
	}
	
}
