package com.hybridFramework.helper.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hybridFramework.helper.browserConfiguration.BrowserType;
import com.hybridFramework.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class PropertyReader implements IConfigReader {

	public static Properties OR;
	public File f1;
	private static FileInputStream fis;

	public PropertyReader() {
		try {
			OR = new Properties();
			
			String configFilePath = ResourceHelper.getResourcePath("/src/main/resources/configFiles/config.properties");
			f1 = new File(configFilePath);
			fis = new FileInputStream(f1);
			OR.load(fis);
			
			String orFilePath = ResourceHelper.getResourcePath("/src/main/resources/configFiles/OR.properties");
			f1 = new File(orFilePath);
			fis = new FileInputStream(f1);
			OR.load(fis);
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}

	@Override
	public int getPageloadTime() {
		return Integer.parseInt(OR.getProperty("pageLoadTime"));
	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	@Override
	public String getURL() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationURL");
	}

	@Override
	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	@Override
	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}

}
