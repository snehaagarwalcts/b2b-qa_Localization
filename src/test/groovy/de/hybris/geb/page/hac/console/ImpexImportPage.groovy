package de.hybris.geb.page.hac.console 

import geb.Page
import de.hybris.geb.module.hac.MenuModule
import de.hybris.geb.module.hac.console.ImportScriptModule

class ImpexImportPage extends Page {

	static url = "/console/impex/import"
	
	static at = { title == "hybris administration console | ImpEx Import" }
	
	static content = {
		
		menu { module MenuModule }
		
		importScript { module ImportScriptModule }
		
		notification { $("div#notification") }
		
		tabs { $("ul.ui-tabs-nav") }
		
		tabScript { tabs.find("li",1).find("a") }
 		
	}

	def importScript(String filePath) {
		tabScript.click()
		
		importScript.file.value(filePath)
		
		importScript.buttonImport.click()
		
		return checkNotification()
		
	}
	
	def boolean checkNotification() {
		waitFor {
			notification.displayed
		}
		
		return notification.text() == "Import finished successfully"
	}
	
		
}
