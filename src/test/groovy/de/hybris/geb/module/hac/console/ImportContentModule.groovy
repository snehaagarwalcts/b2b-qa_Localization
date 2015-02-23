package de.hybris.geb.module.hac.console

import geb.Module
import groovy.json.StringEscapeUtils

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class ImportContentModule extends Module {

	static content = {
		
		form { $("form#contentForm") }
		
		textarea { form.find("textarea#script") }
		
		clearButton { form.find("button#clearScriptContent") }
		
		validateButton { form.find("input#validate") }
			
		importButton { form.find("input", value: "Import content") }
		
	}
	
	def void setText(txt) {
		
		//HACK cross-browser see https://code.google.com/p/selenium/issues/detail?id=7135 
		if(System.getProperty("geb.browser") != null && System.getProperty("geb.browser").startsWith("internet")) {
			WebElement textArea = $("div#textarea-container").firstElement()
			Actions builder = new Actions(driver);
			builder.click(textArea).sendKeys(txt).perform()
		} else {
			txt = StringEscapeUtils.escapeJavaScript(txt)
			js.exec("CodeMirror.fromTextArea(document.getElementById('script'), {mode: 'text/x-impex', lineNumbers: true, autofocus: true, extraKeys: {'F11': function(cm) {setFullScreen(cm, !isFullScreen(cm)); }, 'Esc': function(cm) {if (isFullScreen(cm)) setFullScreen(cm, false); }, 'Ctrl-Space': 'autocomplete'} }).setValue('" + txt + "');")
			js.exec("document.getElementById('textarea-container').style.display='none'")
		}
		
	}
	
}