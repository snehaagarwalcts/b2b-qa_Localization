package lscob2b.test.data


import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import geb.spock.GebReportingSpec

import org.openqa.selenium.WebDriver

class PropertProvider extends GebReportingSpec {

	def String expectedValue(String baseProperty)
	{			
		String locale=browser.config.rawConfig.locale
					
		String FilePath = "B2B_Properties_"+locale+".xls"
		
		FileInputStream fs = new FileInputStream(FilePath)
		
		WorkbookSettings wbSettings = new WorkbookSettings()
		
		wbSettings.setEncoding("CP1252")
		
		Workbook wb = Workbook.getWorkbook(fs, wbSettings)
		
		Sheet sh = wb.getSheet("Sheet1")
		
		Cell cell
		
		int column=0
		
		for (int row = 0; row < sh.getRows(); row++)
		{
			cell = sh.getCell(column, row)
			
			if(cell.getContents()==baseProperty)
			{
				cell=sh.getCell(2, row)				
				break
			}			
		}														
		return cell.getContents()
	}
	
	int count=0
	def String verifyTrue(String actual, String expected)
	{	
		try
		{
			assert actual == expected
		}
		catch(AssertionError e)
		{
			e.printStackTrace()
			count++
		}
		finally
		{
			return true
		}
	}
	
	def String verifyTrueContains(String actual, String expected, String text)
	{
		try
		{
			assert actual.contains(expected)
		}
		catch(AssertionError e)
		{
			e.printStackTrace()
			count++
		}
		finally
		{
			return true
		}
	}
	
	def verifyTestFailedOrPassed(){
		if(count!=0)
		{
			count=0
			return false
		}
		else
		{
			return true
		}
	}
}
