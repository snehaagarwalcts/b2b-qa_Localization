package de.hybris.geb.module.hac

import geb.Module;

class MenuModule extends Module {

	static content = {

		//Console Menu
		
		console { $("a#console") }

		consoleImpexImport { $("a", href: endsWith("/console/impex/import")) }
		
	}
	
	def goToConsoleImpexImport() {
		console.click();
		consoleImpexImport.click()
	}
		
	def logout() {
		//Different version of logout button on each environment
		if(!$("div#loginInfo").empty) {
			$("div#loginInfo").click()
		}
		if(!$("input", value:"logout").empty) {
			$("input", value:"logout").click()
		}
	}
}
