package com.hybridFramework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hybridFramework.helper.logger.LoggerHelper;
import com.hybridFramework.helper.resource.ResourceHelper;

public class ExtentManager {

	private static ExtentReports extent;
	private static final Logger log = LoggerHelper.getLogger(ExtentManager.class);
	
	/**
	 * 
	 * @return
	 */
	public static ExtentReports getInstance() {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	//	String actualImageName = imageLocation + "/" + imageName + "_" +formater.format(calender.getTime()) + ".png";
		String reportName = ResourceHelper.getResourcePath("/src/test/java/com/hybridFramework/reports/" + "test_"+ formater.format(calender.getTime()) + ".html");

		if(extent == null) {
			return createInstance(reportName);
		}
		else {
			return extent;			
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		log.info("Test Report " +fileName+ " generated...");
		return extent;
	}
}
