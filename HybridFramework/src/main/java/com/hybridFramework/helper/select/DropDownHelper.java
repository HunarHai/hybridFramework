package com.hybridFramework.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.hybridFramework.helper.logger.LoggerHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class DropDownHelper {
	
//	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(DropDownHelper.class);
	
/*	public DropDownHelper(WebDriver driver){
		this.driver = driver;
		log.info("DropDownHelper object created...");
	}*/
	
	public DropDownHelper(){
		log.info("DropDownHelper object created...");
	}
	
	/**
	 * This method is to select DropDown element by value
	 * @param element
	 * @param value
	 */
	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("selectUsingValue and value is: "+ value);
		select.selectByValue(value);
	}
	
	/**
	 * This method is to select DropDown element by index
	 * @param element
	 * @param index
	 */
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("selectUsingIndex and index is: "+ index);
		select.selectByIndex(index);
	}
	
	/***
	 * This method is to select DropDown element by Visible Text
	 * @param element
	 * @param visibleText
	 */
	public void selectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		log.info("selectUsingVisibleText and visible text is: "+ visibleText);
		select.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method is to de-select DropDown element by value
	 * @param element
	 * @param value
	 */
	public void deSelectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("deSelectUsingValue and value is: "+ value);
		select.deselectByValue(value);
	}
	
	/**
	 * This method is to de-select DropDown element by index
	 * @param element
	 * @param index
	 */
	public void deSelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("deSelectUsingIndex and index is: "+ index);
		select.deselectByIndex(index);
	}
	
	/***
	 * This method is to de-select DropDown element by Visible Text
	 * @param element
	 * @param visibleText
	 */
	public void deSelectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		log.info("deselectUsingVisibleText and visible text is: "+ visibleText);
		select.deselectByVisibleText(visibleText);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele:elementList){
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}
	
}