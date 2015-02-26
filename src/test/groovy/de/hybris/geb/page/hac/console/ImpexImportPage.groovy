

package de.hybris.geb.page.hac.console 

import geb.Page
import de.hybris.geb.module.hac.MenuModule
import de.hybris.geb.module.hac.console.ImportContentModule
import de.hybris.geb.module.hac.console.ImportScriptModule

class ImpexImportPage extends Page {

	static url = "/console/impex/import"
	
	static at = { waitFor { title == "hybris administration console | ImpEx Import" } }
	
	static content = {
		
		menu { module MenuModule }
		
		importScript { module ImportScriptModule }
		
		importContent { module ImportContentModule }
		
		notification { $("div#notification") }
		
		tabs { $("ul.ui-tabs-nav") }
		
		tabContent { tabs.find("li",0).find("a") }
		
		tabScript { tabs.find("li",1).find("a") }
		
		logOut { $("form input",1)}
		
		settings { $("div#settings") }
		
		settingsHeader { settings.find("h3",0) }
		
		settingsContent { settings.find("div.ui-accordion-content") }
		
		settingsLegacyMode { settingsContent.find("input", id:"legacyMode1") }
		
	}
	
	def importTextScript(String impexText) {
		tabContent.click()
		
		importContent.setText(impexText)
		
		waitFor { importContent.importButton.displayed }
		importContent.importButton.click()
	}

	def importFileScript(String filePath) {
		tabScript.click()
		
		importScript.file.value(filePath)
		
		importScript.buttonImport.click()
	}
	
	def boolean checkNotification() {
		waitFor {
			notification.displayed
		}
		
		return notification.text() == "Import finished successfully"
	}
	
	def boolean getLegacyMode() {
		if(!settingsContent.displayed) {
			settingsHeader.click()
			waitFor { settingsContent.displayed }
		}
		settingsLegacyMode.value()
	}
	
	def void setLegacyMode(boolean mode) {
		if(getLegacyMode() != mode) {
			settingsLegacyMode.click()
		}
	}
		
}
