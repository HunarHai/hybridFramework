package com.hybridFramework.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hybridFramework.helper.logger.LoggerHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class ExcelHelper {

	private static Logger log = LoggerHelper.getLogger(ExcelHelper.class);
	
	/**
	 * 
	 * @param excelLocation
	 * @param sheetName
	 * @return
	 */
	public Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object dataSets[][] = null;
			File file = new File(excelLocation);
			FileInputStream fis = new FileInputStream(file);
			
			//Create workbook instance
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Get sheet from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//Get number of active rows in sheet
			int totalRows = sheet.getLastRowNum();
			
			//Get number of active columns in row
			int totalColumns = sheet.getRow(0).getLastCellNum();
			
			dataSets = new Object[totalRows][totalColumns];
			
			// Iterate thru each row one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int t = 0;
			while(rowIterator.hasNext()) {
				// For every row, we need to iterate over the columns
				Row row = rowIterator.next();
				if(i++ !=0) {
					int k = t;
					t++;

					//Iterate through each cell one by one
					Iterator<Cell> cellIterator = row.iterator();
					int j = 0;
					while(cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						switch(cell.getCellTypeEnum()) {
						case STRING: 
							dataSets[k][j++] = cell.getStringCellValue();
							break;
						
						case NUMERIC:
							dataSets[k][j++] = cell.getStringCellValue();
							break;
						
						case BOOLEAN:
							dataSets[k][j++] = cell.getStringCellValue();
							break;
							
						case FORMULA:
							dataSets[k][j++] = cell.getStringCellValue();
							break;
							
						default:
							System.out.println("No matching enum data type found.");
						}
					}
				}
			}
			
			workbook.close();
			fis.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		
		return null;
	}
}
