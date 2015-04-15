package de.hybris.geb.module.hac

import geb.Module

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
		waitFor { !$("div#loginInfo").empty }
		$("div#loginInfo").click()
		browser.go(browser.config.rawConfig.hacUrl + "/j_spring_security_logout")
	}
}
