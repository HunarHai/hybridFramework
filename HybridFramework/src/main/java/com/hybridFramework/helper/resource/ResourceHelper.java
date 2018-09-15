package com.hybridFramework.helper.resource;

/**
 * 
 * @author vasudevp
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String path) {
		String basePath = System.getProperty("user.dir");
	//	System.out.println(basePath);
	//	System.out.println(basePath + path);
		return basePath + "/" + path;
	}
}
