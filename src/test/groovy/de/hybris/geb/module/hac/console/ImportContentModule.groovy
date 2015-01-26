package de.hybris.geb.module.hac.console

import geb.Module;

class ImportContentModule extends Module {

	static content = {
		
		form { $("form#contentForm") }
		
		textarea { form.find("textarea#script") }
		
		clearButton { form.find("button#clearScriptContent") }
		
		validateButton { form.find("input#validate") }
			
		importButton { form.find("input", value: "Import content") }
		
	}
		
}