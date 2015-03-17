package lscob2b.test.data

import lscob2b.data.PropertyProviderPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.WebDriver

class PropertProviderTest extends GebReportingSpec {
	
	def String expectedValue(String baseProperty)
	{
			
			String locale=browser.config.rawConfig.locale
			String propertyFullName = locale + "." + baseProperty
			
			//driver.manage().window().maximize()
						
		return PropertyProviderPage.getInstance().getLocalizedPropertyValue(propertyFullName)
	}

}
