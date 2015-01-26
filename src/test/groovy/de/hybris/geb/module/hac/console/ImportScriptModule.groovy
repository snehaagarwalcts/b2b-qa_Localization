package de.hybris.geb.module.hac.console

import geb.Module;

class ImportScriptModule extends Module {

	static content = {
		
		form { $("form#impexImportFile") }
				
		file { form.find("input", name: "file") }
		
		buttonImport { form.find("input", type: "submit") }
			
	}
	
}