package com.hybridFramework.helper.config;

import com.hybridFramework.helper.browserConfiguration.BrowserType;

/**
 * 
 * @author vasudevp
 *
 */
public interface IConfigReader {

	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageloadTime();
	public BrowserType getBrowserType();
	public String getURL();
	public String getUserName();
	public String getPassword();
	public String getDbType();
	public String getDbConnStr();
	
}
