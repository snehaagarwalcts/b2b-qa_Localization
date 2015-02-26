package de.hybris.geb.module.hac

import geb.Module;

class MenuModule extends Module {

	static content = {

		//Logout
		
		logout { $("div#loginInfo") }
		
		//Console Menu
		
		console { $("a#console") }

		consoleImpexImport { $("a", href: endsWith("/console/impex/import")) }
		
	}
	
	def goToConsoleImpexImport() {
		console.click();
		consoleImpexImport.click()
	}
		
	
}
