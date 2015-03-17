package lscob2b.test.data

import lscob2b.data.PropertyProviderPage
import geb.spock.GebReportingSpec

class PropertProviderTest extends GebReportingSpec {
	
	def String expectedValue(String baseProperty)
	{
			String locale=browser.config.rawConfig.locale
			String propertyFullName = locale + "." + baseProperty
						
		return PropertyProviderPage.getInstance().getLocalizedPropertyValue(propertyFullName)
	}

}
